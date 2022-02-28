package com.zheng.eduservice.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "评论查询对象", description = "评论查询对象封装")
@Data
public class CommentQuery implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "课程名称")
        private String coursetitle;

    @ApiModelProperty(value = "用户名字")
    private String  nickname;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
