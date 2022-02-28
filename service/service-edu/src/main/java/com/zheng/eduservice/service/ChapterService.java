package com.zheng.eduservice.service;

import com.zheng.eduservice.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-17
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    boolean removeByCourseId(String courseId);
}
