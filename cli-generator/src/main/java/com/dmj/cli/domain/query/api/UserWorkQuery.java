package com.dmj.cli.domain.query.api;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zd
 */
@Data
public class UserWorkQuery extends BaseQuery {

    @ApiModelProperty(value = "周练ID")
    private Long jobId;

    @ApiModelProperty(value = "用户Id")
    private Long userId;
}
