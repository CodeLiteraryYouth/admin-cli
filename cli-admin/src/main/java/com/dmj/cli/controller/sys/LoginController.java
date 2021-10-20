package com.dmj.cli.controller.sys;

import cn.hutool.core.lang.Assert;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.enums.ResultStatusCode;
import com.dmj.cli.common.redis.RedisUtils;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.domain.vo.sys.LoginForm;
import com.dmj.cli.security.CaptchaException;
import com.dmj.cli.service.sys.SysUserService;
import com.dmj.cli.util.jwt.JwtUtils;
import com.dmj.cli.util.str.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zd
 * @date 2021/9/8
 * @apiNote
 **/
@RestController
@Api(tags = "登录退出接口")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public BaseResult<SysUserDTO> login(LoginForm loginForm) {
        Assert.notNull(loginForm,"bad request");
        Assert.notNull(loginForm.getUsername(),"userName is null");
        SysUserDTO sysUserDTO=sysUserService.getUserByName(loginForm.getUsername());
        if (sysUserDTO == null) {
            return BaseResult.fail(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }
        if (!loginForm.getCode().equals(redisUtils.hget(GlobalConstants.CAPTCHA,loginForm.getUuid()))) {
            throw new CaptchaException("验证码异常");
        }
        String password = passwordEncoder.encode(loginForm.getPassword());
        if (!sysUserDTO.getPassword().equals(password)) {
            return BaseResult.fail(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }
        if (sysUserDTO.getLocked()) {
            return BaseResult.fail(ResultStatusCode.USER_LOCKED);
        }

        redisUtils.hdel(GlobalConstants.CAPTCHA,loginForm.getUuid());
        return BaseResult.success(sysUserDTO);
    }

    @GetMapping("/logout")
    public BaseResult logout() {
       String userName=jwtUtils.getUserName();
       if (StringUtils.isEmpty(userName)) {
           return BaseResult.fail("user is logout");
       }
       return BaseResult.success();
    }
}
