package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.UserWork;
import com.dmj.cli.mapper.api.UserWorkMapper;
import com.dmj.cli.service.api.UserWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 学员作业 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-17
 */
@Service
public class UserWorkServiceImpl extends ServiceImpl<UserWorkMapper, UserWork> implements UserWorkService {

}
