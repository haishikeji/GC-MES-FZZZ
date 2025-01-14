package org.px.modules.maker.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: 制单管理
 * @@author 品讯科技
 * @Date:   2021-11-27
 * @Version: V1.0
 */
@Data
@TableName("zy_maker")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_maker对象", description="制单管理")
public class ZyMaker implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
//	/**所属部门*/
//    @ApiModelProperty(value = "所属部门")
//    private String sysOrgCode;
	/**制单编号*/
    //制单编号 工单编号（22）+顺序号（2）
	@Excel(name = "制单编号", width = 15)
    @ApiModelProperty(value = "制单编号")
    private String code;
	/**工单id*/
	@Excel(name = "工单id", width = 15)
    @ApiModelProperty(value = "工单id")
    private String workerId;
	/**工单编号*/
	@Excel(name = "工单编号", width = 15)
    @ApiModelProperty(value = "工单编号")
    private String workerCode;
	/**产品编号*/
	@TableField(exist = false)
    private String productCode;
    /**产品名称*/
	@TableField(exist = false)
    private String productName;
	/**模块id*/
	@Excel(name = "模块id", width = 15)
    @ApiModelProperty(value = "模块id")
    private String modelId;
	/**模块名称*/
	@Excel(name = "模块名称", width = 15)
    @ApiModelProperty(value = "模块名称")
    private String modelName;
}
