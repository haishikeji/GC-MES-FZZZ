package org.px.modules.demo.zyorders.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.px.common.api.vo.Result;
import org.px.common.system.query.QueryGenerator;
import org.px.common.system.vo.LoginUser;
import org.px.common.util.ImageUrl;
import org.px.modules.demo.accessories.service.IZyAccessoriesService;
import org.px.modules.demo.base.service.IZyClothsTypeService;
import org.px.modules.demo.customerinvoice.entity.CustomerInvoice;
import org.px.modules.demo.customerinvoice.service.ICustomerInvoiceService;
import org.px.modules.demo.customermeasure.entity.CustomerMeasure;
import org.px.modules.demo.customermeasure.service.ICustomerMeasureService;
import org.px.modules.demo.customerpayment.entity.CustomerPayment;
import org.px.modules.demo.customerpayment.service.ICustomerPaymentService;
import org.px.modules.demo.customerreceivingaddress.entity.CustomerReceivingAddress;
import org.px.modules.demo.customerreceivingaddress.service.ICustomerReceivingAddressService;
import org.px.modules.demo.fabric.service.IZyFabricService;
import org.px.modules.demo.goodsaccessories.entity.GoodsAccessories;
import org.px.modules.demo.goodsaccessories.service.IGoodsAccessoriesService;
import org.px.modules.demo.goodsfabric.entity.GoodsFabric;
import org.px.modules.demo.goodsfabric.service.IGoodsFabricService;
import org.px.modules.demo.goodsmodule.entity.GoodsModule;
import org.px.modules.demo.goodsmodule.service.IGoodsModuleService;
import org.px.modules.demo.ordergoods.entity.OrderGoods;
import org.px.modules.demo.ordergoods.entity.OrderGoodsJk;
import org.px.modules.demo.ordergoods.service.IOrderGoodsService;
import org.px.modules.demo.ordergoodsaccessories.entity.OrderGoodsAccessories;
import org.px.modules.demo.ordergoodsaccessories.service.IOrderGoodsAccessoriesService;
import org.px.modules.demo.ordergoodsfabric.entity.OrderGoodsFabric;
import org.px.modules.demo.ordergoodsfabric.service.IOrderGoodsFabricService;
import org.px.modules.demo.ordergoodsmodule.entity.OrderGoodsModule;
import org.px.modules.demo.ordergoodsmodule.service.IOrderGoodsModuleService;
import org.px.modules.demo.zyorders.entity.Dz;
import org.px.modules.demo.zygoods.entity.ZyGoods;
import org.px.modules.demo.zygoods.service.IZyGoodsService;
import org.px.modules.demo.zyorders.entity.ZyOrders;
import org.px.modules.demo.zyorders.entity.ZyOrdersJk;
import org.px.modules.demo.zyorders.service.IZyOrdersService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.px.modules.system.entity.SysDepart;
import org.px.modules.system.entity.SysUser;
import org.px.modules.system.service.ISysDepartService;
import org.px.modules.system.service.ISysUserService;
import org.px.modules.zyclothsstyle.service.IZyClothsStyleService;
import org.px.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.px.common.aspect.annotation.AutoLog;

 /**
 * @Description: 订单基本信息管理
 * @@author 品讯科技
 * @Date:   2022-12-26
 * @Version: V1.0
 */
@Api(tags="订单基本信息管理")
@RestController
@RequestMapping("/zyorders/zyOrders")
@Slf4j
public class ZyOrdersController extends JeecgController<ZyOrders, IZyOrdersService> {
	 @Autowired
	 private IZyOrdersService zyOrdersService;
	 @Autowired
	 private IOrderGoodsService orderGoodsService;
	 @Autowired
	 private ICustomerReceivingAddressService iCustomerReceivingAddressService;
	 @Autowired
	 private ICustomerInvoiceService iCustomerInvoiceService;
	 @Autowired
	 private ICustomerPaymentService iCustomerPaymentService;
	 @Autowired
	 private IZyGoodsService zyGoodsService;
	 @Autowired
	 private IOrderGoodsModuleService orderGoodsModuleService;
	 @Autowired
	 private IOrderGoodsFabricService orderGoodsFabricService;
	 @Autowired
	 private IOrderGoodsAccessoriesService orderGoodsAccessoriesService;
	 @Autowired
	 private IGoodsModuleService goodsModuleService;
	 @Autowired
	 private IGoodsFabricService goodsFabricService;
	 @Autowired
	 private IGoodsAccessoriesService goodsAccessoriesService;
	 @Autowired
	 private IZyClothsStyleService zyClothsStyleService;
	 @Autowired
	 private IZyFabricService zyFabricService;
	 @Autowired
	 private IZyAccessoriesService zyAccessoriesService;
	 @Autowired
	 private ISysDepartService sysDepartService;
	 @Autowired
	 private ISysUserService sysUserService;
	 @Autowired
	 private ICustomerMeasureService iCustomerMeasureService;
	 @Autowired
	 private IZyClothsTypeService zyClothsTypeService;

	 /**
	  * 订单和订单商品接口
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "订单和订单商品接口")
	 @ApiOperation(value="订单和订单商品接口", notes="订单和订单商品接口")
	 public List<String> orderJk(ZyOrdersJk zyOrdersJk, List<OrderGoodsJk> orderGoodsJkList) {
	 	Double actual_payment = 0.00;
	 	Double money = 0.00;
	 	for(int i = 0 ; i < orderGoodsJkList.size() ; i++){
			ZyGoods zyGoods = zyGoodsService.getById(orderGoodsJkList.get(i).getGoodsId());
			money = money + zyGoods.getUnitPrice()*orderGoodsJkList.get(i).getGoodsNum();
			actual_payment = actual_payment + zyGoods.getPromotionPrice()*orderGoodsJkList.get(i).getGoodsNum();
		}
		 ZyOrders zyOrders = new ZyOrders();
		 zyOrders.setIschild(0);
		 zyOrders.setOrderTime(new Date());
		 zyOrders.setOrdersNum(getOrdersNum());
		 zyOrders.setMoney(money);
		 zyOrders.setFreight(zyOrdersJk.getFreight());
		 zyOrders.setPromotionPrice(money-actual_payment);
		 zyOrders.setActualPayment(actual_payment);
		 zyOrders.setDeliveryTime(zyOrdersJk.getDeliveryTime());
		 zyOrders.setOrdersType(zyOrdersJk.getOrdersType());
		 zyOrders.setUserId(zyOrdersJk.getUserId());
		 zyOrders.setGoodsQuantity(zyOrdersJk.getGoodsQuantity());
		 CustomerReceivingAddress customerReceivingAddress = iCustomerReceivingAddressService.getById(zyOrdersJk.getAddressId());
		 zyOrders.setCityId(customerReceivingAddress.getCityId());
		 zyOrders.setAddress(customerReceivingAddress.getAddress());
		 zyOrders.setZipCode(customerReceivingAddress.getZipCode());
		 zyOrders.setReceiver(zyOrdersJk.getReceiver());
		 zyOrders.setMobile(customerReceivingAddress.getMobile());
		 CustomerInvoice customerInvoice = iCustomerInvoiceService.getById(zyOrdersJk.getInvoiceId());
		 zyOrders.setInvoiceCustomerType(Integer.parseInt(customerInvoice.getCustomerType()));
		 zyOrders.setInvoiceType(Integer.parseInt(customerInvoice.getInvoiceType()));
		 zyOrders.setTaxIdentificationNumber(customerInvoice.getTaxIdentificationNumber());
		 zyOrders.setAddressBilling(customerInvoice.getAddress());
		 zyOrders.setTelephone(customerInvoice.getTelephone());
		 zyOrders.setBankPublic(customerInvoice.getBankDeposit());
		 zyOrders.setAccountNumber(customerInvoice.getAccountNumber());
		 zyOrders.setSalesEnterpriseId(zyOrdersJk.getSalesEnterpriseId());
		 zyOrders.setSalespersonId(zyOrdersJk.getSalespersonId());
		 zyOrders.setLogisticsEnterprisesId(zyOrdersJk.getLogisticsEnterprisesId());
		 zyOrders.setDeliveryStatus(0);
		 zyOrdersService.save(zyOrders);
		 for(int j = 0 ; j < orderGoodsJkList.size() ; j++){
			 ZyGoods zyGoods = zyGoodsService.getById(orderGoodsJkList.get(j).getGoodsId());
			 OrderGoods orderGoods = new OrderGoods();
			 orderGoods.setOrdersId(zyOrders.getId());
			 orderGoods.setGoodsId(orderGoodsJkList.get(j).getGoodsId());
			 orderGoods.setGoodsNum(orderGoodsJkList.get(j).getGoodsNum());
			 orderGoods.setGoodsCode(zyGoods.getGoodsCode());
			 orderGoods.setGoodsName(zyGoods.getGoodsName());
			 orderGoods.setGoodsIntr(zyGoods.getGoodsIntr());
			 orderGoods.setCustomized(zyGoods.getCustomized());
			 orderGoods.setMeasureId(orderGoodsJkList.get(j).getMeasureId());
			 orderGoods.setGoodsPicture(zyGoods.getGoodsPicture());
			 orderGoods.setDefaultPicture(zyGoods.getDefaultPicture());
			 orderGoods.setGoodsVideo(zyGoods.getGoodsVideo());
			 orderGoods.setClothsTypeId(zyGoods.getClothsTypeId());
			 orderGoods.setStyleId(zyGoods.getStyleId());
			 orderGoods.setModelId(zyGoods.getModelId());
			 orderGoods.setUnit(zyGoods.getUnit());
			 orderGoods.setNumber(zyGoods.getNumber());
			 orderGoods.setUnitPrice(zyGoods.getUnitPrice());
			 orderGoods.setDiscount(zyGoods.getDiscount());
			 orderGoods.setPromotionPrice(zyGoods.getPromotionPrice());
			 orderGoods.setRecommend(zyGoods.getRecommend());
			 orderGoods.setPlaceOrigin(zyGoods.getPlaceOrigin());
			 orderGoods.setStatus(zyGoods.getStatus());
			 orderGoods.setTotalPrice(zyGoods.getUnitPrice()*orderGoodsJkList.get(j).getGoodsNum());
			 orderGoods.setPayTotalPrice(zyGoods.getPromotionPrice()*orderGoodsJkList.get(j).getGoodsNum());
			 orderGoodsService.save(orderGoods);
			 GoodsModule goodsModule = new GoodsModule();
			 QueryWrapper<GoodsModule> queryWrapperzsm = new QueryWrapper();
			 queryWrapperzsm.eq("goods_id",zyGoods.getId());
			 queryWrapperzsm.eq("style_id",zyGoods.getStyleId());
			 List<GoodsModule> listzsm = goodsModuleService.list(queryWrapperzsm);
			 for (int k = 0 ; k < listzsm.size() ; k++){
				 OrderGoodsModule orderGoodsModule = new OrderGoodsModule();
				 orderGoodsModule.setOrderGoodsId(orderGoods.getId());
				 orderGoodsModule.setGoodsId(zyGoods.getId());
				 orderGoodsModule.setStyleId(zyGoods.getStyleId());
				 orderGoodsModule.setStyleModuleId(listzsm.get(k).getStyleModuleId());
				 orderGoodsModule.setModuleId(listzsm.get(k).getModuleId());
				 orderGoodsModuleService.save(orderGoodsModule);
			 }
			 GoodsFabric goodsFabric = new GoodsFabric();
			 QueryWrapper<GoodsFabric> queryWrapperzsf =new QueryWrapper();
			 queryWrapperzsf.eq("goods_id",zyGoods.getId());
			 List<GoodsFabric> listzsf = goodsFabricService.list(queryWrapperzsf);
			 for (int o = 0 ; o < listzsf.size() ; o++){
				 OrderGoodsFabric orderGoodsFabric = new OrderGoodsFabric();
				 orderGoodsFabric.setOrderGoodsId(orderGoods.getId());
				 orderGoodsFabric.setGoodsId(zyGoods.getId());
				 orderGoodsFabric.setFabricId(listzsf.get(o).getFabricId());
				 orderGoodsFabricService.save(orderGoodsFabric);
			 }
			 GoodsAccessories goodsAccessories = new GoodsAccessories();
			 QueryWrapper<GoodsAccessories> queryWrapperzsa = new QueryWrapper();
			 queryWrapperzsa.eq("goods_id",zyGoods.getId());
			 List<GoodsAccessories> listzsa = goodsAccessoriesService.list(queryWrapperzsa);
			 for (int p = 0 ; p < listzsa.size() ; p++){
				 OrderGoodsAccessories orderGoodsAccessories = new OrderGoodsAccessories();
				 orderGoodsAccessories.setOrderGoodsId(orderGoods.getId());
				 orderGoodsAccessories.setGoodsId(zyGoods.getId());
				 orderGoodsAccessories.setAccessoriesId(listzsa.get(p).getAccessoriesId());
				 orderGoodsAccessoriesService.save(orderGoodsAccessories);
			 }


		 }
		 List<String> ids = new ArrayList<>();
		 ZyOrders zos = new ZyOrders();
		 QueryWrapper<ZyOrders> queryWrapperzos = new QueryWrapper();
		 queryWrapperzos.eq("order_id",zyOrders.getId());
		 List<ZyOrders> listzos = zyOrdersService.list(queryWrapperzos);
		 if(listzos.size()>0){
			 //return Result.error("该订单已经拆分,不可以再次拆单!");
			 return null;
		 }else{
			 OrderGoods orderGoods = new OrderGoods();
			 QueryWrapper<OrderGoods> queryWrapperog = new QueryWrapper();
			 queryWrapperog.eq("orders_id",zyOrders.getId());
			 List<OrderGoods> listog = orderGoodsService.list(queryWrapperog);
			 if(listog.size()>0){
				 for (int i = 0 ; i < listog.size() ; i++){
					 ZyOrders zo = new ZyOrders();
					 zo = zyOrders;
					 zo.setId(null);
					 zo.setOrdersNum(getOrdersNum());
					 zo.setFreight(Double.parseDouble("0"));
					 zo.setIschild(1);
					 zo.setOrderId(zyOrders.getId());
					 zo.setMoney(listog.get(i).getTotalPrice());
					 zo.setPromotionPrice(listog.get(i).getPromotionPrice());
					 zo.setActualPayment(listog.get(i).getPayTotalPrice());
					 zo.setGoodsQuantity(1);
					 zyOrdersService.save(zo);
					 ids.add(zo.getId());
					 OrderGoods og = new OrderGoods();
					 og = listog.get(i);
					 og.setOrdersId(zo.getId());
					 orderGoodsService.updateById(og);
				 }
			 }
		 }
 		 return ids;
	 }


	/**
	 * 分页列表查询
	 *
	 * @param zyOrders
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单基本信息管理-分页列表查询")
	@ApiOperation(value="订单基本信息管理-分页列表查询", notes="订单基本信息管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZyOrders zyOrders,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<ZyOrders> queryWrapper = QueryGenerator.initQueryWrapper(zyOrders, req.getParameterMap());
		Page<ZyOrders> page = new Page<ZyOrders>(pageNo, pageSize);
		IPage<ZyOrders> pageList = zyOrdersService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param zyOrders
	 * @return
	 */
	@AutoLog(value = "订单基本信息管理-添加")
	@ApiOperation(value="订单基本信息管理-添加", notes="订单基本信息管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZyOrders zyOrders) {
		zyOrdersService.save(zyOrders);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param zyOrders
	 * @return
	 */
	@AutoLog(value = "订单基本信息管理-编辑")
	@ApiOperation(value="订单基本信息管理-编辑", notes="订单基本信息管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZyOrders zyOrders) {
		zyOrdersService.updateById(zyOrders);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单基本信息管理-通过id删除")
	@ApiOperation(value="订单基本信息管理-通过id删除", notes="订单基本信息管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ZyOrders zyOrders = zyOrdersService.getById(id);
		zyOrders.setDeliveryStatus(9);
		zyOrdersService.updateById(zyOrders);
		return Result.OK("撤销订单成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单基本信息管理-批量删除")
	@ApiOperation(value="订单基本信息管理-批量删除", notes="订单基本信息管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		String str[] = ids.split(",");
		for (int i = 0 ; i < str.length ; i ++){
			ZyOrders zyOrders = zyOrdersService.getById(str[i]);
			zyOrders.setDeliveryStatus(9);
			zyOrdersService.updateById(zyOrders);
		}
		return Result.OK("批量撤销订单成功!");
	}

	/**
	 * 生产
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "生产")
	@ApiOperation(value="生产", notes="生产")
	@GetMapping(value = "/shengchan")
	public Result<?> shengchan(@RequestParam(name="id",required=true) String id) {
		ZyOrders zyOrders = zyOrdersService.getById(id);
		if(zyOrders==null) {
			return Result.error("未找到对应数据");
		}
		zyOrders.setDeliveryStatus(2);
		zyOrdersService.updateById(zyOrders);
		return Result.OK("成功");
	}
	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "订单基本信息管理-通过id查询")
	 @ApiOperation(value="订单基本信息管理-通过id查询", notes="订单基本信息管理-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		 ZyOrders zyOrders = zyOrdersService.getById(id);
		 if(zyOrders==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.OK(zyOrders);
	 }
	 /**
	  * 生成订单编号
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "生成订单编号")
	 @ApiOperation(value="生成订单编号", notes="生成订单编号")
	 @GetMapping(value = "/getOrdersNum")
	 public String getOrdersNum() {
	 	 List<ZyOrders> list = zyOrdersService.list();
		 List<String> collect = list.stream().map(ZyOrders::getOrdersNum).collect(Collectors.toList());
		 String OrdersNum = String.format("%018d", zyClothsTypeService.getListMax(collect, 18));
		 return OrdersNum;
	 }

	 /**
	  * 拆单
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "拆单")
	 @ApiOperation(value="拆单", notes="拆单")
	 @GetMapping(value = "/chaidan")
	 public Result<?> chaidan(@RequestParam(name="id",required=true) String id,HttpServletRequest req) {
		 ZyOrders zyOrders = zyOrdersService.getById(id);
		 if(zyOrders==null) {
			 return Result.error("未找到对应数据");
		 }else{
			 ZyOrders zos = new ZyOrders();
			 QueryWrapper<ZyOrders> queryWrapperzos = QueryGenerator.initQueryWrapper(zos, req.getParameterMap());
			 queryWrapperzos.eq("order_id",id);
			 List<ZyOrders> listzos = zyOrdersService.list(queryWrapperzos);
			 if(listzos.size()>0){
				 return Result.error("该订单已经拆分,不可以再次拆单!");
			 }else{
				 OrderGoods orderGoods = new OrderGoods();
				 QueryWrapper<OrderGoods> queryWrapperog = QueryGenerator.initQueryWrapper(orderGoods, req.getParameterMap());
				 queryWrapperog.eq("orders_id",id);
				 List<OrderGoods> listog = orderGoodsService.list(queryWrapperog);
				 if(listog.size()>0){
					 for (int i = 0 ; i < listog.size() ; i++){
						 ZyOrders zo = new ZyOrders();
						 zo = zyOrders;
						 zo.setId(null);
						 zo.setOrdersNum(getOrdersNum());
						 zo.setFreight(Double.parseDouble("0"));
						 zo.setIschild(1);
						 zo.setOrderId(id);
						 zo.setMoney(listog.get(i).getTotalPrice());
						 zo.setPromotionPrice(listog.get(i).getPromotionPrice());
						 zo.setActualPayment(listog.get(i).getPayTotalPrice());
						 zo.setGoodsQuantity(1);
						 zyOrdersService.save(zo);
						 OrderGoods og = new OrderGoods();
						 og = listog.get(i);
						 og.setOrdersId(zo.getId());
						 orderGoodsService.updateById(og);
					 }
				 }
			 }
		 }
		 return Result.OK("成功");
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param zyOrders
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyOrders zyOrders) {
        return super.exportXls(request, zyOrders, ZyOrders.class, "订单基本信息管理");
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
        return super.importExcel(request, response, ZyOrders.class);
    }

	 /**
	  * 商城接口下单
	  *
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "下单")
	 @ApiOperation(value="下单", notes="下单")
	 @GetMapping(value = "/xd")
	 public Result<?> xd(@RequestParam(name="goodsid",required=true) String goodsid,HttpServletRequest req) {
		 //这里方便获取当前登陆信息
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 //这里获取当前登陆的id
		 String userid = loginUser.getId();
		 //这里获取当前登陆的真实姓名
		 String rlname = loginUser.getRealname();
		 ZyGoods zyGoods = zyGoodsService.getById(goodsid);
		 Map<String,Object> map = new HashMap<>();
		 Date d = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 map.put("dqsj",sdf.format(d));
		 map.put("orderNum",getOrdersNum());
		 //默认地址
		 CustomerReceivingAddress customerReceivingAddress = new CustomerReceivingAddress();
		 QueryWrapper<CustomerReceivingAddress> queryWrappercra = QueryGenerator.initQueryWrapper(customerReceivingAddress, req.getParameterMap());
		 queryWrappercra.eq("user_id",userid);
		 queryWrappercra.orderByAsc("sort");
		 List<CustomerReceivingAddress> listcra = iCustomerReceivingAddressService.list(queryWrappercra);
		 Dz dz = new Dz();
		 dz.setId(listcra.get(0).getId());
		 dz.setAddr1(listcra.get(0).getCityId());
		 dz.setAddr2(listcra.get(0).getAddress());
		 dz.setPostCode(listcra.get(0).getZipCode());
		 dz.setFlag(false);
		 map.put("address",dz);
		 map.put("gmr",rlname);
		 map.put("spmc",zyGoods.getGoodsName());
		 map.put("spjg",zyGoods.getUnitPrice());
		 map.put("spks",zyClothsStyleService.getById(zyGoods.getStyleId()).getStyleNames());
		 //主辅料
		 GoodsFabric goodsFabric = new GoodsFabric();
		 QueryWrapper<GoodsFabric> queryWrappergf = QueryGenerator.initQueryWrapper(goodsFabric, req.getParameterMap());
		 queryWrappergf.eq("goods_id",goodsid);
		 List<GoodsFabric> listgf = goodsFabricService.list(queryWrappergf);
		 String zlstr = "";
		 for(int i = 0 ; i < listgf.size() ; i++){
		 	zlstr = zlstr + zyFabricService.getById(listgf.get(i).getFabricId()).getName()+",";
		 }
		 GoodsAccessories goodsAccessories = new GoodsAccessories();
		 QueryWrapper<GoodsAccessories> queryWrapperga = QueryGenerator.initQueryWrapper(goodsAccessories, req.getParameterMap());
		 queryWrapperga.eq("goods_id",goodsid);
		 List<GoodsAccessories> listga = goodsAccessoriesService.list(queryWrapperga);
		 String flstr = "";
		 for(int k = 0 ; k < listga.size() ; k++){
		 	flstr = flstr + zyAccessoriesService.getById(listga.get(k).getAccessoriesId()).getContents()+",";
		 }
		 map.put("zfl",zlstr+flstr.substring(0,flstr.length()-1));
		 map.put("sptp", ImageUrl.Image_Url+zyGoods.getDefaultPicture());
		 return Result.OK(map);
	 }

	 /**
	  * 获取地址列表
	  *
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "地址列表")
	 @ApiOperation(value="地址列表", notes="地址列表")
	 @GetMapping(value = "/dzlist")
	 public Result<?> dzlist(HttpServletRequest req) {
		 //这里方便获取当前登陆信息
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 //这里获取当前登陆的id
		 String userid = loginUser.getId();
		 CustomerReceivingAddress customerReceivingAddress = new CustomerReceivingAddress();
		 QueryWrapper<CustomerReceivingAddress> queryWrappercra = QueryGenerator.initQueryWrapper(customerReceivingAddress, req.getParameterMap());
		 queryWrappercra.eq("user_id",userid);
		 List<CustomerReceivingAddress> listcra = iCustomerReceivingAddressService.list(queryWrappercra);
		 List<Dz> listdz = new ArrayList<>();
		 for (int i = 0 ; i < listcra.size(); i++){
			 Dz dz = new Dz();
			 dz.setId(listcra.get(i).getId());
			 dz.setAddr1(listcra.get(i).getCityId());
			 dz.setAddr2(listcra.get(i).getAddress());
			 dz.setPostCode(listcra.get(i).getZipCode());
			 dz.setFlag(false);
			 listdz.add(dz);
		 }
		 return Result.OK(listdz);
	 }

	 /**
	  * 订单下单数据交互
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "订单下单数据交互")
	 @ApiOperation(value="订单下单数据交互", notes="订单下单数据交互")
	 @GetMapping(value = "/orderXD")
	 public String orderXD(ZyOrdersJk zyOrdersJk,List<OrderGoodsJk> orderGoodsJkList,HttpServletRequest req,String OrdersNum) {
		 Double actual_payment = 0.00;
		 Double money = 0.00;
		 for(int i = 0 ; i < orderGoodsJkList.size() ; i++){
			 ZyGoods zyGoods = zyGoodsService.getById(orderGoodsJkList.get(i).getGoodsId());
			 money = money + zyGoods.getUnitPrice()*orderGoodsJkList.get(i).getGoodsNum();
			 actual_payment = actual_payment + zyGoods.getPromotionPrice()*orderGoodsJkList.get(i).getGoodsNum();
		 }
		 ZyOrders zyOrders = new ZyOrders();
		 zyOrders.setIschild(0);
		 zyOrders.setOrderTime(new Date());
		 zyOrders.setOrdersNum(OrdersNum);
		 zyOrders.setMoney(money);
		 zyOrders.setFreight(zyOrdersJk.getFreight());
		 zyOrders.setPromotionPrice(money-actual_payment);
		 zyOrders.setActualPayment(actual_payment);
		 //交货期先为空
		 //zyOrders.setDeliveryTime(zyOrdersJk.getDeliveryTime());
		 zyOrders.setOrdersType(zyOrdersJk.getOrdersType());
		 zyOrders.setUserId(zyOrdersJk.getUserId());
		 CustomerPayment customerPayment = new CustomerPayment();
		 QueryWrapper<CustomerPayment> queryWrappercp = QueryGenerator.initQueryWrapper(customerPayment, req.getParameterMap());
		 queryWrappercp.eq("user_id",zyOrdersJk.getUserId());
		 queryWrappercp.orderByAsc("sort");
		 List<CustomerPayment>listcp = iCustomerPaymentService.list(queryWrappercp);
		 zyOrders.setPaymentMethod(Integer.parseInt(listcp.get(0).getPaymentType()));
		 zyOrders.setPaymentStatus(0);
		 zyOrders.setAccountNo(listcp.get(0).getAccountNo());
		 zyOrders.setBankDeposit(listcp.get(0).getBankDeposit());
		 zyOrders.setGoodsQuantity(zyOrdersJk.getGoodsQuantity());
		 CustomerReceivingAddress customerReceivingAddress = iCustomerReceivingAddressService.getById(zyOrdersJk.getAddressId());
		 zyOrders.setCityId(customerReceivingAddress.getCityId());
		 zyOrders.setAddress(customerReceivingAddress.getAddress());
		 zyOrders.setZipCode(customerReceivingAddress.getZipCode());
		 zyOrders.setReceiver(zyOrdersJk.getReceiver());
		 zyOrders.setMobile(customerReceivingAddress.getMobile());
		 CustomerInvoice customerInvoice = iCustomerInvoiceService.getById(zyOrdersJk.getInvoiceId());
		 zyOrders.setInvoiceCustomerType(Integer.parseInt(customerInvoice.getCustomerType()));
		 zyOrders.setInvoiceType(Integer.parseInt(customerInvoice.getInvoiceType()));
		 zyOrders.setTaxIdentificationNumber(customerInvoice.getTaxIdentificationNumber());
		 zyOrders.setAddressBilling(customerInvoice.getAddress());
		 zyOrders.setTelephone(customerInvoice.getTelephone());
		 zyOrders.setBankPublic(customerInvoice.getBankDeposit());
		 zyOrders.setAccountNumber(customerInvoice.getAccountNumber());
		 zyOrders.setSalesEnterpriseId(zyOrdersJk.getSalesEnterpriseId());
		 zyOrders.setSalespersonId(zyOrdersJk.getSalespersonId());
		 //物流企业先空着
		 //zyOrders.setLogisticsEnterprisesId(zyOrdersJk.getLogisticsEnterprisesId());
		 zyOrders.setDeliveryStatus(0);
		 zyOrdersService.save(zyOrders);
		 for(int j = 0 ; j < orderGoodsJkList.size() ; j++){
			 ZyGoods zyGoods = zyGoodsService.getById(orderGoodsJkList.get(j).getGoodsId());
			 OrderGoods orderGoods = new OrderGoods();
			 orderGoods.setOrdersId(zyOrders.getId());
			 orderGoods.setGoodsId(orderGoodsJkList.get(j).getGoodsId());
			 orderGoods.setGoodsNum(orderGoodsJkList.get(j).getGoodsNum());
			 orderGoods.setGoodsCode(zyGoods.getGoodsCode());
			 orderGoods.setGoodsName(zyGoods.getGoodsName());
			 orderGoods.setGoodsIntr(zyGoods.getGoodsIntr());
			 orderGoods.setCustomized(zyGoods.getCustomized());
			 orderGoods.setMeasureId(orderGoodsJkList.get(j).getMeasureId());
			 orderGoods.setGoodsPicture(zyGoods.getGoodsPicture());
			 orderGoods.setDefaultPicture(zyGoods.getDefaultPicture());
			 orderGoods.setGoodsVideo(zyGoods.getGoodsVideo());
			 orderGoods.setClothsTypeId(zyGoods.getClothsTypeId());
			 orderGoods.setStyleId(zyGoods.getStyleId());
			 orderGoods.setModelId(zyGoods.getModelId());
			 orderGoods.setUnit(zyGoods.getUnit());
			 orderGoods.setNumber(zyGoods.getNumber());
			 orderGoods.setUnitPrice(zyGoods.getUnitPrice());
			 orderGoods.setDiscount(zyGoods.getDiscount());
			 orderGoods.setPromotionPrice(zyGoods.getPromotionPrice());
			 orderGoods.setRecommend(zyGoods.getRecommend());
			 orderGoods.setPlaceOrigin(zyGoods.getPlaceOrigin());
			 orderGoods.setStatus(zyGoods.getStatus());
			 orderGoods.setTotalPrice(zyGoods.getUnitPrice()*orderGoodsJkList.get(j).getGoodsNum());
			 orderGoods.setPayTotalPrice(zyGoods.getPromotionPrice()*orderGoodsJkList.get(j).getGoodsNum());
			 orderGoodsService.save(orderGoods);
			 GoodsModule goodsModule = new GoodsModule();
			 QueryWrapper<GoodsModule> queryWrapperzsm = QueryGenerator.initQueryWrapper(goodsModule, req.getParameterMap());
			 queryWrapperzsm.eq("goods_id",zyGoods.getId());
			 queryWrapperzsm.eq("style_id",zyGoods.getStyleId());
			 List<GoodsModule> listzsm = goodsModuleService.list(queryWrapperzsm);
			 for (int k = 0 ; k < listzsm.size() ; k++){
				 OrderGoodsModule orderGoodsModule = new OrderGoodsModule();
				 orderGoodsModule.setOrderGoodsId(orderGoods.getId());
				 orderGoodsModule.setGoodsId(zyGoods.getId());
				 orderGoodsModule.setStyleId(zyGoods.getStyleId());
				 orderGoodsModule.setStyleModuleId(listzsm.get(k).getStyleModuleId());
				 orderGoodsModule.setModuleId(listzsm.get(k).getModuleId());
				 orderGoodsModuleService.save(orderGoodsModule);
			 }
			 GoodsFabric goodsFabric = new GoodsFabric();
			 QueryWrapper<GoodsFabric> queryWrapperzsf = QueryGenerator.initQueryWrapper(goodsFabric, req.getParameterMap());
			 queryWrapperzsf.eq("goods_id",zyGoods.getId());
			 List<GoodsFabric> listzsf = goodsFabricService.list(queryWrapperzsf);
			 for (int o = 0 ; o < listzsf.size() ; o++){
				 OrderGoodsFabric orderGoodsFabric = new OrderGoodsFabric();
				 orderGoodsFabric.setOrderGoodsId(orderGoods.getId());
				 orderGoodsFabric.setGoodsId(zyGoods.getId());
				 orderGoodsFabric.setFabricId(listzsf.get(o).getFabricId());
				 orderGoodsFabricService.save(orderGoodsFabric);
			 }
			 GoodsAccessories goodsAccessories = new GoodsAccessories();
			 QueryWrapper<GoodsAccessories> queryWrapperzsa = QueryGenerator.initQueryWrapper(goodsAccessories, req.getParameterMap());
			 queryWrapperzsa.eq("goods_id",zyGoods.getId());
			 List<GoodsAccessories> listzsa = goodsAccessoriesService.list(queryWrapperzsa);
			 for (int p = 0 ; p < listzsa.size() ; p++){
				 OrderGoodsAccessories orderGoodsAccessories = new OrderGoodsAccessories();
				 orderGoodsAccessories.setOrderGoodsId(orderGoods.getId());
				 orderGoodsAccessories.setGoodsId(zyGoods.getId());
				 orderGoodsAccessories.setAccessoriesId(listzsa.get(p).getAccessoriesId());
				 orderGoodsAccessoriesService.save(orderGoodsAccessories);
			 }
		 }
		 return zyOrders.getId();
	 }
	 /**
	  * 提交商品订单
	  *
	  * @param
	  * @return
	  */
	 @AutoLog(value = "提交商品订单")
	 @ApiOperation(value="提交商品订单", notes="提交商品订单")
	 @GetMapping(value = "/TJorder")
	 public Result<?> TJorder(@RequestParam(name="OrdersNum",required=true) String OrdersNum,HttpServletRequest req,@RequestParam(name="goodsid",required=true) String goodsid,@RequestParam(name="goodsNum",required=true) String goodsNum) {
		 //这里方便获取当前登陆信息
		 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 //这里获取当前登陆的id
		 String userid = loginUser.getId();
		 ZyOrdersJk zyOrdersJk = new ZyOrdersJk();
		 zyOrdersJk.setFreight(0.0);
		 zyOrdersJk.setOrdersType(0);
		 zyOrdersJk.setUserId(userid);
		 zyOrdersJk.setGoodsQuantity(1);
		 CustomerReceivingAddress customerReceivingAddress = new CustomerReceivingAddress();
		 QueryWrapper<CustomerReceivingAddress> queryWrappercra = QueryGenerator.initQueryWrapper(customerReceivingAddress, req.getParameterMap());
		 queryWrappercra.eq("user_id",userid);
		 queryWrappercra.orderByAsc("sort");
		 List<CustomerReceivingAddress> listcra = iCustomerReceivingAddressService.list(queryWrappercra);
		 zyOrdersJk.setAddressId(listcra.get(0).getId());
		 zyOrdersJk.setReceiver(userid);
		 CustomerInvoice customerInvoice = new CustomerInvoice();
		 QueryWrapper<CustomerInvoice> queryWrapperci = QueryGenerator.initQueryWrapper(customerInvoice, req.getParameterMap());
		 queryWrapperci.eq("user_id",userid);
		 queryWrapperci.orderByAsc("sort");
		 List<CustomerInvoice> listci = iCustomerInvoiceService.list(queryWrapperci);
		 zyOrdersJk.setInvoiceId(listci.get(0).getId());
		 SysDepart sysDepart = new SysDepart();
		 QueryWrapper<SysDepart> queryWrappersd = QueryGenerator.initQueryWrapper(sysDepart, req.getParameterMap());
		 queryWrappersd.eq("org_category","4");
		 queryWrappersd.orderByAsc("depart_order");
		 List<SysDepart> listsd = sysDepartService.list(queryWrappersd);
		 zyOrdersJk.setSalesEnterpriseId(listsd.get(0).getId());
		 SysUser sysUser = new SysUser();
		 QueryWrapper<SysUser> queryWrappersu = QueryGenerator.initQueryWrapper(sysUser, req.getParameterMap());
		 queryWrappersu.eq("org_code",listsd.get(0).getOrgCode());
		 List<SysUser> listsu = sysUserService.list(queryWrappersu);
		 zyOrdersJk.setSalespersonId(listsu.get(0).getId());
		 zyOrdersJk.setDeliveryStatus(0);
		 List<OrderGoodsJk> orderGoodsJkList = new ArrayList<>();
		 OrderGoodsJk orderGoodsJk = new OrderGoodsJk();
		 orderGoodsJk.setGoodsId(goodsid);
		 orderGoodsJk.setGoodsNum(Integer.parseInt(goodsNum));
		 CustomerMeasure customerMeasure = new CustomerMeasure();
		 QueryWrapper<CustomerMeasure> queryWrappercm = QueryGenerator.initQueryWrapper(customerMeasure, req.getParameterMap());
		 queryWrappercm.eq("user_id",userid);
		 queryWrappercm.orderByAsc("sort");
		 List<CustomerMeasure> listcm = iCustomerMeasureService.list(queryWrappercm);
		 orderGoodsJk.setMeasureId(listcm.get(0).getId());
		 orderGoodsJkList.add(orderGoodsJk);
		 String orderid = orderXD(zyOrdersJk,orderGoodsJkList,req,OrdersNum);
		 return Result.OK(orderid);
	 }

	 /**
	  * 支付
	  *
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "支付")
	 @ApiOperation(value="支付", notes="支付")
	 @GetMapping(value = "/zf")
	 public Result<?> zf(@RequestParam(name="ordersid",required=true) String ordersid,@RequestParam(name="goodsid",required=true) String goodsid,HttpServletRequest req) {
		 ZyOrders zyOrders =  zyOrdersService.getById(ordersid);
		 zyOrders.setPaymentStatus(1);
		 zyOrders.setPaymentTime(new Date());
		 zyOrders.setDeliveryStatus(1);
		 zyOrdersService.updateById(zyOrders);
		 ZyGoods zyGoods = zyGoodsService.getById(goodsid);
		 zyGoods.setNum(zyGoods.getNum()+1);
		 zyGoodsService.updateById(zyGoods);
		 return Result.OK("支付成功");
	 }


}
