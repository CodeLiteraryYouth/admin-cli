package com.dmj.cli.domain.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/26
 * @apiNote
 **/
@Data
public class CountVO implements Serializable {

    @ApiModelProperty(value = "当日注册数")
    private Long registerNum;

    @ApiModelProperty(value = "日活数")
    private Long uvNum;

    @ApiModelProperty(value = "产品总数")
    private Long productNum;

    @ApiModelProperty(value = "文章总数")
    private Long articleNum;
}
