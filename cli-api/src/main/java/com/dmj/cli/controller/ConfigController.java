package com.dmj.cli.controller;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysDict;
import com.dmj.cli.domain.query.api.SysConfigQuery;
import com.dmj.cli.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zd
 */
@RestController
@RequestMapping("/sys-config")
@Api(tags = "查询系统配置")
public class ConfigController {

    @Autowired
    private ConfigService configService;


    @ApiOperation("查询配置充值类型")
    @GetMapping("members")
    public BaseResult<Map<String, List<SysDict>>> listMembers(@ModelAttribute SysConfigQuery query) {
        return configService.listMembers(query);
    }
}
