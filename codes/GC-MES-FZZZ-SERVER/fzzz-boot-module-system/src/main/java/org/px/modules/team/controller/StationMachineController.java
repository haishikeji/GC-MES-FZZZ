package org.px.modules.team.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.px.common.api.vo.Result;
import org.px.common.aspect.annotation.AutoLog;
import org.px.common.system.base.controller.JeecgController;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.device.entity.ZyDevice;
import org.px.modules.device.service.IZyDeviceService;
import org.px.modules.team.entity.StationMachine;
import org.px.modules.team.service.IStationMachineService;
import org.px.modules.team.vo.StationMachineVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 工序设备管理
 * @@author 品讯科技
 * @Date: 2022-12-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "工序设备管理")
@RestController
@RequestMapping("/stationMachine")
public class StationMachineController extends JeecgController<StationMachine, IStationMachineService> {
    @Autowired
    private IStationMachineService stationMachineService;

    @Autowired
    private IZyDeviceService iZyDeviceService;

    /**
     * 分页列表查询
     *
     * @param stationMachine
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "工序设备管理-分页列表查询")
    @ApiOperation(value = "工序设备管理-分页列表查询", notes = "工序设备管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(StationMachine stationMachine,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<StationMachine> queryWrapper = QueryGenerator.initQueryWrapper(stationMachine, req.getParameterMap());
        Page<StationMachine> page = new Page<StationMachine>(pageNo, pageSize);
        IPage<StationMachine> pageList = stationMachineService.page(page, queryWrapper);
        List<StationMachine> records = pageList.getRecords();
        if (!ObjectUtils.isEmpty(records)) {
            records.forEach(e -> {
                ZyDevice zyDevice = iZyDeviceService.getById(e.getMachineId());
                if (!ObjectUtils.isEmpty(zyDevice)) {
                    e.setCode(StringUtils.isNotBlank(zyDevice.getCode()) ? zyDevice.getCode() : "");
                }

            });
        }
        return Result.OK(pageList);
    }

    @ApiOperation(value = "工序工具管理-分页列表查询", notes = "工序工具管理-分页列表查询")
    @GetMapping(value = "/list2")
    public Result<?> list2(StationMachine stationMachine,
                           HttpServletRequest req) {
        List<StationMachineVo> voList = new LinkedList<>();
        List<StationMachine> toolList = stationMachineService.list(new LambdaQueryWrapper<StationMachine>().eq(StationMachine::getStationId, stationMachine.getStationId()));
        if (!ObjectUtils.isEmpty(toolList)) {
            toolList.forEach(en -> {
                StationMachineVo stationMachineVo = new StationMachineVo();
                stationMachineVo.setId(en.getMachineId());
                stationMachineVo.setValue(en.getMachineId());
                ZyDevice zyDevice = iZyDeviceService.getById(en.getMachineId());
                stationMachineVo.setText(zyDevice.getName());
                stationMachineVo.setTitle(zyDevice.getName());
                voList.add(stationMachineVo);
            });
        }
        return Result.OK(voList);
    }

    /**
     * 添加
     *
     * @param stationMachine
     * @return
     */
    @AutoLog(value = "工序设备管理-添加")
    @ApiOperation(value = "工序设备管理-添加", notes = "工序设备管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody StationMachine stationMachine) {
        stationMachineService.save(stationMachine);
        return Result.OK("添加成功！");
    }

    @AutoLog(value = "工序设备管理-批量添加")
    @ApiOperation(value = "工序设备管理-批量添加", notes = "工序设备管理-批量添加")
    @PostMapping(value = "/addBatch")
    public Result<?> addBatch(@RequestBody StationMachine en) {
        String machineIds = en.getMachineId();
        List<StationMachine> addList = new LinkedList<>();
        if (StringUtils.isNotBlank(machineIds)) {
            List<String> list = Arrays.asList(machineIds.split(","));
            list.forEach(e -> {
                StationMachine s = new StationMachine();
                BeanUtils.copyProperties(en, s);
                s.setMachineId(e);
                addList.add(s);
            });
        }
        stationMachineService.saveBatch(addList);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param stationMachine
     * @return
     */
    @AutoLog(value = "工序设备管理-编辑")
    @ApiOperation(value = "工序设备管理-编辑", notes = "工序设备管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody StationMachine stationMachine) {
        stationMachineService.updateById(stationMachine);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工序设备管理-通过id删除")
    @ApiOperation(value = "工序设备管理-通过id删除", notes = "工序设备管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        stationMachineService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "工序设备管理-批量删除")
    @ApiOperation(value = "工序设备管理-批量删除", notes = "工序设备管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.stationMachineService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "工序设备管理-通过id查询")
    @ApiOperation(value = "工序设备管理-通过id查询", notes = "工序设备管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        StationMachine stationMachine = stationMachineService.getById(id);
        return Result.OK(stationMachine);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param stationMachine
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, StationMachine stationMachine) {
        return super.exportXls(request, stationMachine, StationMachine.class, "工序设备管理");
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
        return super.importExcel(request, response, StationMachine.class);
    }

}
