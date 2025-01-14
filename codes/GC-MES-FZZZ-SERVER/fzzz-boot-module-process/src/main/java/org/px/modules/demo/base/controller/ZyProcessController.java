package org.px.modules.demo.base.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.demo.accessories.entity.ZyAccessories;
import org.px.modules.demo.base.entity.ZyAllProcess;
import org.px.modules.demo.base.entity.ZyClothAction;
import org.px.modules.demo.base.entity.ZyProcess;
import org.px.modules.demo.base.service.IZyProcessService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.px.modules.demo.device.entity.ZyDevicetypel;
import org.px.modules.demo.device.service.IZyDevicetypeService1;
import org.px.modules.demo.fabric.entity.ZyFabric;
import org.px.modules.demo.pro.service.IZyProcessAccessoriesService;
import org.px.modules.demo.pro.service.IZyProcessActionService;
import org.px.modules.demo.pro.service.IZyProcessFabricService;
import org.px.modules.demo.tools.entity.ZyToolTypes;
import org.px.modules.demo.tools.service.IZyToolTypesService;
import org.px.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.px.common.aspect.annotation.AutoLog;

/**
 * @Description: zy_process
 * @@author 品讯科技
 * @Date: 2021-11-03
 * @Version: V1.0
 */
@Api(tags = "zy_process")
@RestController
@RequestMapping("/base/zyProcess")
@Slf4j
public class ZyProcessController extends JeecgController<ZyProcess, IZyProcessService> {
    @Autowired
    private IZyProcessService zyProcessService;
    @Autowired
    IZyProcessAccessoriesService zyProcessAccessoriesService;
    @Autowired
    IZyProcessActionService zyProcessActionService;
    @Autowired
    IZyProcessFabricService zyProcessFabricService;
    @Autowired
    private IZyDevicetypeService1 zyDevicetypeService;
    @Autowired
    private IZyToolTypesService zyToolTypesService;

    /**
     * 分页列表查询
     *
     * @param zyProcess
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "zy_process-分页列表查询")
    @ApiOperation(value = "zy_process-分页列表查询", notes = "zy_process-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ZyProcess zyProcess,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ZyProcess> queryWrapper = QueryGenerator.initQueryWrapper(zyProcess, req.getParameterMap());
        //QueryWrapper queryWrapper = new QueryWrapper();
        Page<ZyProcess> page = new Page<ZyProcess>(pageNo, pageSize);
        IPage<ZyProcess> pageList = zyProcessService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 服装款式之款式工序中的工序接口 分页列表查询
     * 根据服装类型筛选list中的数据
     */
    @GetMapping(value = "/listScreen")
    public Result<?> queryPageListScreen(ZyProcess zyProcess,
                                      @RequestParam(name = "clothStyleId", required = true) String clothStyleId,
                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                      HttpServletRequest req) {
        //根据clothStyleId 查到服装类型的编号 在根据编号进行筛选
        String nums = zyProcessService.screenList(clothStyleId);
        QueryWrapper<ZyProcess> queryWrapper = QueryGenerator.initQueryWrapper(zyProcess, req.getParameterMap());
        queryWrapper.eq(StringUtils.hasText(nums), "style_id", nums);
        Page<ZyProcess> page = new Page<ZyProcess>(pageNo, pageSize);
        IPage<ZyProcess> pageList = zyProcessService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 制衣模块之模块工序中的工序接口 分页列表查询
     * 根据服装类型筛选list中的数据
     */
    @GetMapping(value = "/listScreenTwo")
    public Result<?> queryPageListScreenTwo(ZyProcess zyProcess,
                                         @RequestParam(name = "clothsTypeId", required = true) String clothsTypeId,
                                         @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         HttpServletRequest req) {
        //根据clothsTypeId 查到服装类型的编号 在根据编号进行筛选
        String nums = zyProcessService.getNums(clothsTypeId);
        QueryWrapper<ZyProcess> queryWrappers = QueryGenerator.initQueryWrapper(zyProcess, req.getParameterMap());
        queryWrappers.eq(StringUtils.hasText(nums), "style_id", nums);
        Page<ZyProcess> page = new Page<>(pageNo, pageSize);
        IPage<ZyProcess> pageList = zyProcessService.page(page, queryWrappers);
        return Result.OK(pageList);
    }

    /**
     * 制衣部件之部件工序中的工序接口 分页列表查询
     * 根据服装类型筛选list中的数据
     */
    @GetMapping(value = "/listScreenThree")
    public Result<?> queryPageListScreenThree(ZyProcess zyProcess,
                                            @RequestParam(name = "clothsComponentId", required = true) String clothsComponentId,
                                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                            HttpServletRequest req) {
        //根据clothsTypeId 查到服装类型的编号 在根据编号进行筛选
        String nums = zyProcessService.getNumsByClothsComponentId(clothsComponentId);
        QueryWrapper<ZyProcess> queryWrappers = QueryGenerator.initQueryWrapper(zyProcess, req.getParameterMap());
        queryWrappers.eq(StringUtils.hasText(nums), "style_id", nums);
        Page<ZyProcess> page = new Page<>(pageNo, pageSize);
        IPage<ZyProcess> pageList = zyProcessService.page(page, queryWrappers);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param zyProcess
     * @return
     */
    @AutoLog(value = "zy_process-添加")
    @ApiOperation(value = "zy_process-添加", notes = "zy_process-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ZyProcess zyProcess) {
        zyProcess.setCreateTime(null);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("process_name", zyProcess.getProcessName());
        int a = zyProcessService.count(queryWrapper);
        if (a != 0)
            return Result.error("添加失败，该工序名称已存在！！！");
        zyProcessService.save(zyProcess);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zyProcess
     * @return
     */
    @AutoLog(value = "zy_process-编辑")
    @ApiOperation(value = "zy_process-编辑", notes = "zy_process-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ZyProcess zyProcess) {
        zyProcessService.updateById(zyProcess);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "zy_process-通过id删除")
    @ApiOperation(value = "zy_process-通过id删除", notes = "zy_process-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        zyProcessService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "zy_process-批量删除")
    @ApiOperation(value = "zy_process-批量删除", notes = "zy_process-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zyProcessService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "zy_process-通过id查询")
    @ApiOperation(value = "zy_process-通过id查询", notes = "zy_process-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ZyProcess zyProcess = zyProcessService.selectZyprocess(id);
        List<ZyAccessories> pageList = zyProcessAccessoriesService.selectByid(id);
        List<ZyClothAction> pageList1 = zyProcessActionService.selectByid(id);
        List<ZyFabric> pageList2 = zyProcessFabricService.selectByid(id);
        List<ZyDevicetypel> pageList3 = zyDevicetypeService.selectByid(id);
        List<ZyToolTypes> pageList4 = zyToolTypesService.selectByid(id);
        ZyAllProcess zyAllProcess = new ZyAllProcess();
        zyAllProcess.setZyProcess(zyProcess);
        zyAllProcess.setZyClothActions(pageList1);
        zyAllProcess.setZyAccessories(pageList);
        zyAllProcess.setZyFabrics(pageList2);
        zyAllProcess.setZyDevicetypes(pageList3);
        zyAllProcess.setZyToolTypes(pageList4);

        if (zyAllProcess == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(zyAllProcess);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zyProcess
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyProcess zyProcess) {
        return super.exportXls(request, zyProcess, ZyProcess.class, "zy_process");
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
        return super.importExcel(request, response, ZyProcess.class);
    }

    @AutoLog(value = "zy_process-顺序查询")
    @ApiOperation(value = "zy_process-顺序查询", notes = "zy_process-顺序查询")
    @GetMapping(value = "/sort")
    public Result<?> sort(@RequestParam(name = "id", required = true) String id) {
        String sort = zyProcessService.sort(id);
        if (sort == null) {
            return Result.ok(0);
        }
        return Result.OK(sort.substring(10, sort.length()));
    }

}
