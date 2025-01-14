package org.px.modules.demo.base.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.demo.base.entity.ZyClothAction;
import org.px.modules.demo.base.service.IZyClothActionService;

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
 * @Description: zy_cloth_action
 * @@author 品讯科技
 * @Date:   2021-11-10
 * @Version: V1.0
 */
@Api(tags="zy_cloth_action")
@RestController
@RequestMapping("/base/zyClothAction")
@Slf4j
public class ZyClothActionController extends JeecgController<ZyClothAction, IZyClothActionService> {
	@Autowired
	private IZyClothActionService zyClothActionService;

	/**
	 * 分页列表查询
	 *
	 * @param zyClothAction
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "zy_cloth_action-分页列表查询")
	@ApiOperation(value="zy_cloth_action-分页列表查询", notes="zy_cloth_action-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyClothAction zyClothAction,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyClothAction> queryWrapper = QueryGenerator.initQueryWrapper(zyClothAction, req.getParameterMap());
		Page<ZyClothAction> page = new Page<ZyClothAction>(pageNo, pageSize);
		IPage<ZyClothAction> pageList = zyClothActionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param zyClothAction
	 * @return
	 */
	@AutoLog(value = "zy_cloth_action-添加")
	@ApiOperation(value="zy_cloth_action-添加", notes="zy_cloth_action-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyClothAction zyClothAction) {
		zyClothActionService.save(zyClothAction);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyClothAction
	 * @return
	 */
	@AutoLog(value = "zy_cloth_action-编辑")
	@ApiOperation(value="zy_cloth_action-编辑", notes="zy_cloth_action-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyClothAction zyClothAction) {
		zyClothActionService.updateById(zyClothAction);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zy_cloth_action-通过id删除")
	@ApiOperation(value="zy_cloth_action-通过id删除", notes="zy_cloth_action-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyClothActionService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "zy_cloth_action-批量删除")
	@ApiOperation(value="zy_cloth_action-批量删除", notes="zy_cloth_action-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyClothActionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "zy_cloth_action-通过id查询")
	@ApiOperation(value="zy_cloth_action-通过id查询", notes="zy_cloth_action-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyClothAction zyClothAction = zyClothActionService.getById(id);
		if(zyClothAction==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyClothAction);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyClothAction
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyClothAction zyClothAction) {
        return super.exportXls(request, zyClothAction, ZyClothAction.class, "zy_cloth_action");
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
        return super.importExcel(request, response, ZyClothAction.class);
    }
	@GetMapping(value = "/sort")
	 public Result<?> sort(){
		String s=zyClothActionService.sort();
		return Result.ok(s.substring(1));
	}

}
