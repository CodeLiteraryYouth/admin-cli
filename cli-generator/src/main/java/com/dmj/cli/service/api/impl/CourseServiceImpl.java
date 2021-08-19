package com.dmj.cli.service.api.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Course;
import com.dmj.cli.domain.UserVideoLog;
import com.dmj.cli.domain.query.api.CourseQuery;
import com.dmj.cli.mapper.api.CourseMapper;
import com.dmj.cli.mapper.api.UserVideoLogMapper;
import com.dmj.cli.service.api.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserVideoLogMapper userVideoLogMapper;

    @Override
    public BaseResult<Map<String,List<Course>>> listCourse(CourseQuery courseQuery) {
        List<Course> courses=courseMapper.listCourse(courseQuery);
        Map<String,List<Course>> result=null;
        if (CollectionUtil.isNotEmpty(courses)) {
            result=courses.stream().collect(Collectors.groupingBy(Course::getCourseType));
        }
        return BaseResult.success(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public BaseResult watchVideo(Long id, Long userId) {
        Assert.notNull(id,"courseId is null");
        Assert.notNull(userId,"userId is null");
        UserVideoLog userVideoLog=new UserVideoLog();
        userVideoLog.setVideoId(id);
        userVideoLog.setUserId(userId);
        userVideoLog.setVideoTime(LocalDateTime.now());
        userVideoLogMapper.insert(userVideoLog);
        return BaseResult.success();
    }
}
