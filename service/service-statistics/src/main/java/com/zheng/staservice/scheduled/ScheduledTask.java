package com.zheng.staservice.scheduled;/*
 *@Author lee
 * @date 2020/08/10
 */

import com.zheng.commonutils.R;
import com.zheng.staservice.service.StatisticsDailyService;
import com.zheng.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService dailyService;


    //每5秒执行一次
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void task1() {
//        System.out.println("*********++++++++++++*****执行了");
//    }

    //每天凌晨两点执行
    @Scheduled(cron = "0 0 2 * * ? ")
    public void task2() {
        dailyService.createStatisticsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
