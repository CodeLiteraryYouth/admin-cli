package com.dmj.cli.service.api;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.cli.domain.query.api.CourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zd
 * @since 2021-08-13
 */
public interface CourseService extends IService<Course> {

    BaseResult<Map<String, List<Course>>> listCourse(CourseQuery courseQuery);

    /**
     * 观看视频
     * @param id
     * @param userId
     * @return
     */
    BaseResult watchVideo(Long id,Long userId);
}
