package com.dmj.cli.controller;

import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.UserRechargeInfo;
import com.dmj.cli.domain.query.BaseQuery;
import com.dmj.cli.domain.vo.api.CollectInfoVO;
import com.dmj.cli.domain.vo.api.PayLogVO;
import com.dmj.cli.domain.vo.api.UserInfoVO;
import com.dmj.cli.domain.vo.api.VideoLogVO;
import com.dmj.cli.service.UserService;
import com.github.pagehelper.PageInfo;
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
public class UserController extends BaseController {

    @Autowired
    private UserService userInfoService;

    @Login
    @ApiOperation("查询个人中心信息")
    @GetMapping("/info")
    public BaseResult<UserInfoVO> get() {
        return userInfoService.getUserBySceneId(getToken());
    }

    @Login
    @ApiOperation("查询个人收藏信息")
    @GetMapping("/collect")
    public BaseResult<Map<String, List<CollectInfoVO>>> getUserCollect() {
        return userInfoService.getCollectInfo(getToken());
    }

    @Login
    @ApiOperation("查询观看记录")
    @GetMapping("/video")
    public BaseResult<List<VideoLogVO>> listVideoLog() {
        return userInfoService.listVideoLog(getToken());
    }

    @Login
    @ApiOperation("查询支付记录")
    @GetMapping("/pay/{skuType}")
    public BaseResult<List<PayLogVO>> listPay(@PathVariable Long skuType) {
        return userInfoService.listPayLog(getToken(),skuType);
    }

    @Login
    @ApiOperation("查询会员获取记录")
    @GetMapping("/member/record")
    public BaseResult<PageInfo<List<UserRechargeInfo>>> record(@ModelAttribute BaseQuery query) {
        startPage();
        List<UserRechargeInfo> list = userInfoService.listUserRecord(getToken());
        return pageInfoBaseResult(list);
    }


}
