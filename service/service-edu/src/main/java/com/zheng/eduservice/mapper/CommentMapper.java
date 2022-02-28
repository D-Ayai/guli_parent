package com.zheng.eduservice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.eduservice.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zheng.eduservice.entity.chapter.CommentVo;
import com.zheng.eduservice.entity.query.CommentQuery;
import com.zheng.eduservice.entity.vo.CourseQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-12-02
 */
public interface CommentMapper extends BaseMapper<Comment> {

    Page<CommentVo> pageQuery(Page<CommentVo> pageParam, @Param(value = "cq") CommentQuery courseQuery);
}
