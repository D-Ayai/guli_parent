package com.zheng.educenter.service;

import com.zheng.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zheng.educenter.entity.vo.LoginVo;
import com.zheng.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-11-30
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //会员注册
    public void register(RegisterVo registerVo);

    /**
     * 会员登录
     * @param loginVo
     * @return
     */
    public String login(LoginVo loginVo);

    //统计某一天的注册人数
    Integer countRegisterByDay(String day);


    UcenterMember getOpenIdMember(String openid);

}
