package com.dmj.cli.service.sys.impl;

import com.dmj.cli.domain.Tool;
import com.dmj.cli.mapper.sys.ToolMapper;
import com.dmj.cli.service.sys.ToolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-31
 */
@Service
public class ToolServiceImpl extends ServiceImpl<ToolMapper, Tool> implements ToolService {

}
