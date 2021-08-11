package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.UserInfo;
import com.dmj.cli.mapper.api.UserInfoMapper;
import com.dmj.cli.service.api.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
