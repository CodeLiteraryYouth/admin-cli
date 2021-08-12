package com.dmj.cli.domain.query.api;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 */
@Data
public class ResourcesQuery extends BaseQuery implements Serializable {

    @ApiModelProperty(value = "素材标签")
    private Long typeId;

    @ApiModelProperty(value = "是否免费")
    private Boolean free;

    @ApiModelProperty(value = "搜索字段")
    private String searchVal;
}
