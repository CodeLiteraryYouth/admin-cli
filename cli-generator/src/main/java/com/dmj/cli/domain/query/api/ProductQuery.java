package com.dmj.cli.domain.query.api;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zd
 * @date 2021/9/7
 * @apiNote
 **/
@Data
public class ProductQuery extends BaseQuery {

    @ApiModelProperty(value ="false:文章 true:作品")
    private Boolean isArticle;

    @ApiModelProperty(value = "类型ID")
    private Long typeId;
}
