package org.px.modules.devicequalifying.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.demo.base.entity.ZyProcess;
import org.px.modules.demo.base.service.IZyProcessService;
import org.px.modules.device.entity.ZyDevice;
import org.px.modules.device.service.IZyDeviceService;
import org.px.modules.devicequalifying.entity.ZyDevicequalifyinglist;
import org.px.modules.devicequalifying.service.IZyDevicequalifyinglistService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.px.modules.system.entity.SysUser;
import org.px.modules.system.service.ISysUserService;
import org.px.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.px.common.aspect.annotation.AutoLog;

 /**
 * @Description: 工艺排位
 * @@author 品讯科技
 * @Date:   2021-11-17
 * @Version: V1.0
 */
@Api(tags="工艺排位")
@RestController
@RequestMapping("/devicequalifying/zyDevicequalifyinglist")
@Slf4j
public class ZyDevicequalifyinglistController extends JeecgController<ZyDevicequalifyinglist, IZyDevicequalifyinglistService> {
	@Autowired
	private IZyDevicequalifyinglistService zyDevicequalifyinglistService;
	 @Autowired
	 private IZyProcessService zyProcessService;
	 @Autowired
	 private ISysUserService sysUserService;
	 @Autowired
	 private IZyDeviceService zyDeviceService;
	/**
	 * 分页列表查询
	 *
	 * @param zyDevicequalifyinglist
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工艺排位-分页列表查询")
	@ApiOperation(value="工艺排位-分页列表查询", notes="工艺排位-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyDevicequalifyinglist zyDevicequalifyinglist,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyDevicequalifyinglist> queryWrapper = QueryGenerator.initQueryWrapper(zyDevicequalifyinglist, req.getParameterMap());
		Page<ZyDevicequalifyinglist> page = new Page<ZyDevicequalifyinglist>(pageNo, pageSize);
		IPage<ZyDevicequalifyinglist> pageList = zyDevicequalifyinglistService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param zyDevicequalifyinglist
	 * @return
	 */
	@AutoLog(value = "工艺排位-添加")
	@ApiOperation(value="工艺排位-添加", notes="工艺排位-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyDevicequalifyinglist zyDevicequalifyinglist) {
		ZyProcess zyProcess = zyProcessService.selectZyprocess(zyDevicequalifyinglist.getTypeid());
		SysUser user = sysUserService.getUserByName(zyDevicequalifyinglist.getPeopleid());
		ZyDevice device = zyDeviceService.getById(zyDevicequalifyinglist.getDeviceid());
		zyDevicequalifyinglist.setTypename(zyProcess.getProcessName());
		zyDevicequalifyinglist.setPeoplename(user.getRealname());
		zyDevicequalifyinglist.setDevicecode(device.getCode());
		zyDevicequalifyinglistService.save(zyDevicequalifyinglist);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyDevicequalifyinglist
	 * @return
	 */
	@AutoLog(value = "工艺排位-编辑")
	@ApiOperation(value="工艺排位-编辑", notes="工艺排位-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyDevicequalifyinglist zyDevicequalifyinglist) {
		ZyProcess zyProcess = zyProcessService.selectZyprocess(zyDevicequalifyinglist.getTypeid());
		SysUser user = sysUserService.getUserByName(zyDevicequalifyinglist.getPeopleid());
		ZyDevice device = zyDeviceService.getById(zyDevicequalifyinglist.getDeviceid());
		zyDevicequalifyinglist.setTypename(zyProcess.getProcessName());
		zyDevicequalifyinglist.setPeoplename(user.getRealname());
		zyDevicequalifyinglist.setDevicecode(device.getCode());
		zyDevicequalifyinglistService.updateById(zyDevicequalifyinglist);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工艺排位-通过id删除")
	@ApiOperation(value="工艺排位-通过id删除", notes="工艺排位-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyDevicequalifyinglistService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工艺排位-批量删除")
	@ApiOperation(value="工艺排位-批量删除", notes="工艺排位-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyDevicequalifyinglistService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工艺排位-通过id查询")
	@ApiOperation(value="工艺排位-通过id查询", notes="工艺排位-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyDevicequalifyinglist zyDevicequalifyinglist = zyDevicequalifyinglistService.getById(id);
		if(zyDevicequalifyinglist==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyDevicequalifyinglist);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyDevicequalifyinglist
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyDevicequalifyinglist zyDevicequalifyinglist) {
        return super.exportXls(request, zyDevicequalifyinglist, ZyDevicequalifyinglist.class, "工艺排位");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZyDevicequalifyinglist.class);
    }

}
