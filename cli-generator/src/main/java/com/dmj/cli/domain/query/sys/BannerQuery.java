package com.dmj.cli.domain.query.sys;

import com.dmj.cli.domain.query.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zd
 * @date 2021/9/8
 * @apiNote
 **/
@Data
public class BannerQuery extends BaseQuery {

    @ApiModelProperty(value = "0:顶部 1:底部 2:左侧 3:右侧")
    private Integer location;

    @ApiModelProperty(value = "0:首页")
    private Integer type;
}
