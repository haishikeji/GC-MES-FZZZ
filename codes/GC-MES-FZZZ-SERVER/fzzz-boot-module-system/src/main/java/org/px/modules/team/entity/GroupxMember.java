package org.px.modules.team.entity;

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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 班组成员表管理
 * @@author 品讯科技
 * @Date: 2022-12-03
 * @Version: V1.0
 */
@Data
@TableName("groupx_member")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "groupx_member对象", description = "班组成员管理")
public class GroupxMember {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 班组id FK 班组表
     */
    @Excel(name = "班组id FK 班组表", width = 15)
    @ApiModelProperty(value = "班组id FK 班组表")
    private String groupxId;
    /**
     * 用户id FK 用户表
     */
    @Excel(name = "用户id FK 用户表", width = 15)
    @ApiModelProperty(value = "用户id FK 用户表")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    private String userId;

    @TableField(exist = false)
    private String workNo;
    /**
     * 加入时间
     */
    @Excel(name = "加入时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "加入时间")
    private Date joinTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
