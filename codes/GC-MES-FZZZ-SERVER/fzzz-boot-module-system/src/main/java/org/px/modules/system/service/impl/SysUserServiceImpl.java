package org.px.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.px.common.api.vo.Result;
import org.px.common.constant.CacheConstant;
import org.px.common.constant.CommonConstant;
import org.px.common.system.api.ISysBaseAPI;
import org.px.common.system.vo.LoginUser;
import org.px.common.system.vo.SysUserCacheInfo;
import org.px.common.util.PasswordUtil;
import org.px.common.util.UUIDGenerator;
import org.px.common.util.oConvertUtils;
import org.px.modules.base.service.BaseCommonService;
import org.px.modules.orderbymakeclothplan.pojo.ZyOrder;
import org.px.modules.system.entity.*;
import org.px.modules.system.mapper.*;
import org.px.modules.system.entity.*;
import org.px.modules.system.mapper.*;
import org.px.modules.system.model.SysUserSysDepartModel;
import org.px.modules.system.service.ISysUserService;
import org.px.modules.system.vo.Grzx1;

import org.px.modules.system.vo.SysUserDepVo;
import org.px.modules.system.vo.SysUserdfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @@author 品讯科技
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysUserDepartMapper sysUserDepartMapper;
	@Autowired
	private ISysBaseAPI sysBaseAPI;
	@Autowired
	private SysDepartMapper sysDepartMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysDepartRoleUserMapper departRoleUserMapper;
	@Autowired
	private SysDepartRoleMapper sysDepartRoleMapper;
	@Resource
	private BaseCommonService baseCommonService;
	@Autowired
	private SysThirdAccountMapper sysThirdAccountMapper;
	@Autowired
	ThirdAppWechatEnterpriseServiceImpl wechatEnterpriseService;
	@Autowired
	ThirdAppDingtalkServiceImpl dingtalkService;

    @Override
    @CacheEvict(value = {CacheConstant.SYS_USERS_CACHE}, allEntries = true)
    public Result<?> resetPassword(String username, String oldpassword, String newpassword, String confirmpassword) {
        SysUser user = userMapper.getUserByName(username);
        String passwordEncode = PasswordUtil.encrypt(username, oldpassword, user.getSalt());
        if (!user.getPassword().equals(passwordEncode)) {
            return Result.error("旧密码输入错误!");
        }
        if (oConvertUtils.isEmpty(newpassword)) {
            return Result.error("新密码不允许为空!");
        }
        if (!newpassword.equals(confirmpassword)) {
            return Result.error("两次输入密码不一致!");
        }
        String password = PasswordUtil.encrypt(username, newpassword, user.getSalt());
        this.userMapper.update(new SysUser().setPassword(password), new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, user.getId()));
        return Result.ok("密码重置成功!");
    }

    @Override
    @CacheEvict(value = {CacheConstant.SYS_USERS_CACHE}, allEntries = true)
    public Result<?> changePassword(SysUser sysUser) {
        String salt = oConvertUtils.randomGen(8);
        sysUser.setSalt(salt);
        String password = sysUser.getPassword();
        String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), password, salt);
        sysUser.setPassword(passwordEncode);
        this.userMapper.updateById(sysUser);
        return Result.ok("密码修改成功!");
    }

    @Override
    @CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteUser(String userId) {
		//1.删除用户
		this.removeById(userId);
		return false;
	}

	@Override
    @CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional(rollbackFor = Exception.class)
	public boolean deleteBatchUsers(String userIds) {
		//1.删除用户
		this.removeByIds(Arrays.asList(userIds.split(",")));
		return false;
	}

	@Override
	public SysUser getUserByName(String username) {
		return userMapper.getUserByName(username);
	}


	@Override
	@Transactional
	public void addUserWithRole(SysUser user, String roles) {
		this.save(user);
		if(oConvertUtils.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}

	@Override
	@CacheEvict(value= {CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	@Transactional
	public void editUserWithRole(SysUser user, String roles) {
		this.updateById(user);
		//先删后加
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, user.getId()));
		if(oConvertUtils.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
	}


	@Override
	public List<String> getRole(String username) {
		return sysUserRoleMapper.getRoleByUserName(username);
	}

	/**
	 * 通过用户名获取用户角色集合
	 * @param username 用户名
     * @return 角色集合
	 */
	@Override
	public Set<String> getUserRolesSet(String username) {
		// 查询用户拥有的角色集合
		List<String> roles = sysUserRoleMapper.getRoleByUserName(username);
		log.info("-------通过数据库读取用户拥有的角色Rules------username： " + username + ",Roles size: " + (roles == null ? 0 : roles.size()));
		return new HashSet<>(roles);
	}

	/**
	 * 通过用户名获取用户权限集合
	 *
	 * @param username 用户名
	 * @return 权限集合
	 */
	@Override
	public Set<String> getUserPermissionsSet(String username) {
		Set<String> permissionSet = new HashSet<>();
		List<SysPermission> permissionList = sysPermissionMapper.queryByUser(username);
		for (SysPermission po : permissionList) {
//			// TODO URL规则有问题？
//			if (oConvertUtils.isNotEmpty(po.getUrl())) {
//				permissionSet.add(po.getUrl());
//			}
			if (oConvertUtils.isNotEmpty(po.getPerms())) {
				permissionSet.add(po.getPerms());
			}
		}
		log.info("-------通过数据库读取用户拥有的权限Perms------username： "+ username+",Perms size: "+ (permissionSet==null?0:permissionSet.size()) );
		return permissionSet;
	}

	@Override
	public SysUserCacheInfo getCacheUser(String username) {
		SysUserCacheInfo info = new SysUserCacheInfo();
		info.setOneDepart(true);
//		SysUser user = userMapper.getUserByName(username);
//		info.setSysUserCode(user.getUsername());
//		info.setSysUserName(user.getRealname());


		LoginUser user = sysBaseAPI.getUserByName(username);
		if(user!=null) {
			info.setSysUserCode(user.getUsername());
			info.setSysUserName(user.getRealname());
			info.setSysOrgCode(user.getOrgCode());
		}

		//多部门支持in查询
		List<SysDepart> list = sysDepartMapper.queryUserDeparts(user.getId());
		List<String> sysMultiOrgCode = new ArrayList<String>();
		if(list==null || list.size()==0) {
			//当前用户无部门
			//sysMultiOrgCode.add("0");
		}else if(list.size()==1) {
			sysMultiOrgCode.add(list.get(0).getOrgCode());
		}else {
			info.setOneDepart(false);
			for (SysDepart dpt : list) {
				sysMultiOrgCode.add(dpt.getOrgCode());
			}
		}
		info.setSysMultiOrgCode(sysMultiOrgCode);

		return info;
	}

	// 根据部门Id查询
	@Override
	public IPage<SysUser> getUserByDepId(Page<SysUser> page, String departId,String username) {
		return userMapper.getUserByDepId(page, departId,username);
	}

	@Override
	public IPage<SysUser> getUserByDepIds(Page<SysUser> page, List<String> departIds, String username) {
		return userMapper.getUserByDepIds(page, departIds,username);
	}
	@Override
	public List<SysUser> getUserByDepIds(List<String> departIds, String username) {
		return userMapper.getUserByDepIds(departIds,username);
	}
	@Override
	public Map<String, String> getDepNamesByUserIds(List<String> userIds) {
		List<SysUserDepVo> list = this.baseMapper.getDepNamesByUserIds(userIds);

		Map<String, String> res = new HashMap<String, String>();
		list.forEach(item -> {
					if (res.get(item.getUserId()) == null) {
						res.put(item.getUserId(), item.getDepartName());
					} else {
						res.put(item.getUserId(), res.get(item.getUserId()) + "," + item.getDepartName());
					}
				}
		);
		return res;
	}

	@Override
	public IPage<SysUser> getUserByDepartIdAndQueryWrapper(Page<SysUser> page, String departId, QueryWrapper<SysUser> queryWrapper) {
		LambdaQueryWrapper<SysUser> lambdaQueryWrapper = queryWrapper.lambda();

		lambdaQueryWrapper.eq(SysUser::getDelFlag, CommonConstant.DEL_FLAG_0);
        lambdaQueryWrapper.inSql(SysUser::getId, "SELECT user_id FROM sys_user_depart WHERE dep_id = '" + departId + "'");

        return userMapper.selectPage(page, lambdaQueryWrapper);
	}

	@Override
	public IPage<SysUserSysDepartModel> queryUserByOrgCode(String orgCode, SysUser userParams, IPage page) {
		List<SysUserSysDepartModel> list = baseMapper.getUserByOrgCode(page, orgCode, userParams);
		Integer total = baseMapper.getUserByOrgCodeTotal(orgCode, userParams);

		IPage<SysUserSysDepartModel> result = new Page<>(page.getCurrent(), page.getSize(), total);
		result.setRecords(list);

		return result;
	}

	// 根据角色Id查询
	@Override
	public IPage<SysUser> getUserByRoleId(Page<SysUser> page, String roleId, String username) {
		return userMapper.getUserByRoleId(page,roleId,username);
	}


	@Override
	@CacheEvict(value= {CacheConstant.SYS_USERS_CACHE}, key="#username")
	public void updateUserDepart(String username,String orgCode) {
		baseMapper.updateUserDepart(username, orgCode);
	}


	@Override
	public SysUser getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}


	@Override
	public SysUser getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Override
	@Transactional
	public void addUserWithDepart(SysUser user, String selectedParts) {
//		this.save(user);  //保存角色的时候已经添加过一次了
		if(oConvertUtils.isNotEmpty(selectedParts)) {
			String[] arr = selectedParts.split(",");
			for (String deaprtId : arr) {
				SysUserDepart userDeaprt = new SysUserDepart(user.getId(), deaprtId);
				sysUserDepartMapper.insert(userDeaprt);
			}
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	public void editUserWithDepart(SysUser user, String departs) {
		this.updateById(user);  //更新角色的时候已经更新了一次了，可以再跟新一次
		String[] arr = {};
		if(oConvertUtils.isNotEmpty(departs)){
			arr = departs.split(",");
		}
		//查询已关联部门
		List<SysUserDepart> userDepartList = sysUserDepartMapper.selectList(new QueryWrapper<SysUserDepart>().lambda().eq(SysUserDepart::getUserId, user.getId()));
		if(userDepartList != null && userDepartList.size()>0){
			for(SysUserDepart depart : userDepartList ){
				//修改已关联部门删除部门用户角色关系
				if(!Arrays.asList(arr).contains(depart.getDepId())){
					List<SysDepartRole> sysDepartRoleList = sysDepartRoleMapper.selectList(
							new QueryWrapper<SysDepartRole>().lambda().eq(SysDepartRole::getDepartId,depart.getDepId()));
					List<String> roleIds = sysDepartRoleList.stream().map(SysDepartRole::getId).collect(Collectors.toList());
					if(roleIds != null && roleIds.size()>0){
						departRoleUserMapper.delete(new QueryWrapper<SysDepartRoleUser>().lambda().eq(SysDepartRoleUser::getUserId, user.getId())
								.in(SysDepartRoleUser::getDroleId,roleIds));
					}
				}
			}
		}
		//先删后加
		sysUserDepartMapper.delete(new QueryWrapper<SysUserDepart>().lambda().eq(SysUserDepart::getUserId, user.getId()));
		if(oConvertUtils.isNotEmpty(departs)) {
			for (String departId : arr) {
				SysUserDepart userDepart = new SysUserDepart(user.getId(), departId);
				sysUserDepartMapper.insert(userDepart);
			}
		}
	}


	/**
	   * 校验用户是否有效
	 * @param sysUser
	 * @return
	 */
	@Override
	public Result<?> checkUserIsEffective(SysUser sysUser) {
		Result<?> result = new Result<Object>();
		//情况1：根据用户信息查询，该用户不存在
		if (sysUser == null) {
			result.error500("该用户不存在，请注册");
			baseCommonService.addLog("用户登录失败，用户不存在！", CommonConstant.LOG_TYPE_1, null);
			return result;
		}
		//情况2：根据用户信息查询，该用户已注销
		//update-begin---author:王帅   Date:20200601  for：if条件永远为falsebug------------
		if (CommonConstant.DEL_FLAG_1.equals(sysUser.getDelFlag())) {
		//update-end---author:王帅   Date:20200601  for：if条件永远为falsebug------------
			baseCommonService.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已注销！", CommonConstant.LOG_TYPE_1, null);
			result.error500("该用户已注销");
			return result;
		}
		//情况3：根据用户信息查询，该用户已冻结
		if (CommonConstant.USER_FREEZE.equals(sysUser.getStatus())) {
			baseCommonService.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已冻结！", CommonConstant.LOG_TYPE_1, null);
			result.error500("该用户已冻结");
			return result;
		}
		return result;
	}

	@Override
	public List<SysUser> queryLogicDeleted() {
		return this.queryLogicDeleted(null);
	}

	@Override
	public List<SysUser> queryLogicDeleted(LambdaQueryWrapper<SysUser> wrapper) {
		if (wrapper == null) {
			wrapper = new LambdaQueryWrapper<>();
		}
		wrapper.eq(SysUser::getDelFlag, CommonConstant.DEL_FLAG_1);
		return userMapper.selectLogicDeleted(wrapper);
	}

	@Override
	public boolean revertLogicDeleted(List<String> userIds, SysUser updateEntity) {
		String ids = String.format("'%s'", String.join("','", userIds));
		return userMapper.revertLogicDeleted(ids, updateEntity) > 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean removeLogicDeleted(List<String> userIds) {
		String ids = String.format("'%s'", String.join("','", userIds));
		// 1. 删除用户
		int line = userMapper.deleteLogicDeleted(ids);
		// 2. 删除用户部门关系
		line += sysUserDepartMapper.delete(new LambdaQueryWrapper<SysUserDepart>().in(SysUserDepart::getUserId, userIds));
		//3. 删除用户角色关系
		line += sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().in(SysUserRole::getUserId, userIds));
		//4.同步删除第三方App的用户
		try {
			dingtalkService.removeThirdAppUser(userIds);
			wechatEnterpriseService.removeThirdAppUser(userIds);
		} catch (Exception e) {
			log.error("同步删除第三方App的用户失败：", e);
		}
		//5. 删除第三方用户表（因为第4步需要用到第三方用户表，所以在他之后删）
		line += sysThirdAccountMapper.delete(new LambdaQueryWrapper<SysThirdAccount>().in(SysThirdAccount::getSysUserId, userIds));

		return line != 0;
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateNullPhoneEmail() {
        userMapper.updateNullByEmptyString("email");
        userMapper.updateNullByEmptyString("phone");
        return true;
    }

	@Override
	public void saveThirdUser(SysUser sysUser) {
		//保存用户
		String userid = UUIDGenerator.generate();
		sysUser.setId(userid);
		baseMapper.insert(sysUser);
		//获取第三方角色
		SysRole sysRole = sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleCode, "third_role"));
		//保存用户角色
		SysUserRole userRole = new SysUserRole();
		userRole.setRoleId(sysRole.getId());
		userRole.setUserId(userid);
		sysUserRoleMapper.insert(userRole);
	}

	@Override
	public List<SysUser> queryByDepIds(List<String> departIds, String username) {
		return userMapper.queryByDepIds(departIds,username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUser user, String selectedRoles, String selectedDeparts) {
		//step.1 保存用户
		this.save(user);
		//step.2 保存角色
		if(oConvertUtils.isNotEmpty(selectedRoles)) {
			String[] arr = selectedRoles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}
		//step.3 保存所属部门
		if(oConvertUtils.isNotEmpty(selectedDeparts)) {
			String[] arr = selectedDeparts.split(",");
			for (String deaprtId : arr) {
				SysUserDepart userDeaprt = new SysUserDepart(user.getId(), deaprtId);
				sysUserDepartMapper.insert(userDeaprt);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value={CacheConstant.SYS_USERS_CACHE}, allEntries=true)
	public void editUser(SysUser user, String roles, String departs) {
		//step.1 修改用户基础信息
		this.updateById(user);
		//step.2 修改角色
		//处理用户角色 先删后加
		sysUserRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, user.getId()));
		if(oConvertUtils.isNotEmpty(roles)) {
			String[] arr = roles.split(",");
			for (String roleId : arr) {
				SysUserRole userRole = new SysUserRole(user.getId(), roleId);
				sysUserRoleMapper.insert(userRole);
			}
		}

		//step.3 修改部门
		String[] arr = {};
		if(oConvertUtils.isNotEmpty(departs)){
			arr = departs.split(",");
		}
		//查询已关联部门
		List<SysUserDepart> userDepartList = sysUserDepartMapper.selectList(new QueryWrapper<SysUserDepart>().lambda().eq(SysUserDepart::getUserId, user.getId()));
		if(userDepartList != null && userDepartList.size()>0){
			for(SysUserDepart depart : userDepartList ){
				//修改已关联部门删除部门用户角色关系
				if(!Arrays.asList(arr).contains(depart.getDepId())){
					List<SysDepartRole> sysDepartRoleList = sysDepartRoleMapper.selectList(
							new QueryWrapper<SysDepartRole>().lambda().eq(SysDepartRole::getDepartId,depart.getDepId()));
					List<String> roleIds = sysDepartRoleList.stream().map(SysDepartRole::getId).collect(Collectors.toList());
					if(roleIds != null && roleIds.size()>0){
						departRoleUserMapper.delete(new QueryWrapper<SysDepartRoleUser>().lambda().eq(SysDepartRoleUser::getUserId, user.getId())
								.in(SysDepartRoleUser::getDroleId,roleIds));
					}
				}
			}
		}
		//先删后加
		sysUserDepartMapper.delete(new QueryWrapper<SysUserDepart>().lambda().eq(SysUserDepart::getUserId, user.getId()));
		if(oConvertUtils.isNotEmpty(departs)) {
			for (String departId : arr) {
				SysUserDepart userDepart = new SysUserDepart(user.getId(), departId);
				sysUserDepartMapper.insert(userDepart);
			}
		}
		//step.4 修改手机号和邮箱
		// 更新手机号、邮箱空字符串为 null
		userMapper.updateNullByEmptyString("email");
		userMapper.updateNullByEmptyString("phone");

	}

	@Override
	public List<String> userIdToUsername(Collection<String> userIdList) {
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.in(SysUser::getId, userIdList);
		List<SysUser> userList = super.list(queryWrapper);
		return userList.stream().map(SysUser::getUsername).collect(Collectors.toList());
	}

	@Override
	public List<Grzx1> getallgr1(String userid) {
		return userMapper.getallgr1(userid);
	}

	//这个是获取订单的数据中的商品数据
	@Override
	public Grzx1 getallg2(String id) {
		return userMapper.getallg2(id);
	}

	@Override
	public ZyOrder getallzyo(String id) {
		return userMapper.getallzyo(id);
	}

	//这个是获取一个虚拟用户的对象
	@Override
	public SysUserdfVO getuser() {
		//虚拟角色id
		String roleid="1630020854744330241";
		//根据角色id查询用户id
		List<String> uids = new ArrayList<>();
		//获取用户id的合集
		uids = userMapper.getuserids(roleid);
		Random random = new Random();
		int n = random.nextInt(uids.size());
		String needid = uids.get(n);
		SysUser sysUser = new  SysUser();
		sysUser = userMapper.getneeduser(needid);
		String needuname = sysUser.getId();
		List<String> dizhi = userMapper.getuserdizhi(needuname);
		List<String> fapiao = userMapper.getuserfapiao(needuname);
		List<String> measureId = userMapper.getusermeasureId(needuname);
		SysUserdfVO sdfvo = new SysUserdfVO();
		sdfvo.setSysUser(sysUser);
		sdfvo.setDizhi(dizhi);
		sdfvo.setFapiao(fapiao);
		//量体
		if(measureId.size()>0)
		{
			sdfvo.setMeasureId(measureId.get((measureId.size()-1)));
		}

		//System.out.println(sdfvo);
		return sdfvo;
	}

	@Override
	public SysUser getneeduser(String id) {
		return userMapper.getneeduser(id);
	}

	@Override
	public void updateneed(String id, String sex, String birthday, String realname, String phone, String email) {
		userMapper.updateneed(id,sex,birthday,realname,phone,email);
	}

	@Override
	public List<Grzx1> getscgoods(String userid) {
		return userMapper.getscgoods(userid);
	}

	@Override
	public List<String> getscgoodml(String id) {
		return userMapper.getscgoodml(id);
	}

	@Override
	public List<String> getscgoodfi(String id) {
		return userMapper.getscgoodfi(id);
	}

}
