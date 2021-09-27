package com.dmj.cli.mapper.api;

import com.dmj.cli.domain.UvCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountItemVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-09-26
 */
public interface UvCountMapper extends BaseMapper<UvCount> {

    List<CountItemVO> listCountItem(CountQuery query);
}
