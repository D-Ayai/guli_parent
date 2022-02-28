package com.zheng.staservice.service;

import com.zheng.commonutils.R;
import com.zheng.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-12-06
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

        //统计某一天的注册人数
    void createStatisticsByDay(String day);

    Map<String, Object> showData(String type, String begin, String end);
}
