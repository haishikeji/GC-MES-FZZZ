package org.px.modules.workorderfinished.entity;

import java.io.Serializable;

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
 * @Description: 工单完工列表
 * @@author 品讯科技
 * @Date:   2021-11-12
 * @Version: V1.0
 */
@Data
@TableName("work_order_finished")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="work_order_finished对象", description="工单完工列表")
public class WorkOrderFinished implements Serializable {
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
	/**工单ID*/
	@Excel(name = "工单ID", width = 15)
    @ApiModelProperty(value = "工单ID")
    private java.lang.String workId;
	/**工单编号*/
	@Excel(name = "工单编号", width = 15)
    @ApiModelProperty(value = "工单编号")
    private java.lang.String workCode;
	/**当前工序ID*/
	@Excel(name = "当前工序ID", width = 15)
    @ApiModelProperty(value = "当前工序ID")
    private java.lang.String procedureId;
	/**当前工序（最后）*/
	@Excel(name = "当前工序（最后）", width = 15)
    @ApiModelProperty(value = "当前工序（最后）")
    private java.lang.String procedureCode;
	/**完工百分比*/
	@Excel(name = "完工百分比", width = 15)
    @ApiModelProperty(value = "完工百分比")
    private java.lang.String finished;
}
