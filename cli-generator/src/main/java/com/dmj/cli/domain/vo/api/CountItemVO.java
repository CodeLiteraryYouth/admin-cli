package com.dmj.cli.domain.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/27
 * @apiNote
 **/
@Data
public class CountItemVO implements Serializable {

    @ApiModelProperty(value = "日期(yyyy-MM-dd格式)")
    private String date;

    private Long value;
}
