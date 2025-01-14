package org.px.modules.demo.pro.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

@Data
public class NewZyProcessComponent {
    /**工序代码*/
    @Excel(name = "工序代码", width = 15)
    @ApiModelProperty(value = "工序代码")
    private java.lang.String processCode;
    /**工序名称*/
    @Excel(name = "工序名称", width = 15)
    @ApiModelProperty(value = "工序名称")
    private java.lang.String processName;
    /**工艺描述*/
    @Excel(name = "工艺描述", width = 15)
    @ApiModelProperty(value = "工艺描述")
    private java.lang.String processDescribe;
    /**做工说明*/
    @Excel(name = "做工说明", width = 15)
    @ApiModelProperty(value = "做工说明")
    private java.lang.String mkExplain;
    /**品质要求*/
    @Excel(name = "品质要求", width = 15)
    @ApiModelProperty(value = "品质要求")
    private java.lang.String qualityRequire;
    /**工序时间*/
    @Excel(name = "工序时间", width = 15)
    @ApiModelProperty(value = "工序时间")
    private java.lang.String processTime;
    /**工序单价*/
    @Excel(name = "工序单价", width = 15)
    @ApiModelProperty(value = "工序单价")
    private java.lang.Double price;
    /**工序等级*/
    @Excel(name = "工序等级", width = 15)
    @ApiModelProperty(value = "工序等级")
    private java.lang.String grade;
    /**针距（针/厘米）*/
    @Excel(name = "针距（针/厘米）", width = 15)
    @ApiModelProperty(value = "针距（针/厘米）")
    private java.lang.Integer needlePitch;
    /**手工宽放*/
    @Excel(name = "手工宽放", width = 15)
    @ApiModelProperty(value = "手工宽放")
    private java.lang.String manualWide;
    /**手工时间*/
    @Excel(name = "手工时间", width = 15)
    @ApiModelProperty(value = "手工时间")
    private java.lang.Double manualTime;
    /**机器转速*/
    @Excel(name = "机器转速", width = 15)
    @ApiModelProperty(value = "机器转速")
    private java.lang.Integer machineSpeed;
    /**机器宽放*/
    @Excel(name = "机器宽放", width = 15)
    @ApiModelProperty(value = "机器宽放")
    private java.lang.String machineWide;
    /**机器时间*/
    @Excel(name = "机器时间", width = 15)
    @ApiModelProperty(value = "机器时间")
    private java.lang.Double machineTime;
    /**机器浮于*/
    @Excel(name = "机器浮于", width = 15)
    @ApiModelProperty(value = "机器浮于")
    private java.lang.String machineFloat;
    /**绑包宽放*/
    @Excel(name = "绑包宽放", width = 15)
    @ApiModelProperty(value = "绑包宽放")
    private java.lang.String bundleWide;
    /**绑包时间*/
    @Excel(name = "绑包时间", width = 15)
    @ApiModelProperty(value = "绑包时间")
    private java.lang.String bundleTime;
    /**企业名称*/
    @Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    private java.lang.String enterpriseId;
    /**工具名称*/
    /**款式名称*/
    @Excel(name = "款式名称", width = 15)
    @ApiModelProperty(value = "款式名称")
    private java.lang.String styleId;
    /**工段代码*/
    @Excel(name = "工段代码", width = 15)
    @ApiModelProperty(value = "工段代码")
    private java.lang.String worksectionCode;

    /**合计（手工TMU）*/
    @Excel(name = "合计（手工TMU）", width = 15)
    @ApiModelProperty(value = "合计（手工TMU）")
    private java.lang.Integer totalManualTmu;
    /**合计（机器TMU）*/
    @Excel(name = "合计（机器TMU）", width = 15)
    @ApiModelProperty(value = "合计（机器TMU）")
    private java.lang.Integer totalMachineTmu;
    /**合计（手工秒）*/
    @Excel(name = "合计（手工秒）", width = 15)
    @ApiModelProperty(value = "合计（手工秒）")
    private java.lang.Integer totalMaunal;
    /**合计（机器秒）*/
    @Excel(name = "合计（机器秒）", width = 15)
    @ApiModelProperty(value = "合计（机器秒）")
    private java.lang.Integer totalMachine;


    /**编号*/
    @Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private java.lang.String nums;
    /**部件名称*/
    @Excel(name = "部件名称", width = 15)
    @ApiModelProperty(value = "部件名称")
    private java.lang.String partsName;
    /**描述*/
    @Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;


}
