package com.zheng.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.commonutils.JwtUtils;
import com.zheng.commonutils.R;
import com.zheng.commonutils.order.CourseWebVoOrder;
import com.zheng.eduservice.client.OrderClient;
import com.zheng.eduservice.entity.Course;
import com.zheng.eduservice.entity.chapter.ChapterVo;
import com.zheng.eduservice.entity.frontvo.CourseQueryVo;
import com.zheng.eduservice.entity.frontvo.CourseWebVo;
import com.zheng.eduservice.entity.vo.CourseInfoForm;
import com.zheng.eduservice.service.ChapterService;
import com.zheng.eduservice.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
//@CrossOrigin
public class CourseFrontController {


    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<Course> pageParam = new Page<Course>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return  R.ok().data(map);
    }


    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }

    //根据id查询课程详情信息
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id, HttpServletRequest request) {
        //课程查询课程基本信息
        CourseWebVo courseFrontInfo = courseService.selectInfoWebById(id);
        //查询课程里面大纲数据
        List<ChapterVo> chapterVideoList = chapterService.nestedList(id);

        //远程调用，判断课程是否被购买
        boolean buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), id);

        return R.ok().data("courseFrontInfo",courseFrontInfo).data("chapterVideoList",chapterVideoList).data("isbuy",buyCourse);
    }

    //根据课程id查询课程信息
    @GetMapping("getDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId) {
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        CourseWebVoOrder courseInfo = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo,courseInfo);
        return courseInfo;
    }

}
