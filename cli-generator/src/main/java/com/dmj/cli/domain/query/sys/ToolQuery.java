package com.dmj.cli.domain.query.sys;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ToolQuery extends BaseQuery implements Serializable {

    @ApiModelProperty(value = "类型Id")
    private Long typeId;
}
