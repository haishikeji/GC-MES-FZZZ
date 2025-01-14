package org.px.modules.demo.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.px.common.aspect.annotation.Dict;
import org.px.modules.demo.base.entity.vo.ZyClothActionVo;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: zy_process
 * @@author 品讯科技
 * @Date: 2021-11-03
 * @Version: V1.0
 */
@Data
@TableName("zy_process")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "zy_process对象", description = "zy_process")
public class ZyProcess implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 工序表id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "工序表id")
    private java.lang.String id;
    /**
     * 工序代码
     */
    @Excel(name = "工序代码", width = 15)
    @ApiModelProperty(value = "工序代码")
    private java.lang.String processCode;
    /**
     * 工序名称
     */
    @Excel(name = "工序名称", width = 15)
    @ApiModelProperty(value = "工序名称")
    private java.lang.String processName;
    /**
     * 工艺描述
     */
    @Excel(name = "工艺描述", width = 15)
    @ApiModelProperty(value = "工艺描述")
    private java.lang.String processDescribe;
    /**
     * 做工说明
     */
    @Excel(name = "做工说明", width = 15)
    @ApiModelProperty(value = "做工说明")
    private java.lang.String mkExplain;
    /**
     * 品质要求
     */
    @Excel(name = "品质要求", width = 15)
    @ApiModelProperty(value = "品质要求")
    private java.lang.String qualityRequire;
    /**
     * 工序时间
     */
    @Excel(name = "工序时间", width = 15)
    @ApiModelProperty(value = "工序时间")
    private java.lang.String processTime;
    /**
     * 工序单价
     */
    @Excel(name = "工序单价", width = 15)
    @ApiModelProperty(value = "工序单价")
    private java.lang.Double price;
    /**
     * 工序等级
     */
    @Excel(name = "工序等级", width = 15)
    @ApiModelProperty(value = "工序等级")
    @Dict(dicCode = "fabric_grade")
    private java.lang.String grade;
//	/**面料代码*/
//	@Excel(name = "面料代码", width = 15)
//    @ApiModelProperty(value = "面料代码")
//    private java.lang.String fabricNum;
//	/**面料等级*/
//	@Excel(name = "面料等级", width = 15)
//    @ApiModelProperty(value = "面料等级")
//    private java.lang.String fabricGrade;
    /**
     * 针距（针/厘米）
     */
    @Excel(name = "针距（针/厘米）", width = 15)
    @ApiModelProperty(value = "针距（针/厘米）")
    private java.lang.Integer needlePitch;
//	/**机器名*/
//	@Excel(name = "机器名", width = 15, dictTable = "zy_devicetype", dicText = "name", dicCode = "id")
//	@Dict(dictTable = "zy_devicetype", dicText = "name", dicCode = "id")
//    @ApiModelProperty(value = "机器名")
//    private java.lang.String machineId;
    /**
     * 手工宽放
     */
    @Excel(name = "手工宽放", width = 15)
    @ApiModelProperty(value = "手工宽放")
    private java.lang.String manualWide;
    /**
     * 手工时间
     */
    @Excel(name = "手工时间", width = 15)
    @ApiModelProperty(value = "手工时间")
    private java.lang.Double manualTime;
    /**
     * 机器转速
     */
    @Excel(name = "机器转速", width = 15)
    @ApiModelProperty(value = "机器转速")
    private java.lang.Integer machineSpeed;
    /**
     * 机器宽放
     */
    @Excel(name = "机器宽放", width = 15)
    @ApiModelProperty(value = "机器宽放")
    private java.lang.String machineWide;
    /**
     * 机器时间
     */
    @Excel(name = "机器时间", width = 15)
    @ApiModelProperty(value = "机器时间")
    private java.lang.Double machineTime;
    /**
     * 机器浮于
     */
    @Excel(name = "机器浮于", width = 15)
    @ApiModelProperty(value = "机器浮于")
    private java.lang.String machineFloat;
    /**
     * 绑包宽放
     */
    @Excel(name = "绑包宽放", width = 15)
    @ApiModelProperty(value = "绑包宽放")
    private java.lang.String bundleWide;
    /**
     * 绑包时间
     */
    @Excel(name = "绑包时间", width = 15)
    @ApiModelProperty(value = "绑包时间")
    private java.lang.String bundleTime;
//	/**部件表*/
//	@Excel(name = "部件表", width = 15, dictTable = "zy_cloths_component", dicText = "parts_name", dicCode = "id")
//	@Dict(dictTable = "zy_cloths_component", dicText = "parts_name", dicCode = "id")
//    @ApiModelProperty(value = "部件表")
//    private java.lang.String componentId;
//	/**是否为瓶颈工序*/
//	@Excel(name = "是否为瓶颈工序", width = 15)
//    @ApiModelProperty(value = "是否为瓶颈工序")
//    private java.lang.Integer isBottleneck;
    /**
     * 企业名称
     */
    @Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    @Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    private java.lang.String enterpriseId;
    /**工具名称*/
//	@Excel(name = "工具名称", width = 15, dictTable = "zy_tool", dicText = "name", dicCode = "id")
//	@Dict(dictTable = "zy_tool", dicText = "name", dicCode = "id")
//    @ApiModelProperty(value = "工具名称")
//    private java.lang.String toolId;
    /**
     * 款式名称
     */
    @Excel(name = "类型名称", width = 15)
    @ApiModelProperty(value = "类型名称")
    @Dict(dictTable = "zy_cloths_type", dicText = "type_name", dicCode = "nums")
    private java.lang.String styleId;
    /**
     * 工段代码
     */
    @Excel(name = "工段代码", width = 15)
    @ApiModelProperty(value = "工段代码")
    private java.lang.String worksectionCode;
    /**合同号*/
//	@Excel(name = "合同号", width = 15)
//    @ApiModelProperty(value = "合同号")
//    private java.lang.String contractNum;
    /**
     * 合计（手工TMU）
     */
    @Excel(name = "合计（手工TMU）", width = 15)
    @ApiModelProperty(value = "合计（手工TMU）")
    private java.lang.Integer totalManualTmu;
    /**
     * 合计（机器TMU）
     */
    @Excel(name = "合计（机器TMU）", width = 15)
    @ApiModelProperty(value = "合计（机器TMU）")
    private java.lang.Integer totalMachineTmu;
    /**
     * 合计（手工秒）
     */
    @Excel(name = "合计（手工秒）", width = 15)
    @ApiModelProperty(value = "合计（手工秒）")
    private java.lang.Integer totalMaunal;
    /**
     * 合计（机器秒）
     */
    @Excel(name = "合计（机器秒）", width = 15)
    @ApiModelProperty(value = "合计（机器秒）")
    private java.lang.Integer totalMachine;
    /**
     * 图片
     */
    @Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private java.lang.String image;
    /**
     * 视频
     */
    @Excel(name = "视频", width = 15)
    @ApiModelProperty(value = "视频")
    private java.lang.String vedio;

    @TableField(exist = false)
    private List<ZyClothActionVo> zyClothAction;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;

    @Excel(name = "工序类型", width = 15)
    @ApiModelProperty(value = "工序类型")
    private int processType;
}
