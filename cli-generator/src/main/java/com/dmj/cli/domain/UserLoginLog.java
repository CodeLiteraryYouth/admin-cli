package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录记录表
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@Data
@Accessors(chain = true)
@TableName("user_login_log")
@ApiModel(value="UserLoginLog对象", description="用户登录记录表")
public class UserLoginLog {

    private static final long serialVersionUID=1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登陆者微信号")
    @TableField("login_open_ig")
    private String loginOpenIg;

    @ApiModelProperty(value = "登录方式")
    @TableField("login_type")
    private String loginType;

    @ApiModelProperty(value = "登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "消息类型(event或者text)")
    @TableField("msg_type")
    private String msgType;

    @ApiModelProperty(value = "事件类型")
    @TableField("event")
    private String event;

    @ApiModelProperty(value = "事件KEY值，qrscene_为前缀，后面为二维码的参数值")
    @TableField("event_key")
    private String eventKey;

    @ApiModelProperty(value = "二维码的ticket，可用来换取二维码图片")
    @TableField("ticket")
    private String ticket;

    @ApiModelProperty(value = "登录邮箱地址")
    @TableField("email")
    private String email;
    
    
}
