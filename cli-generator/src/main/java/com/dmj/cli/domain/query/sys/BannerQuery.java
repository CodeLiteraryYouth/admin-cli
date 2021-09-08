package com.dmj.cli.domain.query.sys;

import com.dmj.cli.domain.query.BaseQuery;
import lombok.Data;

/**
 * @author zd
 * @date 2021/9/8
 * @apiNote
 **/
@Data
public class BannerQuery extends BaseQuery {
    
    private Integer location;

    private Integer type;
}
