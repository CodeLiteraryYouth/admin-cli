package com.dmj.cli.domain.dto.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dmj.cli.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zd
 */
@Data
public class SysPermissionDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "权限名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "权限类型(directory|menu|button)")
    @TableField("permission_type")
    private String permissionType;

    @ApiModelProperty(value = "权限路径")
    @TableField("permission_url")
    private String permissionUrl;

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

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    private List<String> roles;
}
