package com.dmj.cli.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zd
 */
@Data
public class SysPermissionDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "权限类型(directory|menu|button)")
    @TableField("permission_type")
    private String permissionType;

    @ApiModelProperty(value = "权限标识")
    @TableField("permission_str")
    private String permissionStr;

    @ApiModelProperty(value = "父类权限ID")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "权限标识排序")
    @TableField("permission_order")
    private Integer permissionOrder;

    @ApiModelProperty(value = "是否展示该权限")
    @TableField("is_view")
    private Boolean isView;

    @ApiModelProperty(value = "创建者")
    @TableField("creater")
    private String creater;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    @TableField("updater")
    private String updater;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标记")
    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;

    private List<String> roles;
}
