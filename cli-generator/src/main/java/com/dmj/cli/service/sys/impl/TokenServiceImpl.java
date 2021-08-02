package com.dmj.cli.service.sys.impl;

import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.domain.JWTPayload;
import com.dmj.cli.service.sys.ITokenService;
import com.dmj.cli.util.JWTUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zd
 * @date 2021-03-10
 */
@Service
@AllArgsConstructor
public class TokenServiceImpl implements ITokenService {


    RedisTemplate redisTemplate;

    @Override
    @SneakyThrows
    public boolean invalidateToken(String token) {

        JWTPayload payload = JWTUtils.getJWTPayload(token);

        // 计算是否过期
        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        Long exp = payload.getExp();
        // token已过期，无需加入黑名单
        if (exp < currentTimeSeconds) {
            return true;
        }
        // 添加至黑名单使其失效
        redisTemplate.opsForValue().set(AuthConstants.TOKEN_BLACKLIST_PREFIX + payload.getJti(), null, (exp - currentTimeSeconds), TimeUnit.SECONDS);
        return true;
    }

    @Override
    public int getTokenStatus(String token) {
        JWTPayload payload = JWTUtils.getJWTPayload(token);

        // 计算是否过期
        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        Long exp = payload.getExp();
        // token已过期  返回失效
        if (exp < currentTimeSeconds) {
            return 0;
        }

        // 判断是否存在黑名单
        String jti = payload.getJti();
        Boolean isExists = redisTemplate.hasKey(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti);
        // 被添加到黑名单  返回失效
        if (isExists == true) {
            return 0;
        }
        return 1;
    }

}
