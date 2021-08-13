package com.dmj.cli.domain.query.api;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zd
 */
@Data
public class CourseQuery extends BaseQuery {

    @ApiModelProperty(value = "类型ID")
    private Long typeId;

    @ApiModelProperty(value = "查询字段")
    private String searchVal;
}
