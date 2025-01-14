package org.px.modules.person.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 人员分配表
 * @@author 品讯科技
 * @Date:   2021-10-23
 * @Version: V1.0
 */
@Data
@TableName("zy_distribution")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_distribution对象", description="人员分配表")
public class ZyDistribution implements Serializable {
    private static final long serialVersionUID = 1L;

	/**人员分配表*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "人员分配表")
    private String id;
	/**衣服id*/
	@Excel(name = "衣服id", width = 15)
    @ApiModelProperty(value = "衣服id")
    private String clothId;
	/**工序序号*/
	@Excel(name = "工序序号", width = 15)
    @ApiModelProperty(value = "工序序号")
    private Integer orderNum;
	/**工序id*/
	@Excel(name = "工序id", width = 15)
    @ApiModelProperty(value = "工序id")
    private String processId;
	/**机器id*/
	@Excel(name = "机器id", width = 15)
    @ApiModelProperty(value = "机器id")
    private String machineId;
	/**员工id*/
	@Excel(name = "员工id", width = 15)
    @ApiModelProperty(value = "员工id")
    private String userId;


    /**员工名字*/
    @Excel(name = "员工名字", width = 15)
    @ApiModelProperty(value = "员工名字")
    @TableField(exist = false)
    private String userName;

    /**机器名字*/
    @Excel(name = "机器名字", width = 15)
    @ApiModelProperty(value = "机器名字")
    @TableField(exist = false)
    private String machineName;

    /**工序名字*/
    @Excel(name = "工序名字", width = 15)
    @ApiModelProperty(value = "工序名字")
    @TableField(exist = false)
    private String processName;
}
