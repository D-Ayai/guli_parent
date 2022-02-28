package com.zheng.staservice.controller;


import com.zheng.commonutils.R;
import com.zheng.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-12-06
 */
@RestController
@RequestMapping("/staservice/daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;

    //统计某一天的注册人数
    @PostMapping("{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin ,@PathVariable String end){
        Map<String,Object> map =  dailyService.showData(type,begin,end);
        return  R.ok().data(map);
    }
}

