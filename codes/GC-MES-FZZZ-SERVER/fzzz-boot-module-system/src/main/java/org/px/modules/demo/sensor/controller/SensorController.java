package org.px.modules.demo.sensor.controller;



import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.demo.sensor.entity.Sensor;
import org.px.modules.demo.sensor.service.ISensorService;

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
 * @Description: 传感器表
 * @@author 品讯科技
 * @Date:   2023-02-10
 * @Version: V1.0
 */
@Api(tags="传感器表")
@RestController
@RequestMapping("/sensor/sensor")
@Slf4j
public class SensorController extends JeecgController<Sensor, ISensorService> {
	@Autowired
	private ISensorService sensorService;

	/**
	 * 分页列表查询
	 *
	 * @param sensor
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "传感器表-分页列表查询")
	@ApiOperation(value="传感器表-分页列表查询", notes="传感器表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Sensor sensor,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<Sensor> queryWrapper = QueryGenerator.initQueryWrapper(sensor, req.getParameterMap());
		Page<Sensor> page = new Page<Sensor>(pageNo, pageSize);
		queryWrapper.orderByAsc("create_time");
		IPage<Sensor> pageList = sensorService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param sensor
	 * @return
	 */
	@AutoLog(value = "传感器表-添加")
	@ApiOperation(value="传感器表-添加", notes="传感器表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Sensor sensor) {
		sensorService.save(sensor);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param sensor
	 * @return
	 */
	@AutoLog(value = "传感器表-编辑")
	@ApiOperation(value="传感器表-编辑", notes="传感器表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Sensor sensor) {
		sensorService.updateById(sensor);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "传感器表-通过id删除")
	@ApiOperation(value="传感器表-通过id删除", notes="传感器表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sensorService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "传感器表-批量删除")
	@ApiOperation(value="传感器表-批量删除", notes="传感器表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sensorService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "传感器表-通过id查询")
	@ApiOperation(value="传感器表-通过id查询", notes="传感器表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Sensor sensor = sensorService.getById(id);
		if(sensor==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sensor);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param sensor
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Sensor sensor) {
        return super.exportXls(request, sensor, Sensor.class, "传感器表");
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
        return super.importExcel(request, response, Sensor.class);
    }

}
