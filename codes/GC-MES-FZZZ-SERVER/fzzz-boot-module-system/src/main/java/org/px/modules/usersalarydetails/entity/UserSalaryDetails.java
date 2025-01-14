package org.px.modules.usersalarydetails.entity;

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
 * @Description: 员工工资明细表
 * @@author 品讯科技
 * @Date:   2021-11-06
 * @Version: V1.0
 */
@Data
@TableName("user_salary_details")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="user_salary_details对象", description="员工工资明细表")
public class UserSalaryDetails implements Serializable {
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
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
    private java.lang.String workNo;
	/**员工ID*/
	@Excel(name = "员工ID", width = 15)
    @ApiModelProperty(value = "员工ID")
    private java.lang.String userId;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
    private java.lang.String userName;
	/**薪资规则*/
	@Excel(name = "薪资规则", width = 15)
    @ApiModelProperty(value = "薪资规则")
    private java.lang.String ruleName;
	/**薪资要素*/
	@Excel(name = "薪资要素", width = 15)
    @ApiModelProperty(value = "薪资要素")
    private java.lang.String ruleFactor;
	/**薪资金额*/
	@Excel(name = "薪资金额", width = 15)
    @ApiModelProperty(value = "薪资金额")
    private java.lang.String ruleMoney;
	/**薪资日期*/
	@Excel(name = "薪资日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "薪资日期")
    private java.util.Date salaryDate;
	/**个税扣款*/
	@Excel(name = "个税扣款", width = 15)
    @ApiModelProperty(value = "个税扣款")
    private java.lang.String tax;
	/**五险一金*/
	@Excel(name = "五险一金", width = 15)
    @ApiModelProperty(value = "五险一金")
    private java.lang.String insurancesFund;
}
