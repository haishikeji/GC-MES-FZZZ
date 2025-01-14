package org.px.modules.product.pdmachine.entity;


import java.io.Serializable;
import java.util.Date;

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
 * @Description: 产品设备表
 * @@author 品讯科技
 * @Date:   2022-12-20
 * @Version: V1.0
 */
@Data
@TableName("product_machine")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="product_machine对象", description="产品设备表")
public class ProductMachine implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**产品id*/
	@Excel(name = "产品id", width = 15)
    @ApiModelProperty(value = "产品id")
        @Dict(dictTable = "zy_product",dicText = "product_code",dicCode = "id")
    private String productId;
	/**设备d*/
	@Excel(name = "设备d", width = 15)
    @ApiModelProperty(value = "设备d")
    @Dict(dictTable = "zy_device", dicText = "name", dicCode = "id")
    private String machineId;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private Integer sort;
	/**准备情况*/
	@Excel(name = "准备情况", width = 15)
    @ApiModelProperty(value = "准备情况")
    @Dict( dicText = "", dicCode = "ready")
    private Integer preparation;
}
