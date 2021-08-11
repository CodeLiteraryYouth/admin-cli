package com.dmj.cli.security;

import com.dmj.cli.domain.dto.sys.SysUserDTO;
import com.dmj.cli.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zd
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDTO sysUserDTO=sysUserService.getUserByName(username);
        if (null == sysUserDTO) {
            throw new UsernameNotFoundException("user is not exists");
        }
        return new OAuthUserDetails(sysUserDTO);
    }
}
