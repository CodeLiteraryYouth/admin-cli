package com.dmj.cli.domain.vo.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 */
@Data
public class WxQrcodeVO implements Serializable {

    @ApiModelProperty(value = "获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码")
    private String ticket;

    @ApiModelProperty(value = "二维码有效时间")
    private Long expire_seconds;

    @ApiModelProperty(value = "二维码图片解析后的地址")
    private String url;

    @ApiModelProperty(value = "场景值ID")
    private String sceneId;
}
