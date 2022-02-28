package com.zheng.orderservice.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.commonutils.R;
import com.zheng.orderservice.entity.Order;
import com.zheng.orderservice.entity.query.OrderQuery;
import com.zheng.orderservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderservice/orderadmin")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    // 查询所有订单
    @ApiOperation(value = "分页订单列表")
    @PostMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "Order", value = "查询对象", required = false)
            @RequestBody(required = false)   OrderQuery order){

        Page<Order> pageParam = new Page<>(page, limit);
           orderService.pageQuery(pageParam, order);
//        orderService.page(pageParam, null);
        return  R.ok().data("total", pageParam.getTotal()).data("rows", pageParam.getRecords());
    }

    @ApiOperation(value = "根据ID删除订单")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "订单ID", required = true)
            @PathVariable String id){
        boolean result = orderService.removeById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
    @ApiOperation("根据课程查询订单")
    @GetMapping("getcourse/{course}")
    public R getcourse(@PathVariable String course){
        List<Order>  list =orderService.getCourseQuery(course);
        return R.ok().data("rows", list);
    }


}
