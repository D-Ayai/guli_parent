package com.zheng.eduservice.service;

import com.zheng.eduservice.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.eduservice.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-15
 */
public interface SubjectService extends IService<Subject> {

    void importSubjectData(MultipartFile file, SubjectService subjectService);

    List<SubjectNestedVo> nestedList();
}
