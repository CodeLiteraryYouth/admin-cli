package com.dmj.cli.controller;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.vo.wx.WxQrcodeVO;
import com.dmj.cli.service.WxAuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zd
 */
@RestController
@RequestMapping("/auth")
public class WxAuthController {

    @Autowired
    private WxAuthService wxAuthService;

    @ApiOperation("获取微信公众号二维码")
    @GetMapping("qrcode")
    public BaseResult<WxQrcodeVO> qrcode() {
        return wxAuthService.getQrcode();
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
