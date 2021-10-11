package com.dmj.cli.service;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysDict;
import com.dmj.cli.domain.query.api.SysConfigQuery;

import java.util.List;
import java.util.Map;

/**
 * @author zd
 */
public interface ConfigService {

    /**
     * 查询充值会员类型
     * @param query
     * @return
     */
    BaseResult<List<SysDict>> listMembers(SysConfigQuery query);
}
