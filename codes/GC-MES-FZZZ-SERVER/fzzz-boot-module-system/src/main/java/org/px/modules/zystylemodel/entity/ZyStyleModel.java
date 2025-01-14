package org.px.modules.zystylemodel.entity;

import java.io.Serializable;

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
 * @Description: zy_style_model
 * @@author 品讯科技
 * @Date:   2022-09-17
 * @Version: V1.0
 */
@Data
@TableName("zy_style_model")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_style_model对象", description="zy_style_model")
public class ZyStyleModel implements Serializable {
    private static final long serialVersionUID = 1L;

	/**内部编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "内部编号")
    private java.lang.String id;
	/**款式id*/
	@Excel(name = "款式id", width = 15)
    //@Dict(dictTable = "zy_cloths_style", dicText = "style_names", dicCode = "id")
    @ApiModelProperty(value = "款式id")
    private java.lang.String styleId;
	/**是否默认尺码*/
	@Excel(name = "是否默认尺码", width = 15)
    @ApiModelProperty(value = "是否默认尺码")
    //dictCode: 'isdefault',
    private java.lang.Integer isdefault;

    @TableField(exist = false)
    private String isDefaultSize;

	/**型号编码*/
	@Excel(name = "型号编码", width = 15)
    @ApiModelProperty(value = "型号编码")
    //dictCode: 'modenumber'
    private java.lang.String modelNumber;

	/**码数*/
	@Excel(name = "码数", width = 15)
    @ApiModelProperty(value = "码数")
    //dictCode: 'size'
    private java.lang.Integer size;

	/**型*/
	@Excel(name = "型", width = 15)
    @ApiModelProperty(value = "型")
    //dictCode: 'hsize'
    private java.lang.String anumbers;

	/**号*/
	@Excel(name = "号", width = 15)
    @ApiModelProperty(value = "号")
    //dictCode: 'xsize'
    private java.lang.String bnumbers;

	/**领大*/
	@Excel(name = "领大", width = 15)
    @ApiModelProperty(value = "领大")
    private java.lang.Float collarLarge;
	/**胸围*/
	@Excel(name = "胸围", width = 15)
    @ApiModelProperty(value = "胸围")
    private java.lang.Float bust;
	/**袖长*/
	@Excel(name = "袖长", width = 15)
    @ApiModelProperty(value = "袖长")
    private java.lang.Float sleeveLength;
	/**连肩袖长*/
	@Excel(name = "连肩袖长", width = 15)
    @ApiModelProperty(value = "连肩袖长")
    private java.lang.Float shslLength;
	/**总肩宽*/
	@Excel(name = "总肩宽", width = 15)
    @ApiModelProperty(value = "总肩宽")
    private java.lang.Float tsWidth;
	/**袖口*/
	@Excel(name = "袖口", width = 15)
    @ApiModelProperty(value = "袖口")
    private java.lang.Float cuff;
	/**腰围*/
	@Excel(name = "腰围", width = 15)
    @ApiModelProperty(value = "腰围")
    private java.lang.Float waistline;
	/**下摆*/
	@Excel(name = "下摆", width = 15)
    @ApiModelProperty(value = "下摆")
    private java.lang.Float hem;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;

    /**更新时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
    @Excel(name = "型号对应的三位数字", width = 15)
    @ApiModelProperty(value = "型号对应的三位数字")
    private String modelCode;


}
