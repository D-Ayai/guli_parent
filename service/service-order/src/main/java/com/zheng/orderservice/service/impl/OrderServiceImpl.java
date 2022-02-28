package com.zheng.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zheng.orderservice.entity.vo.CourseVo;
import com.zheng.orderservice.entity.query.OrderQuery;
import com.zheng.orderservice.utils.OrderNoUtil;
import com.zheng.commonutils.order.CourseWebVoOrder;
import com.zheng.commonutils.order.UcenterMemberOrder;
import com.zheng.orderservice.client.EduClient;
import com.zheng.orderservice.client.UcenterClient;
import com.zheng.orderservice.entity.Order;
import com.zheng.orderservice.mapper.OrderMapper;
import com.zheng.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-12-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;
        //生成订单的方法
    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        CourseWebVoOrder courseDto = eduClient.getCourseInfoDto(courseId);
        System.out.println(courseDto);
        //远程调用用户服务，根据用户id获取用户信息
        UcenterMemberOrder ucenterMember = ucenterClient.getInfo(memberId);
        System.out.println(ucenterMember);
        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }

    @Override
    public void pageQuery(Page<Order> pageParam, OrderQuery order) {

        if (order == null){
            baseMapper.selectPage(pageParam, null);
            return;
        }
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();

        String orderNo = order.getOrderNo();
        String courseTitle = order.getCourseTitle();
        String begin = order.getBegin();
        String end = order.getEnd();

        if (!StringUtils.isEmpty(orderNo)) {
            queryWrapper.like("order_no", orderNo);
        }

        if (!StringUtils.isEmpty(courseTitle) ) {
            queryWrapper.like("course_title", courseTitle);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Order> getCourseQuery(String course) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        Page<Order> pageParam = new Page<>(1, 8);
        if (course == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return pageParam.getRecords();
        }

        if (!StringUtils.isEmpty(course)) {
            queryWrapper.like("course_id", course);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
        return pageParam.getRecords();
    }
}
