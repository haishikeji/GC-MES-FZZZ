package org.px.modules.demo.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.px.modules.demo.base.entity.ZyClothsModular;
import org.px.modules.demo.base.entity.ZyClothsType;
import org.px.modules.demo.base.entity.vo.ZyClothsModularDetail;
import org.px.modules.demo.base.service.IZyClothsModularService;
import org.px.modules.demo.base.service.IZyClothsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: zy_cloths_modular
 * @@author 品讯科技
 * @Date: 2021-11-10
 * @Version: V1.0
 */
@Api(tags = "zy_cloths_modular")
@RestController
@RequestMapping("/base/zyClothsModular")
@Slf4j
public class ZyClothsModularController extends JeecgController<ZyClothsModular, IZyClothsModularService> {
    @Autowired
    private IZyClothsModularService zyClothsModularService;
    @Autowired
    private IZyClothsTypeService zyClothsTypeService;


    /**
     * 分页列表查询
     *
     * @param zyClothsModular
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "zy_cloths_modular-分页列表查询")
    @ApiOperation(value = "zy_cloths_modular-分页列表查询", notes = "zy_cloths_modular-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ZyClothsModular zyClothsModular,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ZyClothsModular> queryWrapper = QueryGenerator.initQueryWrapper(zyClothsModular, req.getParameterMap());
        Page<ZyClothsModular> page = new Page<ZyClothsModular>(pageNo, pageSize);
        IPage<ZyClothsModular> pageList = zyClothsModularService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 服装款式之款式模块中的模块列表查询接口
     */
    @GetMapping(value = "/listAnother")
    public Result<?> queryList(ZyClothsModular zyClothsModular,
                                   @RequestParam(name = "clothsTypeId", required = true) String clothsTypeId,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        //System.out.println("clothsTypeId "+clothsTypeId);
        LambdaQueryWrapper<ZyClothsModular> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(clothsTypeId), ZyClothsModular::getClothsTypeId, clothsTypeId);
        Page<ZyClothsModular> page = new Page<>(pageNo, pageSize);
        IPage<ZyClothsModular> pageList = zyClothsModularService.page(page, queryWrapper);
        return Result.OK(pageList);
    }


    @AutoLog(value = "zy_cloths_modular-查询所有")
    @ApiOperation(value = "zy_cloths_modular-查询所有", notes = "zy_cloths_modular-查询所有")
    @GetMapping(value = "/findAll")
    public Result<?> findAll() {
        List<ZyClothsModular> list = zyClothsModularService.list();
        for (ZyClothsModular zycl : list) {
            String id = zycl.getClothsTypeId();
            if (id != null) {
                ZyClothsType byId = zyClothsTypeService.getById(id);
                zycl.setClothsTypeId(byId.getTypeName());
            }

        }
        return Result.OK(list);
    }

    /**
     * 制衣模块详情
     * @param id 根据服装款式id进行查询
     */
    @AutoLog(value = "制衣模块详情详情")
    @ApiOperation(value = "制衣模块详情详情", notes = "根据制衣模块详情id进行查询")
    @GetMapping(value = "/detail")
    public Result<?> detail(@RequestParam(name = "id", required = true) String id) {
        //System.out.println("controller层接受到的id: zy_cloths_modularId: "+id);
        ZyClothsModularDetail detail = zyClothsModularService.queryDetail(id);
        return Result.OK(detail);
    }

    /**
     * 添加
     *
     * @param zyClothsModular
     * @return
     */
    @AutoLog(value = "zy_cloths_modular-添加")
    @ApiOperation(value = "zy_cloths_modular-添加", notes = "zy_cloths_modular-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ZyClothsModular zyClothsModular) {
        //zyClothsModular.setNums(zyClothsModularService.generateNumber(zyClothsModular));
        if (StringUtils.isEmpty(zyClothsModular.getModularType())) {
            return Result.error("添加失败！模块类型不能为空");
        }
        if (StringUtils.isEmpty(zyClothsModular.getEnterpriseId())) {
            return Result.error("添加失败！企业不能为空");
        }
        if (StringUtils.isEmpty(zyClothsModular.getModularName())) {
            return Result.error("添加失败！模块名称不能为空");
        }
        if (StringUtils.isEmpty(zyClothsModular.getClothsTypeId())) {
            return Result.error("添加失败！服装类型不能为空");
        }
        if (StringUtils.isEmpty(zyClothsModular.getNums())) {
            return Result.error("添加失败！编号不能为空");
        }
        //模块名称不能重复
        List<ZyClothsModular> zyClothsModularList = zyClothsModularService.list();
        for (ZyClothsModular zy : zyClothsModularList) {
            if (zyClothsModular.getModularName().equals(zy.getModularName())) {
                return Result.error("添加失败！模块名称已存在");
            }
        }
        zyClothsModularService.save(zyClothsModular);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zyClothsModular
     * @return
     */
    @AutoLog(value = "zy_cloths_modular-编辑")
    @ApiOperation(value = "zy_cloths_modular-编辑", notes = "zy_cloths_modular-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ZyClothsModular zyClothsModular) {
        zyClothsModularService.updateById(zyClothsModular);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "zy_cloths_modular-通过id删除")
    @ApiOperation(value = "zy_cloths_modular-通过id删除", notes = "zy_cloths_modular-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        zyClothsModularService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "zy_cloths_modular-批量删除")
    @ApiOperation(value = "zy_cloths_modular-批量删除", notes = "zy_cloths_modular-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zyClothsModularService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "zy_cloths_modular-通过id查询")
    @ApiOperation(value = "zy_cloths_modular-通过id查询", notes = "zy_cloths_modular-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ZyClothsModular zyClothsModular = zyClothsModularService.getById(id);
        if (zyClothsModular == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(zyClothsModular);
    }

    /**
     * sort 编码
     *
     * @param id 服装类型的id
     * @return
     */
    @AutoLog(value = "编码-通过id查询")
    @ApiOperation(value = "编码-通过id查询", notes = "编码-通过id查询")
    @GetMapping(value = "/sort")
    public Result<?> sort(@RequestParam(name = "id", required = true) String id) {
        //根据zy cloths type服装类型的id 进行自动编码
        //nums，编号，varchar，18，非空，不重复，编码规则：服装类型（10）+序号（8）
        String clothsTypeNums = zyClothsModularService.generateNumber(id);
        if (clothsTypeNums == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(clothsTypeNums);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zyClothsModular
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyClothsModular zyClothsModular) {
        return super.exportXls(request, zyClothsModular, ZyClothsModular.class, "zy_cloths_modular");
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
        return super.importExcel(request, response, ZyClothsModular.class);
    }

}
