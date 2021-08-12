package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.SysOauthClient;
import com.dmj.cli.domain.query.sys.OauthClientQuery;
import com.dmj.cli.service.sys.SysOauthClientService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author zd
 * @since 2021-06-28
 */
@RestController
@RequestMapping("/sys-oauth-client")
@Api(tags = "系统管理-客户端管理")
public class SysOauthClientController {

    @Autowired
    private SysOauthClientService service;

    @PostMapping("/save")
    @ApiOperation("新增客户端")
    public BaseResult<SysOauthClient> save(@RequestBody SysOauthClient entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }

    @PutMapping("/update")
    @ApiOperation("更新客户端")
    public BaseResult<SysOauthClient> update(@RequestBody SysOauthClient entity) {
        service.saveOrUpdate(entity);
        return BaseResult.success(entity);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除客户端")
    public BaseResult delete(@PathVariable String id) {
        service.removeById(id);
        return BaseResult.success();
    }

    @ApiOperation("查询客户端列表")
    @GetMapping("/page")
    public BaseResult<PageInfo<SysOauthClient>> pageInfoBaseResult(@ModelAttribute OauthClientQuery query) {
        return service.pageOauthClient(query);
    }

}

