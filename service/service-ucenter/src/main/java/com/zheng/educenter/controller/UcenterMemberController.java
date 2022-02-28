package com.zheng.educenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.imageio.plugins.common.BitFile;
import com.zheng.commonutils.JwtUtils;
import com.zheng.commonutils.R;
import com.zheng.commonutils.order.UcenterMemberOrder;
import com.zheng.servicebase.exceptionhandler.GuliException;
import com.zheng.educenter.entity.UcenterMember;
import com.zheng.educenter.entity.vo.LoginVo;
import com.zheng.educenter.entity.vo.RegisterVo;
import com.zheng.educenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-11-30
 */
@RestController
@RequestMapping("/educenter/apimember")
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }



    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember byId = memberService.getById(memberId);
            return R.ok().data("item", byId);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }

    //根据token字符串获取用户信息
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberOrder getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = memberService.getById(id);
        UcenterMemberOrder memeber = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,memeber);
        return memeber;
    }

    //统计某一天的注册人数
    @GetMapping(value = "countregister/{day}")
    public R registerCount(
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }

    @ApiOperation(value = "查询手机号是否被注册")
    @GetMapping("{mobile}")
    public R mobile(@PathVariable String mobile){
        //查询数据库中是否存在相同的手机号码
        UcenterMember mobile1 = memberService.getOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(mobile1 != null) {
            return R.ok().data("mobile","false");
        }
        return R.ok().data("mobile","true");
    }
}

