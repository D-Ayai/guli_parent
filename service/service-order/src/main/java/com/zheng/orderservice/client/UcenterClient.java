package com.zheng.orderservice.client;

import com.zheng.commonutils.order.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据课程id查询课程信息
    @PostMapping("/educenter/apimember/getInfoUc/{id}")
    public UcenterMemberOrder getInfo(@PathVariable("id") String id);
}
