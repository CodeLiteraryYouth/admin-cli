package com.dmj.cli.util;

import cn.hutool.json.JSONUtil;
import com.dmj.cli.common.constant.AuthConstants;
import com.dmj.cli.domain.JWTPayload;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;

/**
 * @author zd
 * @date 2021-03-10
 */
public class JWTUtils {


    /**
     * 获取JWT的载体
     * @param token
     * @return
     */
    @SneakyThrows
    public static JWTPayload getJWTPayload(String token) {
        token = token.replace(AuthConstants.AUTHORIZATION_PREFIX, Strings.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        JWTPayload payload = JSONUtil.toBean(jwsObject.getPayload().toString(), JWTPayload.class);
        return payload;
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static boolean isExpired(String token) {
        JWTPayload payload = getJWTPayload(token);
        // 计算是否过期
        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        Long exp = payload.getExp();
        // token已过期，无需加入黑名单
        if (exp < currentTimeSeconds) {
            return true;
        }
        return false;
    }

}

