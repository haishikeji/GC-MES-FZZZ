package org.px.modules.devicecuring.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.devicecuring.entity.ZyDevicecuring;
import org.px.modules.devicecuring.service.IZyDevicecuringService;

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
 * @Description: 设备养护
 * @@author 品讯科技
 * @Date:   2021-12-02
 * @Version: V1.0
 */
@Api(tags="设备养护")
@RestController
@RequestMapping("/devicecuring/zyDevicecuring")
@Slf4j
public class ZyDevicecuringController extends JeecgController<ZyDevicecuring, IZyDevicecuringService> {
	@Autowired
	private IZyDevicecuringService zyDevicecuringService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zyDevicecuring
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备养护-分页列表查询")
	@ApiOperation(value="设备养护-分页列表查询", notes="设备养护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyDevicecuring zyDevicecuring,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyDevicecuring> queryWrapper = QueryGenerator.initQueryWrapper(zyDevicecuring, req.getParameterMap());
		Page<ZyDevicecuring> page = new Page<ZyDevicecuring>(pageNo, pageSize);
		IPage<ZyDevicecuring> pageList = zyDevicecuringService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param zyDevicecuring
	 * @return
	 */
	@AutoLog(value = "设备养护-添加")
	@ApiOperation(value="设备养护-添加", notes="设备养护-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyDevicecuring zyDevicecuring) {
		zyDevicecuringService.save(zyDevicecuring);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param zyDevicecuring
	 * @return
	 */
	@AutoLog(value = "设备养护-编辑")
	@ApiOperation(value="设备养护-编辑", notes="设备养护-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyDevicecuring zyDevicecuring) {
		zyDevicecuringService.updateById(zyDevicecuring);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备养护-通过id删除")
	@ApiOperation(value="设备养护-通过id删除", notes="设备养护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyDevicecuringService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备养护-批量删除")
	@ApiOperation(value="设备养护-批量删除", notes="设备养护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyDevicecuringService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备养护-通过id查询")
	@ApiOperation(value="设备养护-通过id查询", notes="设备养护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyDevicecuring zyDevicecuring = zyDevicecuringService.getById(id);
		if(zyDevicecuring==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyDevicecuring);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyDevicecuring
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyDevicecuring zyDevicecuring) {
        return super.exportXls(request, zyDevicecuring, ZyDevicecuring.class, "设备养护");
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
        return super.importExcel(request, response, ZyDevicecuring.class);
    }

}
