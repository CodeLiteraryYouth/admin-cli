package com.dmj.cli.service;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.domain.vo.wx.WxQrcodeVO;
import org.dom4j.DocumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zd
 */
public interface WxAuthService {

    /**
     * 获取临时公众号的二维码
     */
    BaseResult<WxQrcodeVO> getQrcode();

    /**
     * 根据场景ID获取用户信息
     * @param sceneId
     * @return
     */
    BaseResult<UserInfo> getUserBySceneId(Long sceneId);

    /**
     * get请求验证token
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 服务器POST请求获取
     * @param request
     * @param response
     * @return
     */
    void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException;
}
