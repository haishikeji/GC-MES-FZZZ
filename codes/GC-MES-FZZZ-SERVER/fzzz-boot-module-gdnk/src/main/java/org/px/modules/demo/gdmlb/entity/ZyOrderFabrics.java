package org.px.modules.demo.gdmlb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.px.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: zy_order_fabrics
 * @@author 品讯科技
 * @Date:   2021-11-12
 * @Version: V1.0
 */
@Data
@TableName("zy_order_fabrics")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_order_fabrics对象", description="zy_order_fabrics")
public class ZyOrderFabrics implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**工单编号*/
	@Excel(name = "工单编号", width = 15, dictTable = "zy_work_order", dicText = "code", dicCode = "id")
	@Dict(dictTable = "zy_work_order", dicText = "code", dicCode = "id")
    @ApiModelProperty(value = "工单编号")
    private String workOrderId;
	/**商品*/
	@Excel(name = "商品", width = 15, dictTable = "zy_goods", dicText = "goods_name", dicCode = "id")
	@Dict(dictTable = "zy_goods", dicText = "goods_name", dicCode = "id")
    @ApiModelProperty(value = "商品")
    private String merchandiseId;
	/**工序*/
	@Excel(name = "工序", width = 15)
    @ApiModelProperty(value = "工序")
    private String processId;
	/**面料*/
	@Excel(name = "面料", width = 15, dictTable = "zy_fabric", dicText = "name", dicCode = "id")
	@Dict(dictTable = "zy_fabric", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "面料")
    private String fabricId;
	/**库存*/
	@Excel(name = "库存", width = 15)
    @ApiModelProperty(value = "库存")
    private Integer count;
	/**仓库*/
	@Excel(name = "仓库", width = 15)
    @ApiModelProperty(value = "仓库")
    private String warehouseId;
}
