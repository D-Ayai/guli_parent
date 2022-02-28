package com.zheng.eduservice.service;

import com.zheng.eduservice.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.eduservice.entity.vo.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-17
 */
public interface VideoService extends IService<Video> {

    boolean getCountByChapterId(String chapterId);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

    boolean removeByCourseId(String courseId);



}
