package org.px.modules.demo.mall.vo.cloth;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 部件面料中间表
 * @@author 品讯科技
 * @Date:   2021-07-16
 * @Version: V1.0
 */
@Data
@TableName("zy_component_fabric")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_component_fabric对象", description="部件面料中间表")
public class ComponentFabricUtil implements Serializable {
    private static final long serialVersionUID = 1L;

	/**部件id*/
	@Excel(name = "部件id", width = 15)
    @ApiModelProperty(value = "部件id")
    private String componentId;
	/**面料id*/
	@Excel(name = "面料id", width = 15)
    @ApiModelProperty(value = "面料id")
    private String fabricId;
}
