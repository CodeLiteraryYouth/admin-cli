package com.dmj.cli.domain.vo.sys;

import com.dmj.cli.domain.Course;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/16
 * @apiNote
 **/
@Data
public class CourseVO extends Course implements Serializable {

    private String typeName;
}
