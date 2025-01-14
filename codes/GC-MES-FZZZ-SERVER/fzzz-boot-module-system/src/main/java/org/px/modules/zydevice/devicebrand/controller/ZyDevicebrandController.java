package org.px.modules.zydevice.devicebrand.controller;



import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.px.modules.zydevice.devicebrand.entity.ZyDevicebrand;
import org.px.modules.zydevice.devicebrand.service.IZyDevicebrandService;
import org.px.modules.zydevice.devicemodel.service.IZyDevicemodelService;
import org.px.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.px.common.aspect.annotation.AutoLog;

 /**
 * @Description: 设备品牌管理
 * @@author 品讯科技
 * @Date:   2023-01-05
 * @Version: V1.0
 */
@Api(tags="设备品牌管理")
@RestController
@RequestMapping("/devicebrand/zyDevicebrand")
@Slf4j
public class ZyDevicebrandController extends JeecgController<ZyDevicebrand, IZyDevicebrandService> {
	@Autowired
	private IZyDevicebrandService zyDevicebrandService;
	 @Autowired
	 private IZyDevicemodelService zyDevicemodelService;

	 /**
	 * 分页列表查询
	 *
	 * @param zyDevicebrand
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备品牌管理-分页列表查询")
	@ApiOperation(value="设备品牌管理-分页列表查询", notes="设备品牌管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyDevicebrand zyDevicebrand,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyDevicebrand> queryWrapper = QueryGenerator.initQueryWrapper(zyDevicebrand, req.getParameterMap());
		Page<ZyDevicebrand> page = new Page<ZyDevicebrand>(pageNo, pageSize);
		IPage<ZyDevicebrand> pageList = zyDevicebrandService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param zyDevicebrand
	 * @return
	 */
	@AutoLog(value = "设备品牌管理-添加")
	@ApiOperation(value="设备品牌管理-添加", notes="设备品牌管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyDevicebrand zyDevicebrand) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("brand",zyDevicebrand.getBrand());
		int a=zyDevicebrandService.count(queryWrapper);
		if(a==0){
			zyDevicebrandService.save(zyDevicebrand);
			return Result.OK("添加成功！");
		}else{
			return Result.error("添加失败，品牌名重复！！！");
		}

	}
	
	/**
	 *  编辑
	 *
	 * @param zyDevicebrand
	 * @return
	 */
	@AutoLog(value = "设备品牌管理-编辑")
	@ApiOperation(value="设备品牌管理-编辑", notes="设备品牌管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyDevicebrand zyDevicebrand) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("brand",zyDevicebrand.getBrand());
		int a=zyDevicebrandService.count(queryWrapper);
		if(a<2){
			zyDevicebrandService.updateById(zyDevicebrand);
			return Result.OK("编辑成功！");
		}else{
			return Result.error("编辑失败，品牌名重复！！！");
		}
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备品牌管理-通过id删除")
	@ApiOperation(value="设备品牌管理-通过id删除", notes="设备品牌管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("brand_id",id);
		int a=zyDevicemodelService.count(queryWrapper);
		if(a>=0){
			return  Result.error("本品牌已被使用，删除失败！！");
		}
		zyDevicebrandService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备品牌管理-批量删除")
	@ApiOperation(value="设备品牌管理-批量删除", notes="设备品牌管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyDevicebrandService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备品牌管理-通过id查询")
	@ApiOperation(value="设备品牌管理-通过id查询", notes="设备品牌管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyDevicebrand zyDevicebrand = zyDevicebrandService.getById(id);
		if(zyDevicebrand==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyDevicebrand);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyDevicebrand
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyDevicebrand zyDevicebrand) {
        return super.exportXls(request, zyDevicebrand, ZyDevicebrand.class, "设备品牌管理");
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
        return super.importExcel(request, response, ZyDevicebrand.class);
    }

}
