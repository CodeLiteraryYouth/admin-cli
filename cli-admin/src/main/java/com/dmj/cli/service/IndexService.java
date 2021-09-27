package com.dmj.cli.service;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.query.api.CountQuery;
import com.dmj.cli.domain.vo.api.CountVO;

/**
 * @author zd
 * @date 2021/9/27
 * @apiNote
 **/
public interface IndexService {

    public BaseResult<CountVO> info(CountQuery query);
}
