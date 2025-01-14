package org.px.modules.demo.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.px.common.api.vo.Result;
import org.px.common.aspect.annotation.AutoLog;
import org.px.common.system.base.controller.JeecgController;
import org.px.modules.demo.mall.entity.ZyMallStandardSize;
import org.px.modules.demo.mall.service.IZyMallStandardSizeService;
import org.px.modules.demo.mall.vo.DoubleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 标准尺码表
 * @@author 品讯科技
 * @Date:   2021-10-22
 * @Version: V1.0
 */
@Api(tags="标准尺码表")
@RestController
@RequestMapping("/mall/zyMallStandardSize")
@Slf4j
public class ZyMallStandardSizeController extends JeecgController<ZyMallStandardSize, IZyMallStandardSizeService> {
	@Autowired
	private IZyMallStandardSizeService zyMallStandardSizeService;

	/**
	 * 分页列表查询
	 *
	 * @param zyMallStandardSize
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "标准尺码表-分页列表查询")
	@ApiOperation(value="标准尺码表-分页列表查询", notes="标准尺码表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyMallStandardSize zyMallStandardSize,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyMallStandardSize> queryWrapper = new QueryWrapper<>();
		Page<ZyMallStandardSize> page = new Page<ZyMallStandardSize>(pageNo, pageSize);
		IPage<ZyMallStandardSize> pageList = zyMallStandardSizeService.pageSize(page, queryWrapper);
		for(ZyMallStandardSize z:pageList.getRecords()){
			HashMap<String,String> map = JSONObject.parseObject(z.getStandardSize(), HashMap.class);
			z.setBP(map.get("BP"));
			z.setBust(map.get("bust"));
			z.setChestDistance(map.get("chestDistance"));
			z.setClothingLength(map.get("clothingLength"));
			z.setHipline(map.get("hipline"));
			z.setNeck(map.get("neck"));
			z.setPredecessorLength(map.get("predecessorLength"));
			z.setShoulderWidth(map.get("shoulderWidth"));
			z.setSleeveLength(map.get("sleeveLength"));
			z.setCuff(map.get("cuff"));
			z.setWaist(map.get("waist"));
			z.setHem(map.get("hem"));
			z.setHeight(map.get("height"));
			z.setWeight(map.get("weight"));
		}
		return Result.OK(pageList);
	}
	@AutoLog(value = "标准尺码表-修身")
	@ApiOperation(value="标准尺码表-修身", notes="标准尺码表-修身")
	@GetMapping(value = "/minusOne")
	public Result<?> minusOne(@RequestParam(name="id",required=true) String id) {
		ZyMallStandardSize standardSize = zyMallStandardSizeService.getById(id);
		HashMap<String,String> map = JSONObject.parseObject(standardSize.getStandardSize(), HashMap.class);
		standardSize.setBP(DoubleUtil.minus(map.get("BP")));
		standardSize.setBust(DoubleUtil.minus(map.get("bust")));
		standardSize.setChestDistance(DoubleUtil.minus(map.get("chestDistance")));
		standardSize.setClothingLength(DoubleUtil.minus(map.get("clothingLength")));
		standardSize.setHipline(DoubleUtil.minus(map.get("hipline")));
		standardSize.setNeck(DoubleUtil.minus(map.get("neck")));
		standardSize.setPredecessorLength(DoubleUtil.minus(map.get("predecessorLength")));
		standardSize.setShoulderWidth(DoubleUtil.minus(map.get("shoulderWidth")));
		standardSize.setSleeveLength(DoubleUtil.minus(map.get("sleeveLength")));
		standardSize.setCuff(DoubleUtil.minus(map.get("cuff")));
		standardSize.setWaist(DoubleUtil.minus(map.get("waist")));
		standardSize.setHem(DoubleUtil.minus(map.get("hem")));
		standardSize.setHeight(DoubleUtil.minus(map.get("height")));
		standardSize.setWeight(DoubleUtil.minus(map.get("weight")));
		return Result.OK(standardSize);
	}

	@AutoLog(value = "标准尺码表-宽松")
	@ApiOperation(value="标准尺码表-宽松", notes="标准尺码表-宽松")
	@GetMapping(value = "/plusOne")
	public Result<?> plusOne(@RequestParam(name="id",required=true) String id) {
		ZyMallStandardSize standardSize = zyMallStandardSizeService.getById(id);
		HashMap<String,String> map = JSONObject.parseObject(standardSize.getStandardSize(), HashMap.class);
		standardSize.setBP(DoubleUtil.plus(map.get("BP")));
		standardSize.setBust(DoubleUtil.plus(map.get("bust")));
		standardSize.setChestDistance(DoubleUtil.plus(map.get("chestDistance")));
		standardSize.setClothingLength(DoubleUtil.plus(map.get("clothingLength")));
		standardSize.setHipline(DoubleUtil.plus(map.get("hipline")));
		standardSize.setNeck(DoubleUtil.plus(map.get("neck")));
		standardSize.setPredecessorLength(DoubleUtil.plus(map.get("predecessorLength")));
		standardSize.setShoulderWidth(DoubleUtil.plus(map.get("shoulderWidth")));
		standardSize.setSleeveLength(DoubleUtil.plus(map.get("sleeveLength")));
		standardSize.setCuff(DoubleUtil.plus(map.get("cuff")));
		standardSize.setWaist(DoubleUtil.plus(map.get("waist")));
		standardSize.setHem(DoubleUtil.plus(map.get("hem")));
		standardSize.setHeight(DoubleUtil.plus(map.get("height")));
		standardSize.setWeight(DoubleUtil.plus(map.get("weight")));
		return Result.OK(standardSize);
	}

	/**
	 *   添加
	 *
	 * @param zyMallStandardSize
	 * @return
	 */
	@AutoLog(value = "标准尺码表-添加")
	@ApiOperation(value="标准尺码表-添加", notes="标准尺码表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyMallStandardSize zyMallStandardSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("hem",zyMallStandardSize.getHem());
		map.put("cuff",zyMallStandardSize.getCuff());
		map.put("sleeveLength",zyMallStandardSize.getSleeveLength());
		map.put("bust",zyMallStandardSize.getBust());
		map.put("waist",zyMallStandardSize.getWeight());
		map.put("shoulderWidth",zyMallStandardSize.getShoulderWidth());
		map.put("predecessorLength",zyMallStandardSize.getPredecessorLength());
		map.put("neck",zyMallStandardSize.getNeck());
		map.put("hipline",zyMallStandardSize.getHipline());
		map.put("clothingLength",zyMallStandardSize.getClothingLength());
		map.put("chestDistance",zyMallStandardSize.getChestDistance());
		map.put("BP",zyMallStandardSize.getBP());
		Map<String,Object> res=new HashMap<>();
		for (String s:map.keySet()){
			if(!map.get(s).equals("")){
				res.put(s,map.get(s));
			}
		}
		zyMallStandardSize.setStandardSize(new JSONObject(res).toJSONString());
		zyMallStandardSizeService.save(zyMallStandardSize);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyMallStandardSize
	 * @return
	 */
	@AutoLog(value = "标准尺码表-编辑")
	@ApiOperation(value="标准尺码表-编辑", notes="标准尺码表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyMallStandardSize zyMallStandardSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("hem",zyMallStandardSize.getHem());
		map.put("cuff",zyMallStandardSize.getCuff());
		map.put("sleeveLength",zyMallStandardSize.getSleeveLength());
		map.put("bust",zyMallStandardSize.getBust());
		map.put("waist",zyMallStandardSize.getWeight());
		map.put("shoulderWidth",zyMallStandardSize.getShoulderWidth());
		map.put("predecessorLength",zyMallStandardSize.getPredecessorLength());
		map.put("neck",zyMallStandardSize.getNeck());
		map.put("hipline",zyMallStandardSize.getHipline());
		map.put("clothingLength",zyMallStandardSize.getClothingLength());
		map.put("chestDistance",zyMallStandardSize.getChestDistance());
		map.put("BP",zyMallStandardSize.getBP());
		Map<String,Object> res=new HashMap<>();
		for (String s:map.keySet()){
			if(!map.get(s).equals("")){
				res.put(s,map.get(s));
			}
		}
		zyMallStandardSize.setStandardSize(new JSONObject(res).toJSONString());
		zyMallStandardSizeService.updateById(zyMallStandardSize);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "标准尺码表-通过id删除")
	@ApiOperation(value="标准尺码表-通过id删除", notes="标准尺码表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zyMallStandardSizeService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "标准尺码表-批量删除")
	@ApiOperation(value="标准尺码表-批量删除", notes="标准尺码表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyMallStandardSizeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "标准尺码表-通过id查询")
	@ApiOperation(value="标准尺码表-通过id查询", notes="标准尺码表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZyMallStandardSize zyMallStandardSize = zyMallStandardSizeService.getById(id);
		if(zyMallStandardSize==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyMallStandardSize);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyMallStandardSize
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyMallStandardSize zyMallStandardSize) {
        return super.exportXls(request, zyMallStandardSize, ZyMallStandardSize.class, "标准尺码表");
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
        return super.importExcel(request, response, ZyMallStandardSize.class);
    }

}
