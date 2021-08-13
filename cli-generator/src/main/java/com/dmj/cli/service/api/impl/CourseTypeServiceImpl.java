package com.dmj.cli.service.api.impl;

import com.dmj.cli.domain.CourseType;
import com.dmj.cli.mapper.api.CourseTypeMapper;
import com.dmj.cli.service.api.CourseTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程类型 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements CourseTypeService {

}
