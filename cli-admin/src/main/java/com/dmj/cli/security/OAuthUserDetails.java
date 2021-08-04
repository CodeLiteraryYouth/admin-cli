package com.dmj.cli.security;

import cn.hutool.core.collection.CollectionUtil;
import com.dmj.cli.domain.dto.SysUserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;



/**
 * 登录用户信息
 * @author zd
 */
@Data
@NoArgsConstructor
public class OAuthUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String clientId;

    private Collection<SimpleGrantedAuthority> authorities;

    public OAuthUserDetails(SysUserDTO user) {
        this.setId(user.getId().longValue());
        this.setUsername(user.getUserName());
        this.setPassword(user.getPassword());
        this.setEnabled(user.getLocked());
        if (CollectionUtil.isNotEmpty(user.getRoles()) && CollectionUtil.isNotEmpty(user.getAuthorities())) {
            authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            user.getAuthorities().forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
