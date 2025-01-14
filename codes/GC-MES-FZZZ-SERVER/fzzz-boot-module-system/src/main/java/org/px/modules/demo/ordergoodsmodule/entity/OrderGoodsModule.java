package org.px.modules.demo.ordergoodsmodule.entity;

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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 订单商品模块表
 * @@author 品讯科技
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Data
@TableName("order_goods_module")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="order_goods_module对象", description="订单商品模块表")
public class OrderGoodsModule implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**订单商品id*/
	@Excel(name = "订单商品id", width = 15, dictTable = "order_goods", dicText = "goods_name", dicCode = "id")
	@Dict(dictTable = "order_goods", dicText = "goods_name", dicCode = "id")
    @ApiModelProperty(value = "订单商品id")
    private java.lang.String orderGoodsId;
	/**商品id*/
	@Excel(name = "商品id", width = 15, dictTable = "zy_goods", dicText = "goods_name", dicCode = "id")
	@Dict(dictTable = "zy_goods", dicText = "goods_name", dicCode = "id")
    @ApiModelProperty(value = "商品id")
    private java.lang.String goodsId;
	/**款式id*/
	@Excel(name = "款式id", width = 15, dictTable = "zy_cloths_style", dicText = "style_names", dicCode = "id")
	@Dict(dictTable = "zy_cloths_style", dicText = "style_names", dicCode = "id")
    @ApiModelProperty(value = "款式id")
    private java.lang.String styleId;
	/**款式模块id*/
	@Excel(name = "款式模块id", width = 15)
    @ApiModelProperty(value = "款式模块id")
    private java.lang.String styleModuleId;
	/**模块id*/
	@Excel(name = "模块id", width = 15, dictTable = "zy_cloths_modular ", dicText = "modular_name", dicCode = "id")
	@Dict(dictTable = "zy_cloths_modular ", dicText = "modular_name", dicCode = "id")
    @ApiModelProperty(value = "模块id")
    private java.lang.String moduleId;
}
