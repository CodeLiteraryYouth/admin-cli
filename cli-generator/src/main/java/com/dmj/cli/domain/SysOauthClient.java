package com.dmj.cli.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@Data
@TableName("sys_oauth_client")
@ApiModel(value="SysOauthClient对象", description="")
public class SysOauthClient implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户端ID")
    @TableId(value = "client_id", type = IdType.AUTO)
    private String clientId;

    @ApiModelProperty(value = "客户端密钥")
    @TableField("client_secret")
    private String clientSecret;

    @ApiModelProperty(value = "资源id列表")
    @TableField("resources_ids")
    private String resourcesIds;

    @ApiModelProperty(value = "域")
    @TableField("scope")
    private String scope;

    @ApiModelProperty(value = "授权方式")
    @TableField("authorized_grant_type")
    private String authorizedGrantType;

    @ApiModelProperty(value = "回调地址")
    @TableField("webserver_redirect_url")
    private String webserverRedirectUrl;

    @ApiModelProperty(value = "权限列表")
    @TableField("authorities")
    private String authorities;

    @ApiModelProperty(value = "认证令牌时效")
    @TableField("access_token_validity")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "刷信令牌时效")
    @TableField("refresh_token_validity")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "扩展信息")
    @TableField("additional_information")
    private String additionalInformation;

    @ApiModelProperty(value = "是否自动放行")
    @TableField("autoapprove")
    private String autoapprove;


}
