package org.px.modules.demo.mall.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.px.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: zy_orders
 * @@author 品讯科技
 * @Date:   2021-11-11
 * @Version: V1.0
 */
@ApiModel(value="zy_orders对象", description="zy_orders")
@Data
@TableName("zy_orders")
public class ZyOrders implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**订货创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "订货创建日期")
    private java.util.Date createTime;
    /**订单编号*/
    @Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private java.lang.String ordersNum;
    /**金额*/
    @Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
    private java.lang.Double money;
    /**交货期（天）*/
    @Excel(name = "交货期（天）", width = 15)
    @ApiModelProperty(value = "交货期（天）")
    private java.lang.String deliveryTime;
    /**订单类型*/
    @Excel(name = "订单类型", width = 15)
    @ApiModelProperty(value = "订单类型")
    private java.lang.String ordersType;
    /**订单状态*/
    @Excel(name = "订单状态", width = 15, dicCode = "order_state")
    @Dict(dicCode = "order_state")
    @ApiModelProperty(value = "订单状态")
    private java.lang.String orderStatus;
    /**用户*/
    @Excel(name = "用户", width = 15)
    @ApiModelProperty(value = "用户")
    private java.lang.String userId;
    /**商品条目数量*/
    @Excel(name = "商品条目数量", width = 15)
    @ApiModelProperty(value = "商品条目数量")
    private java.lang.String goodsQuantity;
    /**交付状态*/
    @Excel(name = "交付状态", width = 15, dicCode = "jf_state")
    @Dict(dicCode = "jf_state")
    @ApiModelProperty(value = "交付状态")
    private java.lang.String jfState;
    /**客户联系方式*/
    @Excel(name = "客户联系方式", width = 15, dicCode = "kh_contact")
    @Dict(dicCode = "kh_contact")
    @ApiModelProperty(value = "客户联系方式")
    private java.lang.String contact;
    /**支付状态*/
    @Excel(name = "支付状态", width = 15, dicCode = "zf_state")
    @Dict(dicCode = "zf_state")
    @ApiModelProperty(value = "支付状态")
    private java.lang.String zfState;
}
