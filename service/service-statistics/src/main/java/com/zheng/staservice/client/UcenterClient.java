package com.zheng.staservice.client;/*
 *@Author lee
 * @date 2020/08/09
 */

import com.zheng.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping(value = "/educenter/apimember/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);
}
