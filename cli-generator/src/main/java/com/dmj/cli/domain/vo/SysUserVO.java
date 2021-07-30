package com.dmj.cli.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.dmj.cloud.model.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@Data
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="")
public class SysUserVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "移动手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "用户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "固定电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "微信登录唯一标识")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "用户是否已锁定")
    @TableField("locked")
    private Boolean locked;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField("creater")
    private String creater;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updater")
    private String updater;

    @ApiModelProperty(value = "删除标记")
    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;

    private List<SysRole> sysRoles;


}
