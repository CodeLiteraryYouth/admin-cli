package com.dmj.cli.mapper.api;

import com.dmj.cli.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.cli.domain.query.api.CourseQuery;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> listCourse(CourseQuery courseQuery);
}
