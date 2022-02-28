package com.zheng.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.eduservice.entity.Comment;
import com.zheng.eduservice.entity.Teacher;
import com.zheng.eduservice.entity.chapter.CommentVo;
import com.zheng.eduservice.entity.query.CommentQuery;
import com.zheng.eduservice.entity.vo.CourseQuery;
import com.zheng.eduservice.mapper.CommentMapper;
import com.zheng.eduservice.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-12-02
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public void pageQuery(Page<Comment> pageParam, CommentQuery commentQuery) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if (commentQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String coursetitle = commentQuery.getCoursetitle();
        String  nickname = commentQuery.getNickname();
        String begin = commentQuery.getBegin();
        String end = commentQuery.getEnd();

        if (!StringUtils.isEmpty(coursetitle)) {
            queryWrapper.like("name", coursetitle);
        }

        if (!StringUtils.isEmpty(nickname) ) {
            queryWrapper.eq("nickname", nickname);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public void page(Page<CommentVo> pageParam, CommentQuery ticketsQuery) {
         baseMapper.pageQuery(pageParam, ticketsQuery);
    }
}
