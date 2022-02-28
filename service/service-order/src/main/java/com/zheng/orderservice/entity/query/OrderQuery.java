package com.zheng.orderservice.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "Order查询对象", description = "订单查询对象封装")
@Data
public class OrderQuery implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "课程名")
    private String courseTitle;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}


