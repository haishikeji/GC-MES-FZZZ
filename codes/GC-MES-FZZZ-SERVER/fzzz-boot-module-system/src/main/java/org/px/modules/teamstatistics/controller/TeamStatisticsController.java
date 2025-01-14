package org.px.modules.teamstatistics.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.teamstatistics.entity.TeamStatistics;
import org.px.modules.teamstatistics.service.ITeamStatisticsService;

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
 * @Description: 班组绩效统计
 * @@author 品讯科技
 * @Date:   2021-10-30
 * @Version: V1.0
 */
@Api(tags="班组绩效统计")
@RestController
@RequestMapping("/teamstatistics/teamStatistics")
@Slf4j
public class TeamStatisticsController extends JeecgController<TeamStatistics, ITeamStatisticsService> {
	@Autowired
	private ITeamStatisticsService teamStatisticsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param teamStatistics
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "班组绩效统计-分页列表查询")
	@ApiOperation(value="班组绩效统计-分页列表查询", notes="班组绩效统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TeamStatistics teamStatistics,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<TeamStatistics> queryWrapper = QueryGenerator.initQueryWrapper(teamStatistics, req.getParameterMap());
		Page<TeamStatistics> page = new Page<TeamStatistics>(pageNo, pageSize);
		IPage<TeamStatistics> pageList = teamStatisticsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param teamStatistics
	 * @return
	 */
	@AutoLog(value = "班组绩效统计-添加")
	@ApiOperation(value="班组绩效统计-添加", notes="班组绩效统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TeamStatistics teamStatistics) {
		teamStatisticsService.save(teamStatistics);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param teamStatistics
	 * @return
	 */
	@AutoLog(value = "班组绩效统计-编辑")
	@ApiOperation(value="班组绩效统计-编辑", notes="班组绩效统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TeamStatistics teamStatistics) {
		teamStatisticsService.updateById(teamStatistics);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "班组绩效统计-通过id删除")
	@ApiOperation(value="班组绩效统计-通过id删除", notes="班组绩效统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		teamStatisticsService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "班组绩效统计-批量删除")
	@ApiOperation(value="班组绩效统计-批量删除", notes="班组绩效统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.teamStatisticsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "班组绩效统计-通过id查询")
	@ApiOperation(value="班组绩效统计-通过id查询", notes="班组绩效统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TeamStatistics teamStatistics = teamStatisticsService.getById(id);
		if(teamStatistics==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(teamStatistics);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param teamStatistics
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TeamStatistics teamStatistics) {
        return super.exportXls(request, teamStatistics, TeamStatistics.class, "班组绩效统计");
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
        return super.importExcel(request, response, TeamStatistics.class);
    }

}
