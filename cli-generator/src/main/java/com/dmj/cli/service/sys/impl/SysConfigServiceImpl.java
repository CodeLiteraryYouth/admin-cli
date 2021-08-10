package com.dmj.cli.service.sys.impl;

import com.dmj.cli.domain.SysConfig;
import com.dmj.cli.mapper.sys.SysConfigMapper;
import com.dmj.cli.service.sys.SysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置信息表 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-10
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

}
