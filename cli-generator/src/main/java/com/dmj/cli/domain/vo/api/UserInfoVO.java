package com.dmj.cli.domain.vo.api;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dmj.cli.domain.BaseEntity;
import com.dmj.cli.domain.UserInfoAccount;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zd
 */
@Data
public class UserInfoVO extends BaseEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户真实姓名")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "国家")
    @TableField("country")
    private String country;

    @ApiModelProperty(value = "省份")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "语言")
    @TableField("language")
    private String language;

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "微信用户唯一标识")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "subscribe(订阅)、SCAN(浏览)")
    @TableField("event")
    private String event;

    @ApiModelProperty(value = "登录状态")
    @TableField("login_status")
    private Boolean loginStatus;

    @ApiModelProperty(value = "邮箱地址")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "注册密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "场景ID")
    @TableField("scene_id")
    private String sceneId;

    private List<UserInfoAccount> userInfoAccounts;
}
