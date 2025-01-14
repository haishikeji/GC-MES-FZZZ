package org.px.modules.productplan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.px.common.exception.JeecgBootException;
import org.px.modules.demo.ordergoods.entity.OrderGoods;
import org.px.modules.demo.ordergoods.service.IOrderGoodsService;
import org.px.modules.demo.zyorders.entity.ZyOrders;
import org.px.modules.demo.zyorders.service.IZyOrdersService;
import org.px.modules.productplan.entity.ZyPlanProcess;
import org.px.modules.productplan.entity.ZyProductPlan;
import org.px.modules.productplan.entity.vo.PaiWeiTuVo;
import org.px.modules.productplan.enums.ProductPlanStatusEnum;
import org.px.modules.productplan.mapper.ZyProductPlanMapper;
import org.px.modules.productplan.service.IZyPlanProcessService;
import org.px.modules.productplan.service.IZyProductPlanService;
import org.px.modules.system.entity.SysDepart;
import org.px.modules.system.entity.SysUser;
import org.px.modules.system.service.ISysDepartService;
import org.px.modules.system.service.ISysUserService;
import org.px.modules.team.entity.Groupx;
import org.px.modules.team.service.IGroupxService;
import org.px.modules.workorder.entity.WorkOrder;
import org.px.modules.workorder.enums.WorkOrderStatusEnum;
import org.px.modules.workorder.service.IWorkOrderService;
import org.px.modules.workproduct.entity.ZyProduct;
import org.px.modules.workproduct.service.IZyProductService;
import org.px.modules.zyclothsstyle.entity.ZyClothsStyle;
import org.px.modules.zyclothsstyle.service.IZyClothsStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 生产计划
 * @@author 品讯科技
 * @Date: 2022-12-12
 * @Version: V1.0
 */
@Service
public class ZyProductPlanServiceImpl extends ServiceImpl<ZyProductPlanMapper, ZyProductPlan> implements IZyProductPlanService {

    @Autowired
    private IZyProductPlanService iZyProductPlanService;

    @Autowired
    private IZyPlanProcessService iZyPlanProcessService;

    //   订单基本信息
    @Autowired
    private IZyOrdersService iZyOrdersService;

    // 工单信息
    @Autowired
    private IWorkOrderService iWorkOrderService;

    //商品订单信息
    @Autowired
    private IOrderGoodsService iOrderGoodsService;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IZyClothsStyleService iZyClothsStyleService;
    @Autowired
    private IGroupxService iGroupxService;

    @Autowired
    private IZyProductService iZyProductService;

    @Autowired
    private ISysDepartService iSysDepartService;

    @Override
    public PaiWeiTuVo paiweitu(ZyProductPlan plan) {
        ZyProductPlan productPlan = iZyProductPlanService.getById(plan.getId());
        if (ObjectUtils.isEmpty(productPlan)) throw new JeecgBootException(plan.getId() + "生产计划不存在");
//        工单信息
        WorkOrder workOrder = iWorkOrderService.getOne(new LambdaQueryWrapper<WorkOrder>().eq(WorkOrder::getProductCode, productPlan.getProductCode()));
        if (ObjectUtils.isEmpty(workOrder))
            throw new JeecgBootException(productPlan.getProductCode() + "工单信息不存在");

        String ordersId = workOrder.getOrdersId();
        OrderGoods orderGoods = iOrderGoodsService.getOne(new LambdaQueryWrapper<OrderGoods>().eq(OrderGoods::getOrdersId, ordersId));
        if (ObjectUtils.isEmpty(orderGoods)) throw new JeecgBootException(ordersId + ":订单商品信息不存在");

        ZyOrders zyOrders = iZyOrdersService.getOne(new LambdaQueryWrapper<ZyOrders>().eq(ZyOrders::getId, ordersId));
        if (ObjectUtils.isEmpty(zyOrders)) throw new JeecgBootException(ordersId + "订单信息不存在");

        PaiWeiTuVo vo = new PaiWeiTuVo();
        ZyClothsStyle zyClothsStyle = iZyClothsStyleService.getById(orderGoods.getStyleId());
        vo.setStyle(StringUtils.isNotBlank(zyClothsStyle.getStyleNames()) ? zyClothsStyle.getStyleNames() : "");
        vo.setGoodsNum(orderGoods.getGoodsNum());
//        SysUser sysUser = iSysUserService.getById(zyOrders.getUserId());
//        vo.setUser(StringUtils.isNotBlank(sysUser.getRealname()) ? sysUser.getRealname() : "");
        vo.setUser(zyOrders.getUserId());
        AtomicInteger memberNum = new AtomicInteger(0);
        List<ZyPlanProcess> planProcessList = iZyPlanProcessService
                .list(new LambdaQueryWrapper<ZyPlanProcess>()
                        .eq(ZyPlanProcess::getPlanId, plan.getId()));
        if (!ObjectUtils.isEmpty(planProcessList)) {
            planProcessList.forEach(e -> {
                if (StringUtils.isNotBlank(e.getUserIds())) {
                    List<String> userList = Arrays.asList(e.getUserIds().split(","));
                    memberNum.addAndGet(userList.size());
                }
            });
            vo.setList(planProcessList);
        }
        vo.setMemberNum(memberNum.intValue());
        Groupx groupx = iGroupxService.getById(productPlan.getTeamId());
        vo.setTeamName(StringUtils.isNotBlank(groupx.getGroupName()) ? groupx.getGroupName() : "");
        vo.setDuration(productPlan.getDuration());
        return vo;
    }

    @Override
    public void create(ZyProductPlan zyProductPlan) {
        //工单编号
        String productCode = zyProductPlan.getProductCode();
        WorkOrder workOrder = iWorkOrderService.getOne(new LambdaQueryWrapper<WorkOrder>().eq(WorkOrder::getProductCode, productCode));
        Optional.ofNullable(workOrder).orElseThrow(() -> new JeecgBootException(productCode + ":工单编号不存在"));
        ZyProduct zyProduct = iZyProductService.getOne(new LambdaQueryWrapper<ZyProduct>().eq(ZyProduct::getWorkOrderId, workOrder.getId()));
        Optional.ofNullable(zyProduct).orElseThrow(() -> new JeecgBootException(productCode + ":工单对应的产品不存在"));
        zyProductPlan.setProductNo(StringUtils.isNotBlank(zyProduct.getProductCode()) ? zyProduct.getProductCode() : "");
        zyProductPlan.setProductName(StringUtils.isNotBlank(zyProduct.getProductName()) ? zyProduct.getProductName() : "");
        if (StringUtils.isNotBlank(zyProduct.getEnterprisesId())) {
            SysDepart sysDepart = iSysDepartService.getById(zyProduct.getEnterprisesId());
            zyProductPlan.setProductOrg(StringUtils.isNotBlank(sysDepart.getDepartName()) ? sysDepart.getDepartName() : "");
        }
        SysUser sysUser = iSysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getRealname, zyProductPlan.getTeamLeader()));
        zyProductPlan.setTeamLeader(sysUser.getUsername());
        zyProductPlan.setStatus(new Integer(ProductPlanStatusEnum.UNAUDITED.getCode()));
        this.save(zyProductPlan);

        //更新work_order表work_order_status为“1” (不能为其新增生产计划)
        iWorkOrderService.lambdaUpdate()
                .set(WorkOrder::getWorkOrderStatus, WorkOrderStatusEnum.REVIEWED.getCode())
                .eq(WorkOrder::getId, workOrder.getId())
                .update();
    }
}
