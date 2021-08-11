package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.dmj.cli.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_info")
@ApiModel(value="UserInfo对象", description="用户信息表")
public class UserInfo extends BaseEntity {

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

    @ApiModelProperty(value = "是否为会员")
    @TableField("is_members")
    private Boolean isMembers;

    @ApiModelProperty(value = "会员类型(VIDEO:视频会员 MATERIAL:素材会员)")
    @TableField("members_type")
    private String membersType;

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
    
    
}
