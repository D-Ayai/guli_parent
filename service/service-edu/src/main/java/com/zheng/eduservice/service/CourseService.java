package com.zheng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.eduservice.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.eduservice.entity.frontvo.CourseWebVo;
import com.zheng.eduservice.entity.vo.CourseInfoForm;
import com.zheng.eduservice.entity.vo.CoursePublishVo;
import com.zheng.eduservice.entity.vo.CourseQuery;
import com.zheng.eduservice.entity.frontvo.CourseQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-17
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    //根据id 查询
    CourseInfoForm getCourseInfoFormById(String id);

    // 修改
    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    //根据讲师id查询讲师所讲课程列表
    List<Course> selectByTeacherId(String teacherId);

    Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQuery);

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);
}
