package com.dmj.cli.domain.vo.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dmj.cli.domain.BaseEntity;
import com.dmj.cli.domain.SysPermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zd
 */
@Data
public class SysRoleVO extends BaseEntity implements Serializable {

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

    private List<SysPermission> sysPermissions;
}
