package org.px.modules.product.productModule.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.px.common.api.vo.Result;
import org.px.common.aspect.annotation.AutoLog;
import org.px.common.system.base.controller.JeecgController;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.product.productModule.entity.ProductModule;
import org.px.modules.product.productModule.service.IProductModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 工单管理下面的产品模块
 * @@author 品讯科技
 * @Date:   2023-01-05
 * @Version: V1.0
 */
@Api(tags="工单管理下面的产品模块")
@RestController
@RequestMapping("/productModule/productModule")
@Slf4j
public class ProductModuleController extends JeecgController<ProductModule, IProductModuleService> {
	@Autowired
	private IProductModuleService productModuleService;

	/**
	 * 分页列表查询
	 *
	 * @param productModule
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工单管理下面的产品模块-分页列表查询")
	@ApiOperation(value="工单管理下面的产品模块-分页列表查询", notes="工单管理下面的产品模块-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ProductModule productModule,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ProductModule> queryWrapper = QueryGenerator.initQueryWrapper(productModule, req.getParameterMap());
		Page<ProductModule> page = new Page<ProductModule>(pageNo, pageSize);
		IPage<ProductModule> pageList = productModuleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param productModule
	 * @return
	 */
	@AutoLog(value = "工单管理下面的产品模块-添加")
	@ApiOperation(value="工单管理下面的产品模块-添加", notes="工单管理下面的产品模块-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProductModule productModule) {
		productModuleService.save(productModule);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param productModule
	 * @return
	 */
	@AutoLog(value = "工单管理下面的产品模块-编辑")
	@ApiOperation(value="工单管理下面的产品模块-编辑", notes="工单管理下面的产品模块-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ProductModule productModule) {
		productModuleService.updateById(productModule);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工单管理下面的产品模块-通过id删除")
	@ApiOperation(value="工单管理下面的产品模块-通过id删除", notes="工单管理下面的产品模块-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		productModuleService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工单管理下面的产品模块-批量删除")
	@ApiOperation(value="工单管理下面的产品模块-批量删除", notes="工单管理下面的产品模块-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.productModuleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工单管理下面的产品模块-通过id查询")
	@ApiOperation(value="工单管理下面的产品模块-通过id查询", notes="工单管理下面的产品模块-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ProductModule productModule = productModuleService.getById(id);
		if(productModule==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(productModule);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param productModule
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProductModule productModule) {
        return super.exportXls(request, productModule, ProductModule.class, "工单管理下面的产品模块");
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
        return super.importExcel(request, response, ProductModule.class);
    }

}
