package com.dmj.cli.controller;

import com.dmj.cli.annotation.login.Login;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.vo.wx.WxQrcodeVO;
import com.dmj.cli.service.WxAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zd
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "获取登录二维码")
public class WxAuthController extends BaseController {

    @Autowired
    private WxAuthService wxAuthService;

    @ApiOperation("获取微信公众号二维码")
    @GetMapping("qrcode")
    public BaseResult<WxQrcodeVO> qrcode() {
        return wxAuthService.getQrcode();
    }

    @Login
    @ApiOperation("根据sceneId获取用户信息")
    @GetMapping("/user/info")
    public BaseResult<UserInfo> getUserBySceneId() {
        return wxAuthService.getUserBySceneId(getSceneId());
    }

    @GetMapping("checkToken")
    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        wxAuthService.checkToken(request,response);
    }

    @PostMapping("checkToken")
    public void getToken(HttpServletRequest request,HttpServletResponse response) throws Exception {
        wxAuthService.getUserInfo(request,response);
    }
}
