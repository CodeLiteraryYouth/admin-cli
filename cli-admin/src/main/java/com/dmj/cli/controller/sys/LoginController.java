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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zd
 * @date 2021/9/8
 * @apiNote
 **/
@RestController
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;


    @PostMapping("/login")
    public BaseResult<SysUserDTO> login(@RequestBody LoginForm loginForm) {
        Assert.notNull(loginForm,"bad request");
        Assert.notNull(loginForm.getUserName(),"userName is null");
        SysUserDTO sysUserDTO=sysUserService.getUserByName(loginForm.getUserName());
        if (sysUserDTO == null) {
            return BaseResult.fail(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }
        if (!sysUserDTO.getPassword().equals(new BCryptPasswordEncoder().encode(loginForm.getPassword()))) {
            return BaseResult.fail(ResultStatusCode.NOT_EXIST_USER_OR_ERROR_PWD);
        }
        if (sysUserDTO.getLocked()) {
            return BaseResult.fail(ResultStatusCode.USER_LOCKED);
        }
        if (!loginForm.getCode().equals(redisUtils.hget(GlobalConstants.CAPTCHA,loginForm.getUuid()))) {
            throw new CaptchaException("验证码异常");
        }
        redisUtils.hdel(GlobalConstants.CAPTCHA,loginForm.getUuid());
        return BaseResult.success(sysUserDTO);
    }
}
