package com.dmj.cli.domain.query.api;

import com.baomidou.mybatisplus.annotation.TableField;
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

    @ApiModelProperty(value ="false:文章(推荐作品) true:作品(经验分享)")
    private Boolean isArticle;

    @ApiModelProperty(value = "类型ID")
    private Long typeId;

    @ApiModelProperty(value = "false:不屏蔽 true:屏蔽")
    @TableField("is_block")
    private Boolean isBlock;

    @ApiModelProperty(value = "false:不热门 true:热门")
    @TableField("is_hot")
    private Boolean isHot;

    @ApiModelProperty(value = "false:未通过 true:已通过")
    @TableField("is_pass")
    private Boolean isPass;
}
