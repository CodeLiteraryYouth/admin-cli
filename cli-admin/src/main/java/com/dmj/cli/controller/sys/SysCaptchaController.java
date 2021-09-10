package com.dmj.cli.controller.sys;

import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.BaseController;
import com.dmj.cli.security.CaptchaException;
import com.dmj.cli.util.str.StringUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图片验证码（支持算术形式）
 * 
 * @author zd
 */
@Controller
@RequestMapping("/captcha")
@Api(tags = "获取后台登录验证码")
public class SysCaptchaController extends BaseController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 验证码生成
     */
    @GetMapping(value = "/captchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        String uuid=request.getParameter("uuid");
        if (StringUtils.isBlank(uuid)) {
            throw new CaptchaException("验证码的uuid为空");
        }
        ServletOutputStream out = null;
        try {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String capStr = captchaProducer.createText();
            BufferedImage bi = captchaProducer.createImage(capStr);
            redisUtils.hset(GlobalConstants.CAPTCHA,uuid,capStr);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}