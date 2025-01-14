package org.px.modules.zyworkoperation.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.zyworkoperation.entity.ZyWorkOperation;
import org.px.modules.zyworkoperation.service.IZyWorkOperationService;

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
 * @Description: 工单工艺
 * @@author 品讯科技
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@Api(tags="工单工艺")
@RestController
@RequestMapping("/zyworkoperation/zyWorkOperation")
@Slf4j
public class ZyWorkOperationController extends JeecgController<ZyWorkOperation, IZyWorkOperationService> {
	@Autowired
	private IZyWorkOperationService zyWorkOperationService;

	/**
	 * 分页列表查询
	 *
	 * @param zyWorkOperation
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工单工艺-分页列表查询")
	@ApiOperation(value="工单工艺-分页列表查询", notes="工单工艺-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyWorkOperation zyWorkOperation,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyWorkOperation> queryWrapper = QueryGenerator.initQueryWrapper(zyWorkOperation, req.getParameterMap());
		Page<ZyWorkOperation> page = new Page<ZyWorkOperation>(pageNo, pageSize);
		IPage<ZyWorkOperation> pageList = zyWorkOperationService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param zyWorkOperation
	 * @return
	 */
	@AutoLog(value = "工单工艺-添加")
	@ApiOperation(value="工单工艺-添加", notes="工单工艺-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyWorkOperation zyWorkOperation) {
		zyWorkOperationService.save(zyWorkOperation);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyWorkOperation
	 * @return
	 */
	@AutoLog(value = "工单工艺-编辑")
	@ApiOperation(value="工单工艺-编辑", notes="工单工艺-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyWorkOperation zyWorkOperation) {
		zyWorkOperationService.updateById(zyWorkOperation);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工单工艺-通过id删除")
	@ApiOperation(value="工单工艺-通过id删除", notes="工单工艺-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyWorkOperationService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工单工艺-批量删除")
	@ApiOperation(value="工单工艺-批量删除", notes="工单工艺-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyWorkOperationService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工单工艺-通过id查询")
	@ApiOperation(value="工单工艺-通过id查询", notes="工单工艺-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyWorkOperation zyWorkOperation = zyWorkOperationService.getById(id);
		if(zyWorkOperation==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyWorkOperation);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyWorkOperation
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyWorkOperation zyWorkOperation) {
        return super.exportXls(request, zyWorkOperation, ZyWorkOperation.class, "工单工艺");
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
        return super.importExcel(request, response, ZyWorkOperation.class);
    }

}
