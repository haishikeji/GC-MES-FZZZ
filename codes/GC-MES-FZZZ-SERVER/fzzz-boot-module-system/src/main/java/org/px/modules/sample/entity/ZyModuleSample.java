package org.px.modules.sample.entity;

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
 * @Description: 模块样板表
 * @@author 品讯科技
 * @Date:   2021-12-11
 * @Version: V1.0
 */
@Data
@TableName("zy_module_sample")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_module_sample对象", description="模块样板表")
public class ZyModuleSample implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private String nums;
	/**模块名称*/
	@Excel(name = "模块名称", width = 15)
    @ApiModelProperty(value = "模块名称")
    @Dict(dicCode = "id",dictTable = "zy_cloths_modular",dicText = "modular_name")
    private String moduleName;
    /**款式名称*/
    @Excel(name = "款式名称", width = 15)
    @ApiModelProperty(value = "款式名称")
    @Dict(dicCode = "id",dictTable = "zy_cloths_style",dicText = "style_names")
    private String styleNames;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private String descr;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private String url;
	/**成衣*/
	@Excel(name = "成衣", width = 15, dictTable = "zy_cloth_sample", dicText = "cloth_name", dicCode = "id")
	@Dict(dictTable = "zy_cloth_sample", dicText = "descr", dicCode = "id")
    @ApiModelProperty(value = "成衣")
    private String clothId;
	/**用户*/
	@Excel(name = "用户", width = 15)
    @ApiModelProperty(value = "用户")
    private String userId;
	/**用户类型*/
	@Excel(name = "用户类型", width = 15)
    @ApiModelProperty(value = "用户类型")
    private String type;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
