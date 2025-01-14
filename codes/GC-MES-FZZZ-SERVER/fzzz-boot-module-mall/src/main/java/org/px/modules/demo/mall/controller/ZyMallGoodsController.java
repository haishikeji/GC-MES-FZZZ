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
import org.px.modules.demo.base.entity.ZyClothsComponent;
import org.px.modules.demo.base.entity.ZyClothsModular;
import org.px.modules.demo.base.entity.ZyClothsType;
import org.px.modules.demo.base.entity.ZyProcess;
import org.px.modules.demo.base.service.IZyClothsComponentService;
import org.px.modules.demo.base.service.IZyClothsModularService;
import org.px.modules.demo.base.service.IZyClothsTypeService;
import org.px.modules.demo.base.service.IZyProcessService;
import org.px.modules.demo.mall.entity.ZyMallGoods;
import org.px.modules.demo.mall.service.IZyMallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description: 商品表
 * @@author 品讯科技
 * @Date:   2021-11-09
 * @Version: V1.0
 */
@Api(tags="商品表")
@RestController
@RequestMapping("/mall/zyMallGoods")
@Slf4j
public class ZyMallGoodsController extends JeecgController<ZyMallGoods, IZyMallGoodsService> {
	@Autowired
	private IZyMallGoodsService zyMallGoodsService;
	@Autowired
	private IZyClothsModularService zyClothsModularService;
	@Autowired
	private IZyClothsComponentService zyClothsComponentService;
	@Autowired
	private IZyProcessService zyProcessService;
	@Autowired
	private IZyClothsTypeService zyClothsTypeService;

	/**
	 * 分页列表查询
	 *
	 * @param zyMallGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商品表-分页列表查询")
	@ApiOperation(value="商品表-分页列表查询", notes="商品表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyMallGoods zyMallGoods,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyMallGoods> queryWrapper = QueryGenerator.initQueryWrapper(zyMallGoods, req.getParameterMap());
		Page<ZyMallGoods> page = new Page<ZyMallGoods>(pageNo, pageSize);
		IPage<ZyMallGoods> pageList = zyMallGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param zyMallGoods
	 * @return
	 */
	@AutoLog(value = "商品表-添加")
	@ApiOperation(value="商品表-添加", notes="商品表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyMallGoods zyMallGoods) {
		zyMallGoodsService.save(zyMallGoods);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyMallGoods
	 * @return
	 */
	@AutoLog(value = "商品表-编辑")
	@ApiOperation(value="商品表-编辑", notes="商品表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyMallGoods zyMallGoods) {
		zyMallGoodsService.updateById(zyMallGoods);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品表-通过id删除")
	@ApiOperation(value="商品表-通过id删除", notes="商品表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyMallGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商品表-批量删除")
	@ApiOperation(value="商品表-批量删除", notes="商品表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyMallGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商品表-通过id查询")
	@ApiOperation(value="商品表-通过id查询", notes="商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyMallGoods zyMallGoods = zyMallGoodsService.getById(id);
		if(zyMallGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyMallGoods);
	}

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "商品表-通过id查询")
	 @ApiOperation(value="商品表-通过id查询", notes="商品表-通过id查询")
	 @GetMapping(value = "/selectById")
	 public Result<?> selectById(@RequestParam(name="id",required=true) String id) {
		 ZyMallGoods zyMallGoods = zyMallGoodsService.getById(id);
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Map<String, Object> map = new HashMap<String, Object>();
		 //造一下包含数据格式？？？
		 if(zyMallGoods==null) {
			 return Result.error("未找到对应数据");
		 }else{
		 	//模块list
			 List<ZyClothsModular> listzcm = zyClothsModularService.selectByType(zyMallGoods.getStyle());
			 if(listzcm.size() != 0){
				 for(int i=0; i<listzcm.size(); i++){
					 int keynums = 1;
					 //模块map集合
					 Map<String, Object> map2 = new HashMap<String, Object>();
					 map2.put("nums",listzcm.get(i).getNums());
					 map2.put("modular_name",listzcm.get(i).getModularName());
					 map2.put("picture_url",listzcm.get(i).getPictureUrl());
				 	//部件list
					 List<ZyClothsComponent> listzcc = zyClothsComponentService.selectByModular(listzcm.get(i).getId());
					 if(listzcc.size() !=0){
						 //模块对应的工序list集合
						 List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
						 for(int j=0; j<listzcc.size(); j++){
							 //工序list
							 List<ZyProcess> listzp = zyProcessService.selectByComponentId(listzcc.get(j).getId());
							 if(listzp.size() !=0){
							 	for(int k=0; k<listzp.size(); k++){
									Map<String, Object> map3 = new HashMap<String, Object>();
									map3.put("key",keynums);
									map3.put("process_name",listzp.get(k).getProcessName());
									map3.put("process_time",listzp.get(k).getProcessTime());
									map3.put("price",listzp.get(k).getPrice());
									map3.put("grade",listzp.get(k).getGrade());
									map3.put("quality_require",listzp.get(k).getQualityRequire());
									list2.add(map3);
									keynums++;
								}
/*								 map3.put("process_name",listzp.get(j).getProcessName());
								 map3.put("process_time",listzp.get(j).getProcessTime());
								 map3.put("price",listzp.get(j).getPrice());
								 map3.put("grade",listzp.get(j).getGrade());
								 map3.put("quality_require",listzp.get(j).getQualityRequire());
								 map3.put("process",listzp);*/
							 }
						 }
						 //放入模块所对应的所有工序list集合
						 map2.put("processlist",list2);
						 list.add(map2);
					 }
				 }
			 }
			 ZyClothsType zyClothsType = zyClothsTypeService.getById(zyMallGoods.getStyle());
			 zyMallGoods.setStyle(zyClothsType.getTypeName());
			 if("1".equals(zyMallGoods.getStatus())){
				zyMallGoods.setStatus("上架");
			 }else{
			 	zyMallGoods.setStatus("下架");
			 }
			 map.put("goods",zyMallGoods);
			 list.add(map);
		 }
		 return Result.OK(list);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param zyMallGoods
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyMallGoods zyMallGoods) {
        return super.exportXls(request, zyMallGoods, ZyMallGoods.class, "商品表");
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
        return super.importExcel(request, response, ZyMallGoods.class);
    }

}
