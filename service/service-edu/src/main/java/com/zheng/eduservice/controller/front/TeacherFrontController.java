package com.zheng.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.commonutils.R;
import com.zheng.eduservice.entity.Course;
import com.zheng.eduservice.entity.Teacher;
import com.zheng.eduservice.service.CourseService;
import com.zheng.eduservice.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
//@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<Teacher> pageParam = new Page<Teacher>(page, limit);

        Map<String, Object> map = teacherService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        Teacher teacher = teacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<Course> courseList = courseService.selectByTeacherId(id);

        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }
}
