package org.px.modules.demo.pro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.px.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: zy_process_machine
 * @@author 品讯科技
 * @Date:   2021-12-11
 * @Version: V1.0
 */
@Data
@TableName("zy_process_machine")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_process_machine对象", description="zy_process_machine")
public class ZyProcessMachine implements Serializable {
    private static final long serialVersionUID = 1L;

	/**工序机器表*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "工序机器表")
    private String id;
	/**工序*/
	@Excel(name = "工序", width = 15, dictTable = "zy_process", dicText = "process_name", dicCode = "id")
	@Dict(dictTable = "zy_process", dicText = "process_name", dicCode = "id")
    @ApiModelProperty(value = "工序")
    private String processId;
	/**机器*/
	@Excel(name = "设备", width = 15, dictTable = "zy_devicetype", dicText = "name", dicCode = "id")
	@Dict(dictTable = "zy_device", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "设备")
    private String machineId;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @Dict(dictTable = "zy_process", dicText = "process_code", dicCode = "id")
    @ApiModelProperty(value = "工序编号")
    private String processCode;
    private String code;
    private String codeName;
}
