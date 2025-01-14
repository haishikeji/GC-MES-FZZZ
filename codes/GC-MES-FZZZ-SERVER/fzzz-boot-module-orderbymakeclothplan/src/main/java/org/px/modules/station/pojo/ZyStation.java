package org.px.modules.station.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: zy_station
 * @@author 品讯科技
 * @Date:   2021-08-05
 * @Version: V1.0
 */
@Data
@TableName("zy_station")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_station对象", description="zy_station")
public class ZyStation implements Serializable {
    private static final long serialVersionUID = 1L;

	/**工位表*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "工位表")
    private String id;
	/**工位编号*/
	@Excel(name = "工位编号", width = 15)
    @ApiModelProperty(value = "工位编号")
    private String code;
	/**机器id*/
	@Excel(name = "机器id", width = 15)
    @ApiModelProperty(value = "机器id")
    private String machineId;
	/**员工id*/
	@Excel(name = "员工id", width = 15)
    @ApiModelProperty(value = "员工id")
    private String userId;
}
