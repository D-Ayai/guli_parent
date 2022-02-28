package com.zheng.eduservice.mapper;

import com.zheng.eduservice.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zheng.eduservice.entity.frontvo.CourseWebVo;
import com.zheng.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-11-17
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo getCoursePublishVoById(String id);

    CourseWebVo selectInfoWebById(String courseId);
}
