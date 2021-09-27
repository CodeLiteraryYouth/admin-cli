package com.dmj.cli.domain.query.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/27
 * @apiNote
 **/
@Data
public class CountQuery implements Serializable {

    @ApiModelProperty(value = "0:今日 1:本周 2:本月 3:本年")
    private Integer dateType;

    @ApiModelProperty(value = "3:注册数 2:日活数 1:作品总量 0:文章总量")
    private Integer type;

}
