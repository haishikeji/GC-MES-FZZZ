package org.px.modules.warehousequery.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.warehousequery.entity.WarehouseQuery;
import org.px.modules.warehousequery.service.IWarehouseQueryService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.px.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.px.common.aspect.annotation.AutoLog;

 /**
 * @Description: 仓库查询
 * @@author 品讯科技
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@Api(tags="仓库查询")
@RestController
@RequestMapping("/warehousequery/warehouseQuery")
@Slf4j
public class WarehouseQueryController extends JeecgController<WarehouseQuery, IWarehouseQueryService> {
	@Autowired
	private IWarehouseQueryService warehouseQueryService;

	/**
	 * 分页列表查询
	 *
	 * @param warehouseQuery
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "仓库查询-分页列表查询")
	@ApiOperation(value="仓库查询-分页列表查询", notes="仓库查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(WarehouseQuery warehouseQuery,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<WarehouseQuery> queryWrapper = QueryGenerator.initQueryWrapper(warehouseQuery, req.getParameterMap());
		Page<WarehouseQuery> page = new Page<WarehouseQuery>(pageNo, pageSize);
		IPage<WarehouseQuery> pageList = warehouseQueryService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param warehouseQuery
	 * @return
	 */
	@AutoLog(value = "仓库查询-添加")
	@ApiOperation(value="仓库查询-添加", notes="仓库查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WarehouseQuery warehouseQuery) {
		warehouseQueryService.save(warehouseQuery);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param warehouseQuery
	 * @return
	 */
	@AutoLog(value = "仓库查询-编辑")
	@ApiOperation(value="仓库查询-编辑", notes="仓库查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody WarehouseQuery warehouseQuery) {
		warehouseQueryService.updateById(warehouseQuery);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "仓库查询-通过id删除")
	@ApiOperation(value="仓库查询-通过id删除", notes="仓库查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		warehouseQueryService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "仓库查询-批量删除")
	@ApiOperation(value="仓库查询-批量删除", notes="仓库查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.warehouseQueryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "仓库查询-通过id查询")
	@ApiOperation(value="仓库查询-通过id查询", notes="仓库查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WarehouseQuery warehouseQuery = warehouseQueryService.getById(id);
		if(warehouseQuery==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(warehouseQuery);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param warehouseQuery
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WarehouseQuery warehouseQuery) {
        return super.exportXls(request, warehouseQuery, WarehouseQuery.class, "仓库查询");
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
        return super.importExcel(request, response, WarehouseQuery.class);
    }

}
