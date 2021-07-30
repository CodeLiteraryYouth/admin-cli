package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="")
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色编码")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("role_desc")
    private String roleDesc;

    @ApiModelProperty(value = "系统默认值")
    @TableField("sys_default")
    private String sysDefault;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "角色创建者")
    @TableField("creater")
    private String creater;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField("updater")
    private String updater;

    @ApiModelProperty(value = "删除标记")
    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;

}
