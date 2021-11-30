package com.dmj.cli.util.jwt;

import cn.hutool.core.util.StrUtil;
import com.dmj.cli.util.ServletUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private String secret;

    private long expiration;

    private String header;

    /**
     * 生成tokenw
     * @param userName
     * @return
     */
    public String generateToken(String userName) {
        Date now=new Date();
        Date expireDate=new Date(now.getTime()+ expiration);
        return Jwts.builder()
                //样式
                .setHeaderParam("typ","JWT")
                //主体
                .setSubject(userName)
                //创建时间
                .setIssuedAt(now)
                //过期时间
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();


    }

    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserName() {
        HttpServletRequest request= ServletUtils.getRequest();
        String jwt=request.getHeader(getHeader());
        if (StrUtil.isBlankOrUndefined(jwt)) {
            return null;
        }
        Claims claims=getClaimByToken(jwt);
        if (claims == null) {
            return null;
        }
        if (isTokenExpired(claims)) {
            return null;
        }
        String userName=claims.getSubject();
        return userName;
    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
