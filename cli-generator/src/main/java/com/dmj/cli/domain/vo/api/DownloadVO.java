package com.dmj.cli.domain.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DownloadVO implements Serializable {

    @ApiModelProperty(value = "账户下载次数")
    private Long accountNum;

    @ApiModelProperty(value = "资源下载所需要的次数")
    private Long resourceNum;

    @ApiModelProperty(value = "下载地址与密码")
    private String resourcesUrl;
}
