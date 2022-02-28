package com.zheng.eduservice.controller;


import com.alibaba.excel.EasyExcel;
import com.zheng.commonutils.R;
import com.zheng.eduservice.entity.Subject;
import com.zheng.eduservice.entity.vo.SubjectNestedVo;
import com.zheng.eduservice.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-15
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        subjectService.importSubjectData(file,subjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    @ApiOperation(value = "Excel批量导出")
    @PostMapping("writeSubject")
    public R writeSubject(){
        String fileName = "D:\\zheng.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
         List<Subject> list = subjectService.list(null);
        EasyExcel.write(fileName, Subject.class).sheet("写入方法一").doWrite(list);

        return R.ok();
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("")
    public R nestedList(){

        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }
}

