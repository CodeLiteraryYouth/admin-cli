package com.dmj.cli.controller;

import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.query.api.UserPayLogQuery;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import com.dmj.cli.domain.vo.api.UserInfoVO;
import com.dmj.cli.domain.vo.api.UserPayLogVO;
import com.dmj.cli.domain.vo.api.VidelLogVO;
import com.dmj.cli.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zd
 */
@RestController
@RequestMapping("/user")
@Api(tags = "个人中心")
@Login
public class UserController extends BaseController {

    @Autowired
    private UserService userInfoService;

    @ApiOperation("查询个人中心信息")
    @GetMapping("/info")
    public BaseResult<UserInfoVO> get() {
        return userInfoService.getUserBySceneId(getToken());
    }

    @ApiOperation("查询个人收藏信息")
    @GetMapping("/collect")
    public BaseResult<Map<String, List<CollectInfoVO>>> getUserCollect() {
        return userInfoService.getCollectInfo(getToken());
    }

    @ApiOperation("查询观看记录")
    @GetMapping("/video")
    public BaseResult<List<VidelLogVO>> listVideoLog() {
        return userInfoService.listVideoLog(getToken());
    }

    @ApiOperation("查询用户支付记录")
    @GetMapping("/pay/log")
    public BaseResult<List<UserPayLogVO>> listPayLogs(@ModelAttribute UserPayLogQuery query) {
        return userInfoService.listPayLogs(query);
    }

}
