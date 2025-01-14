package org.px.modules.demo.mall.controller;

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
import org.px.common.util.oConvertUtils;
import org.px.modules.demo.mall.entity.ZyPosture;
import org.px.modules.demo.mall.service.IZyPostureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 体态表
 * @@author 品讯科技
 * @Date:   2021-10-23
 * @Version: V1.0
 */
@Api(tags="体态表")
@RestController
@RequestMapping("/mall/zyPosture")
@Slf4j
public class ZyPostureController extends JeecgController<ZyPosture, IZyPostureService>{
	@Autowired
	private IZyPostureService zyPostureService;

	/**
	 * 分页列表查询
	 *
	 * @param zyPosture
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "体态表-分页列表查询")
	@ApiOperation(value="体态表-分页列表查询", notes="体态表-分页列表查询")
	@GetMapping(value = "/rootList")
	public Result<?> queryPageList(ZyPosture zyPosture,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		String hasQuery = req.getParameter("hasQuery");
        if(hasQuery != null && "true".equals(hasQuery)){
            QueryWrapper<ZyPosture> queryWrapper =  QueryGenerator.initQueryWrapper(zyPosture, req.getParameterMap());
            List<ZyPosture> list = zyPostureService.queryTreeListNoPage(queryWrapper);
            IPage<ZyPosture> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        }else{
            String parentId = zyPosture.getPid();
            if (oConvertUtils.isEmpty(parentId)) {
                parentId = "0";
            }
            zyPosture.setPid(null);
            QueryWrapper<ZyPosture> queryWrapper = QueryGenerator.initQueryWrapper(zyPosture, req.getParameterMap());
            // 使用 eq 防止模糊查询
            queryWrapper.eq("pid", parentId);
            Page<ZyPosture> page = new Page<ZyPosture>(pageNo, pageSize);
            IPage<ZyPosture> pageList = zyPostureService.page(page, queryWrapper);
            return Result.OK(pageList);
        }
	}

	 /**
      * 获取子数据
      * @param zyPosture
      * @param req
      * @return
      */
	@AutoLog(value = "体态表-获取子数据")
	@ApiOperation(value="体态表-获取子数据", notes="体态表-获取子数据")
	@GetMapping(value = "/childList")
	public Result<?> queryPageList(ZyPosture zyPosture,HttpServletRequest req) {
		QueryWrapper<ZyPosture> queryWrapper = QueryGenerator.initQueryWrapper(zyPosture, req.getParameterMap());
		List<ZyPosture> list = zyPostureService.list(queryWrapper);
		IPage<ZyPosture> pageList = new Page<>(1, 10, list.size());
        pageList.setRecords(list);
		return Result.OK(pageList);
	}

    /**
      * 批量查询子节点
      * @param parentIds 父ID（多个采用半角逗号分割）
      * @return 返回 IPage
      * @param parentIds
      * @return
      */
	@AutoLog(value = "体态表-批量获取子数据")
    @ApiOperation(value="体态表-批量获取子数据", notes="体态表-批量获取子数据")
    @GetMapping("/getChildListBatch")
    public Result getChildListBatch(@RequestParam("parentIds") String parentIds) {
        try {
            QueryWrapper<ZyPosture> queryWrapper = new QueryWrapper<>();
            List<String> parentIdList = Arrays.asList(parentIds.split(","));
            queryWrapper.in("pid", parentIdList);
            List<ZyPosture> list = zyPostureService.list(queryWrapper);
            IPage<ZyPosture> pageList = new Page<>(1, 10, list.size());
            pageList.setRecords(list);
            return Result.OK(pageList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("批量查询子节点失败：" + e.getMessage());
        }
    }

	/**
	 *   添加
	 *
	 * @param zyPosture
	 * @return
	 */
	@AutoLog(value = "体态表-添加")
	@ApiOperation(value="体态表-添加", notes="体态表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyPosture zyPosture) {
		zyPostureService.addZyPosture(zyPosture);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyPosture
	 * @return
	 */
	@AutoLog(value = "体态表-编辑")
	@ApiOperation(value="体态表-编辑", notes="体态表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyPosture zyPosture) {
		zyPostureService.updateZyPosture(zyPosture);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "体态表-通过id删除")
	@ApiOperation(value="体态表-通过id删除", notes="体态表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyPostureService.deleteZyPosture(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "体态表-批量删除")
	@ApiOperation(value="体态表-批量删除", notes="体态表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyPostureService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "体态表-通过id查询")
	@ApiOperation(value="体态表-通过id查询", notes="体态表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyPosture zyPosture = zyPostureService.getById(id);
		if(zyPosture==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyPosture);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyPosture
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyPosture zyPosture) {
		return super.exportXls(request, zyPosture, ZyPosture.class, "体态表");
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
		return super.importExcel(request, response, ZyPosture.class);
    }

}
