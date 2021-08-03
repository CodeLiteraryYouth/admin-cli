package com.dmj.cli.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Administrator
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    private String secret;

    private long expire;

    private String header;

    /**
     * 生成token
     * @param userName
     * @return
     */
    public String generateToken(String userName) {
        Date now=new Date();
        Date expireDate=new Date(now.getTime()+ 1000*expire);
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
            return null;
        }
    }

    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
