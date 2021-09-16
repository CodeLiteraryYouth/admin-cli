package com.dmj.cli.controller.sys;


import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.api.UserInfoQuery;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.service.api.UserInfoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@RestController
@RequestMapping("/userinfo")
@Api(tags = "用户信息表")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService service;


    @ApiOperation("查询会员信息列表")
    @GetMapping("/list")
    public BaseResult<PageInfo<List<UserInfo>>> page(@ModelAttribute UserInfoQuery query) {
        startPage();
        List<UserInfo> userInfos= service.list();
        return pageInfoBaseResult(userInfos);
    }
}

