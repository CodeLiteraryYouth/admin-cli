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
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@Data
public class SysUserDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    private List<String> roles;

    private List<String> authorities;

}
