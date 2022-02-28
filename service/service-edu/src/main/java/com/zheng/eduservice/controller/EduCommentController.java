package com.zheng.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.commonutils.R;
import com.zheng.eduservice.entity.Comment;
import com.zheng.eduservice.entity.Teacher;
import com.zheng.eduservice.entity.chapter.CommentVo;
import com.zheng.eduservice.entity.query.CommentQuery;
import com.zheng.eduservice.entity.vo.TeacherQuery;
import com.zheng.eduservice.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {


    @Autowired
    private CommentService commentService;


    @ApiOperation(value = "分页评论列表")
    @PostMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CommentQuery commentQuery){



        Page<Comment> pageParam = new Page<>(page, limit);

        commentService.pageQuery(pageParam, commentQuery);
        List<Comment> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }





    @ApiOperation(value = "根据ID删除评论")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "评论ID", required = true)
            @PathVariable String id){
        commentService.removeById(id);
        return R.ok();
    }




    @ApiOperation(value = "分页评论列表")
    @PostMapping("vo/{page}/{limit}")
    public R pageQueryVo(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CommentQuery commentQuery){
        Page<CommentVo> customerQueryPage = new Page<>(page, limit);
        commentService.page(customerQueryPage, commentQuery);
        return R.ok().data("rows", customerQueryPage.getRecords())
                .data("total", customerQueryPage.getTotal());
    }
}

