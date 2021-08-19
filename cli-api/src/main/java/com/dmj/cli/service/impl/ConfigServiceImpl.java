package com.dmj.cli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysDict;
import com.dmj.cli.domain.query.api.SysConfigQuery;
import com.dmj.cli.service.ConfigService;
import com.dmj.cli.service.sys.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zd
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private SysDictService dictService;

    @Override
    public BaseResult<Map<String,List<SysDict>>> listMembers(SysConfigQuery query) {
        Assert.notNull(query.getType(),"type is null");
        List<SysDict> sysDicts=dictService.list(new LambdaQueryWrapper<SysDict>().in(SysDict::getType, query.getType()));
        Map<String,List<SysDict>> result=sysDicts.stream().collect(Collectors.groupingBy(SysDict::getType));
        return BaseResult.success(result);
    }
}
