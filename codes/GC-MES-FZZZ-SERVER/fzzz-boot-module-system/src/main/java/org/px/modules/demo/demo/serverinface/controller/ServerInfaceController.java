package org.px.modules.demo.demo.serverinface.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.modules.demo.demo.serverinface.entity.ServerInface;
import org.px.modules.demo.demo.serverinface.service.IServerInfaceService;

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
 * @Description: 服务器接口配置
 * @@author 品讯科技
 * @Date:   2021-11-24
 * @Version: V1.0
 */
@Api(tags="服务器接口配置")
@RestController
@RequestMapping("/serverinface/serverInface")
@Slf4j
public class ServerInfaceController extends JeecgController<ServerInface, IServerInfaceService> {
	@Autowired
	private IServerInfaceService serverInfaceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param serverInface
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "服务器接口配置-分页列表查询")
	@ApiOperation(value="服务器接口配置-分页列表查询", notes="服务器接口配置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ServerInface serverInface,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ServerInface> queryWrapper = QueryGenerator.initQueryWrapper(serverInface, req.getParameterMap());
		Page<ServerInface> page = new Page<ServerInface>(pageNo, pageSize);
		IPage<ServerInface> pageList = serverInfaceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param serverInface
	 * @return
	 */
	@AutoLog(value = "服务器接口配置-添加")
	@ApiOperation(value="服务器接口配置-添加", notes="服务器接口配置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ServerInface serverInface) {
		serverInfaceService.save(serverInface);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param serverInface
	 * @return
	 */
	@AutoLog(value = "服务器接口配置-编辑")
	@ApiOperation(value="服务器接口配置-编辑", notes="服务器接口配置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ServerInface serverInface) {
		serverInfaceService.updateById(serverInface);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "服务器接口配置-通过id删除")
	@ApiOperation(value="服务器接口配置-通过id删除", notes="服务器接口配置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		serverInfaceService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "服务器接口配置-批量删除")
	@ApiOperation(value="服务器接口配置-批量删除", notes="服务器接口配置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.serverInfaceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "服务器接口配置-通过id查询")
	@ApiOperation(value="服务器接口配置-通过id查询", notes="服务器接口配置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ServerInface serverInface = serverInfaceService.getById(id);
		if(serverInface==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(serverInface);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param serverInface
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ServerInface serverInface) {
        return super.exportXls(request, serverInface, ServerInface.class, "服务器接口配置");
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
        return super.importExcel(request, response, ServerInface.class);
    }

}
