package org.px.modules.zystylemodule.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.px.modules.demo.base.entity.ZyClothsModular;
import org.px.modules.demo.base.service.IZyClothsModularService;
import org.px.modules.zyclothsstyle.service.IZyClothsStyleService;
import org.px.modules.zystylefabric.mapper.ZyStyleFabricMapper;
import org.px.modules.zystylemodule.entity.NewStyleModule;
import org.px.modules.zystylemodule.entity.ZyStyleModule;
import org.px.modules.zystylemodule.entity.ZyStyleModuleDetailVo;
import org.px.modules.zystylemodule.mapper.ZyStyleModuleMapper;
import org.px.modules.zystylemodule.service.IZyStyleModuleService;
import org.px.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.px.common.aspect.annotation.AutoLog;

/**
 * @Description: 款式模块表
 * @@author 品讯科技
 * @Date: 2022-09-08
 * @Version: V1.0
 */
@Api(tags = "款式模块表")
@RestController
@RequestMapping("/zystylemodule/zyStyleModule")
@Slf4j
public class ZyStyleModuleController extends JeecgController<ZyStyleModule, IZyStyleModuleService> {
    @Autowired
    private IZyStyleModuleService zyStyleModuleService;
    @Autowired
    private IZyClothsModularService zyClothsModularService;
    @Autowired
    private IZyClothsStyleService zyClothsStyleService;

    @Autowired
    private ZyStyleFabricMapper zyStyleFabricMapper;
    @Autowired
    private ZyStyleModuleMapper zyStyleModuleMapper;

    /**
     * 分页列表查询
     *
     * @param zyStyleModule2
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "款式模块表-分页列表查询")
    @ApiOperation(value = "款式模块表-分页列表查询", notes = "款式模块表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ZyStyleModule zyStyleModule2,
                                   @RequestParam(name = "id", required = false) String id,
                                   @RequestParam(name = "modularId", required = false) String modularId,
                                   @RequestParam(name = "modularNums", required = false) String modularNums,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        //System.err.println("ZyStyleModule 款式模块表 传来的 " + id);
        QueryWrapper<ZyStyleModule> queryWrapper = new QueryWrapper<>();
        //从服装款式进去款式模块时 根据style_id进行筛选 所以一般都会有id即style_id
        queryWrapper.eq(StringUtils.hasText(id), "style_id", id);
        queryWrapper.eq(StringUtils.hasText(modularId), "modular_id", modularId);
        Page<ZyStyleModule> page = new Page<>(pageNo, pageSize);
        IPage<ZyStyleModule> pageList = zyStyleModuleService.page(page, queryWrapper);
        if (StringUtils.hasText(modularNums)){
            LambdaQueryWrapper<ZyClothsModular> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.like( ZyClothsModular::getNums,modularNums);
            List<ZyClothsModular> list = zyClothsModularService.list(queryWrapper2);
            if (ObjectUtils.isEmpty(list)){
                IPage<NewStyleModule> pageList2 = new Page<>(pageNo, pageSize, 0);
                return Result.OK(pageList2);
            }
            List<ZyStyleModule> zyStyleModules = new ArrayList<>();
            for (ZyClothsModular zy :list) {
                List<ZyStyleModule> zyStyleModulesList = zyStyleModuleMapper.getListByModularId(zy.getId());
                zyStyleModules.addAll(zyStyleModulesList);
            }
            //将新查出来的集合在根据styleid 筛选一下
            List<ZyStyleModule> collect = zyStyleModules.stream().filter(ZyStyleModule -> ZyStyleModule.getStyleId().equals(id)).collect(Collectors.toList());
            pageList.setRecords(collect);
        }
        //List<ZyStyleModule> zyStyleModules = zyStyleModuleMapper.getListByStyleId(id);
        //改变list中的实体类 类型
        List<NewStyleModule> newStyleModuleList = zyStyleModuleService.changeList(pageList.getRecords());

        if (!ObjectUtils.isEmpty(newStyleModuleList)){
            IPage<NewStyleModule> pageList2 = new Page<>(pageNo, pageSize, newStyleModuleList.size());
            pageList2.setRecords(newStyleModuleList);
            return Result.OK(pageList2);
        } else {
            IPage<NewStyleModule> pageList2 = new Page<>(pageNo, pageSize, 0);
            return Result.OK(pageList2);
        }
    }


//        if (id != null) {
//            queryWrapper.eq("style_id", id);
//        }
//        if (zyStyleModule2.getStyleId() != null || zyStyleModule2.getModularId() != null || zyStyleModule2.getTypeId() != null) {
//            if (zyStyleModule2.getStyleId() != null) {
//                queryWrapper.eq("style_id", zyStyleModule2.getStyleId());
//            }
//            if (zyStyleModule2.getModularId() != null) {
//                queryWrapper.eq("modular_id", zyStyleModule2.getModularId());
//            }
//            if (zyStyleModule2.getTypeId() != null) {
//                queryWrapper.eq("type_id", zyStyleModule2.getTypeId());
//            }
//        }
//        List<ZyStyleModule> list = zyStyleModuleService.list(queryWrapper);
//        for (ZyStyleModule zyStyleModule : list) {


//           listNewStyleModules.add(newSeMo);
//        }


    /**
     * 分页列表查询
     *
     * @param zyStyleModule
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "款式模块表-分页列表查询")
    @ApiOperation(value = "款式模块表-分页列表查询", notes = "款式模块表-分页列表查询")
    @GetMapping(value = "/list1")
    public Result<IPage<ZyStyleModule>> queryPageList1(ZyStyleModule zyStyleModule,
                                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                       HttpServletRequest req) {

        QueryWrapper<ZyStyleModule> queryWrapper = QueryGenerator.initQueryWrapper(zyStyleModule, req.getParameterMap());
        Page<ZyStyleModule> page = new Page<ZyStyleModule>(pageNo, pageSize);

        IPage<ZyStyleModule> pageList = zyStyleModuleService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 详情
     */
    @AutoLog(value = "款式模块表-详情")
    @ApiOperation(value = "款式模块表-详情", notes = "款式模块表-详情")
    @GetMapping(value = "/detail")
    public Result<?> detail(@RequestParam(name = "id", required = true) String id) {
        //System.out.println("款式模块表 /detail controller层接受到的id: : " + id);
        ZyStyleModuleDetailVo detailVo = zyStyleModuleService.queryDetail(id);
        return Result.OK(detailVo);
    }

    /**
     * 添加
     *
     * @param zyStyleModule
     * @return
     */
    @AutoLog(value = "款式模块表-添加")
    @ApiOperation(value = "款式模块表-添加", notes = "款式模块表-添加")
    //@RequiresPermissions("org.px.modules.demo:zy_style_module:add")
    @PostMapping(value = "/add")
    public Result<Object> add(@RequestBody ZyStyleModule zyStyleModule) {
        String[] split = zyStyleModule.getModularId().split(",");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type_id", zyStyleModule.getTypeId());
        queryWrapper.eq("style_id", zyStyleModule.getStyleId());
        for (String s : split) {
            zyStyleModule.setModularId(s);
            zyStyleModule.setId(null);
            queryWrapper.eq("modular_id", zyStyleModule.getModularId());
            int a = zyStyleModuleService.count(queryWrapper);
            if (a >= 1)
                return Result.error("存在以添加过的数据！！！");
            zyStyleModuleService.save(zyStyleModule);
        }

        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zyStyleModule
     * @return
     */
    @AutoLog(value = "款式模块表-编辑")
    @ApiOperation(value = "款式模块表-编辑", notes = "款式模块表-编辑")
    //@RequiresPermissions("org.px.modules.demo:zy_style_module:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<Object> edit(@RequestBody ZyStyleModule zyStyleModule) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type_id", zyStyleModule.getTypeId());
        queryWrapper.eq("style_id", zyStyleModule.getStyleId());
        queryWrapper.eq("modular_id", zyStyleModule.getModularId());
        int a = zyStyleModuleService.count(queryWrapper);
        if (a >= 1)
            return Result.error("修改失败，数据已存在！！！");
        zyStyleModuleService.updateById(zyStyleModule);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "款式模块表-通过id删除")
    @ApiOperation(value = "款式模块表-通过id删除", notes = "款式模块表-通过id删除")
    //@RequiresPermissions("org.px.modules.demo:zy_style_module:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        zyStyleModuleService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "款式模块表-批量删除")
    @ApiOperation(value = "款式模块表-批量删除", notes = "款式模块表-批量删除")
    //@RequiresPermissions("org.px.modules.demo:zy_style_module:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zyStyleModuleService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "款式模块表-通过id查询")
    @ApiOperation(value = "款式模块表-通过id查询", notes = "款式模块表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ZyStyleModule zyStyleModule = zyStyleModuleService.getById(id);
        if (zyStyleModule == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(zyStyleModule);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zyStyleModule
     */
    //@RequiresPermissions("org.px.modules.demo:zy_style_module:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyStyleModule zyStyleModule) {
        return super.exportXls(request, zyStyleModule, ZyStyleModule.class, "款式模块表");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("zy_style_module:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZyStyleModule.class);
    }

}
