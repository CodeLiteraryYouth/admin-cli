package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.UserLoginLog;
import com.dmj.cli.mapper.api.UserLoginLogMapper;
import com.dmj.cli.service.api.UserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
