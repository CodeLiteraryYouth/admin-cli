package com.dmj.cli.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.service.sys.SysUserService;
import com.dmj.cli.util.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.JarException;

/**
 * JWT过滤器
 * @author zd
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SysUserService sysUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt=request.getHeader(jwtUtils.getHeader());
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request,response);
            return;
        }
        Claims claims=jwtUtils.getClaimByToken(jwt);
        if (claims == null) {
            throw new JarException("token is null");
        }
        if (jwtUtils.isTokenExpired(claims)) {
            throw new JwtException("token is expired");
        }
        String userName=claims.getSubject();

        //获取缓存中的权限
        SysUserDTO sysUserDTO=sysUserService.getUserByName(userName);

        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();

        if (CollectionUtil.isNotEmpty(sysUserDTO.getRoles()) && CollectionUtil.isNotEmpty(sysUserDTO.getAuthorities())) {
            sysUserDTO.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            sysUserDTO.getAuthorities().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
        }

        //获取用户权限等信息
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName,sysUserDTO.getPassword(),authorities);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request,response);
    }
}
