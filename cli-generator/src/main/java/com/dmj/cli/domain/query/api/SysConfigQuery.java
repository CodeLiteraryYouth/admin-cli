package com.dmj.cli.domain.query.api;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zd
 */
@Data
public class SysConfigQuery extends BaseQuery {

    @ApiModelProperty(value = "Resources:素材会员 Course:课程会员")
    private Object[] type;
}
