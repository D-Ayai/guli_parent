package com.zheng.orderservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.orderservice.entity.query.OrderQuery;
import com.zheng.orderservice.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-12-02
 */
public interface OrderService extends IService<Order> {

    //生成订单的方法
    String saveOrder(String courseId, String memberIdByJwtToken);

    //查询订单的方法
    void pageQuery(Page<Order> pageParam, OrderQuery order);


    List<Order> getCourseQuery(String course);
}
