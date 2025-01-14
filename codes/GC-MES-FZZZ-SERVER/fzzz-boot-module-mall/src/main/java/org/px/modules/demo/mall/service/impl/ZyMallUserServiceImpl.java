package org.px.modules.demo.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.px.modules.demo.mall.entity.BodyDataVo;
import org.px.modules.demo.mall.entity.ZyMallUser;
import org.px.modules.demo.mall.mapper.*;
import org.px.modules.demo.mall.mapper.*;
import org.px.modules.demo.mall.service.IZyMallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 用户表
 * @@author 品讯科技
 * @Date:   2021-10-21
 * @Version: V1.0
 */
@Service
public class ZyMallUserServiceImpl extends ServiceImpl<ZyMallUserMapper, ZyMallUser> implements IZyMallUserService {

	@Autowired
	private ZyMallUserMapper zyMallUserMapper;
	@Autowired
	private ZyMallAddressMapper zyMallAddressMapper;
	@Autowired
	private ZyMallWardrobeMapper zyMallWardrobeMapper;
	@Autowired
	private ZyMallUserDepartMapper zyMallUserDepartMapper;
	@Autowired
	private ZyMallBodyDataMapper zyMallBodyDataMapper;
	@Autowired
	private ZyMallUserRoleMapper zyMallUserRoleMapper;
	@Autowired
	private ZyMallReturnOrderMapper zyMallReturnOrderMapper;

	@Override
	@Transactional
	public void delMain(String id) {
		zyMallAddressMapper.deleteByMainId(id);
		zyMallWardrobeMapper.deleteByMainId(id);
		zyMallUserDepartMapper.deleteByMainId(id);
		zyMallBodyDataMapper.deleteByMainId(id);
		zyMallUserRoleMapper.deleteByMainId(id);
		zyMallReturnOrderMapper.deleteByMainId(id);
		zyMallUserMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			zyMallAddressMapper.deleteByMainId(id.toString());
			zyMallWardrobeMapper.deleteByMainId(id.toString());
			zyMallUserDepartMapper.deleteByMainId(id.toString());
			zyMallBodyDataMapper.deleteByMainId(id.toString());
			zyMallUserRoleMapper.deleteByMainId(id.toString());
			zyMallReturnOrderMapper.deleteByMainId(id.toString());
			zyMallUserMapper.deleteById(id);
		}
	}

	@Override
	public void updateBodyVo(BodyDataVo bodyDataVo) {
		zyMallUserMapper.updateBodyVo(bodyDataVo);
	}

}
