package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("sys_permission")
@ApiModel(value="SysPermission对象", description="")
public class SysPermission extends BaseEntity implements Serializable {

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

    @ApiModelProperty(value = "文件显示路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "权限标识")
    @TableField("permission_str")
    private String permissionStr;

    @ApiModelProperty(value = "父类权限ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "权限标识排序")
    @TableField("permission_order")
    private Integer permissionOrder;

    @ApiModelProperty(value = "是否展示该权限")
    @TableField("is_view")
    private Boolean isView;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;


}
