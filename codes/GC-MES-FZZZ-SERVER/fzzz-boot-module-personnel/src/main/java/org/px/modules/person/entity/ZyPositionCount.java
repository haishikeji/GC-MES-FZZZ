package org.px.modules.person.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: zy_position_count
 * @@author 品讯科技
 * @Date:   2021-11-10
 * @Version: V1.0
 */
@Data
@TableName("zy_position_count")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_position_count对象", description="zy_position_count")
public class ZyPositionCount implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
	/**userId*/
	@Excel(name = "userId", width = 15)
    @ApiModelProperty(value = "userId")
    private String userId;
	/**count*/
	@Excel(name = "count", width = 15)
    @ApiModelProperty(value = "count")
    private Integer count;
	/**workTime*/
	@Excel(name = "workTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "workTime")
    private Date workTime;
	/**positionId*/
	@Excel(name = "positionId", width = 15)
    @ApiModelProperty(value = "positionId")
    private String positionId;
}
