package org.px.modules.hanger.controller;

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
import org.px.modules.hanger.entity.HangRecord;
import org.px.modules.hanger.service.IHangRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 吊挂运行记录表
 * @@author 品讯科技
 * @Date: 2023-02-06
 * @Version: V1.0
 */
@Api(tags = "吊挂运行记录表")
@RestController
@RequestMapping("/org.px.modules.hanger/hangRecord")
@Slf4j
public class HangRecordController extends JeecgController<HangRecord, IHangRecordService> {
    @Autowired
    private IHangRecordService hangRecordService;

    /**
     * 分页列表查询
     *
     * @param hangRecord
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "吊挂运行记录表-分页列表查询")
    @ApiOperation(value = "吊挂运行记录表-分页列表查询", notes = "吊挂运行记录表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(HangRecord hangRecord,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<HangRecord> queryWrapper = QueryGenerator.initQueryWrapper(hangRecord, req.getParameterMap());
        Page<HangRecord> page = new Page<HangRecord>(pageNo, pageSize);
        IPage<HangRecord> pageList = hangRecordService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 根据吊挂编号-增量同步吊挂运行记录
     * @param hangRecord
     * @return
     */
    @ApiOperation(value = "吊挂工位表-同步生产记录表数据到吊挂运行记录表", notes = "吊挂工位表-同步生产记录表数据到吊挂运行记录表")
    @PutMapping(value = "/syncHangRecord")
    public Result<?> syncHangRecord(@RequestBody HangRecord hangRecord) {
        hangRecordService.syncHangRecord(hangRecord);
        return Result.OK();
    }

    /**
     * 添加
     *
     * @param hangRecord
     * @return
     */
    @AutoLog(value = "吊挂运行记录表-添加")
    @ApiOperation(value = "吊挂运行记录表-添加", notes = "吊挂运行记录表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody HangRecord hangRecord) {
        hangRecordService.save(hangRecord);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param hangRecord
     * @return
     */
    @AutoLog(value = "吊挂运行记录表-编辑")
    @ApiOperation(value = "吊挂运行记录表-编辑", notes = "吊挂运行记录表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody HangRecord hangRecord) {
        hangRecordService.updateById(hangRecord);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "吊挂运行记录表-通过id删除")
    @ApiOperation(value = "吊挂运行记录表-通过id删除", notes = "吊挂运行记录表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        hangRecordService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "吊挂运行记录表-批量删除")
    @ApiOperation(value = "吊挂运行记录表-批量删除", notes = "吊挂运行记录表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.hangRecordService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "吊挂运行记录表-通过id查询")
    @ApiOperation(value = "吊挂运行记录表-通过id查询", notes = "吊挂运行记录表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        HangRecord hangRecord = hangRecordService.getById(id);
        if (hangRecord == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(hangRecord);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param hangRecord
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HangRecord hangRecord) {
        return super.exportXls(request, hangRecord, HangRecord.class, "吊挂运行记录表");
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
        return super.importExcel(request, response, HangRecord.class);
    }

}
