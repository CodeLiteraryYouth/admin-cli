package com.dmj.cli.domain.query.api;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zd
 */
@Data
public class UserPayLogQuery extends BaseQuery {

    @ApiModelProperty(value = "交易类型")
    private String tradeType;

    private Long userId;
}
