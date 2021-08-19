package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.UserInfoAccount;
import com.dmj.cli.mapper.api.UserInfoAccountMapper;
import com.dmj.cli.service.api.UserInfoAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户表 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
@Service
public class UserInfoAccountServiceImpl extends ServiceImpl<UserInfoAccountMapper, UserInfoAccount> implements UserInfoAccountService {

}
