package com.dmj.cli.domain.vo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zd
 * @date 2021/9/26
 * @apiNote
 **/
@Data
public class CountVO implements Serializable {

    @ApiModelProperty(value = "当日注册数")
    private Integer registerNum;

    @ApiModelProperty(value = "日活数")
    private Integer uvNum;

    @ApiModelProperty(value = "产品总数")
    private Integer productNum;

    @ApiModelProperty(value = "文章总数")
    private Integer articleNum;

    @ApiModelProperty(value = "统计柱状图")
    private List<CountItemVO> itemVOS;
}
