package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.UserPayLog;
import com.dmj.cli.mapper.api.UserPayLogMapper;
import com.dmj.cli.service.api.UserPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户支付记录表 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-19
 */
@Service
public class UserPayLogServiceImpl extends ServiceImpl<UserPayLogMapper, UserPayLog> implements UserPayLogService {

}
