package com.ant.shop.asauth.controller;

import com.ant.shop.asorm.entity.DeviceInfo;
import com.ant.shop.asorm.entity.WeixinInfo;
import com.ant.shop.asorm.entity.auth.Admin;
import com.ant.shop.asorm.mapper.auth.AdminMapper;
import com.google.gson.Gson;
import constant.RedisKeyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import response.ResultModel;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.Jscode2sessionResult;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Value("${weixin.miniProgram.appId}")
    private String appId;

    @Value("${weixin.miniProgram.appSecret}")
    private String appSecret;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AdminMapper adminMapper;

    @GetMapping("/member")
    public Principal user(Principal member) {
        return member;
    }
    @PostMapping("/register")

    public ResultModel registerDevice(@RequestParam(required = true) String source, @RequestParam(required = true) String deviceId, Principal member){
        if(StringUtils.isEmpty(deviceId)){
            return ResultModel.error("设备ID不能为空");
        }
        Gson gson = new Gson();
        String userId = member.getName();
        Admin admin = adminMapper.selectByPrimaryKey(Integer.parseInt(userId));

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setUserId(userId);
        deviceInfo.setUsername(admin.getUsername());
        deviceInfo.setSource(source);


        deviceInfo.setDeviceId(deviceId);
        String redisKey= RedisKeyConstant.REDIS_DEVICE_INFO + ":" + userId;
        redisTemplate.opsForSet().add(redisKey,gson.toJson(deviceInfo));
        return ResultModel.ok();
    }

    @PostMapping("/register-wx")
    public ResultModel registerOpenId(@RequestParam(required = true) String code, Principal member){
        Gson gson = new Gson();
        Jscode2sessionResult jscode2sessionResult = SnsAPI.jscode2session(appId, appSecret, code);
        String openId = jscode2sessionResult.getOpenid();
        String unionId = jscode2sessionResult.getUnionid();
        String userId = member.getName();

        WeixinInfo weixinInfo = new WeixinInfo();
        weixinInfo.setUserId(Integer.parseInt(userId));
        weixinInfo.setUnionId(unionId);
        weixinInfo.setOpenId(openId);
        String redisKey= RedisKeyConstant.REDIS_WEIXIN_INFO + ":" + userId;
        redisTemplate.opsForSet().add(redisKey,gson.toJson(weixinInfo));
        return ResultModel.ok();
    }
}
