package com.zheng.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.eduservice.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.eduservice.entity.chapter.CommentVo;
import com.zheng.eduservice.entity.query.CommentQuery;
import com.zheng.eduservice.entity.vo.CourseQuery;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-12-02
 */
public interface CommentService extends IService<Comment> {

    void pageQuery(Page<Comment> pageParam, CommentQuery commentQuery);

    void page(Page<CommentVo> pageParam, CommentQuery ticketsQuery);

}
