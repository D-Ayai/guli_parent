package com.zheng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.eduservice.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.eduservice.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-10
 */
public interface TeacherService extends IService<Teacher> {

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);

    public Map<String, Object> pageListWeb(Page<Teacher> pageParam);
}
