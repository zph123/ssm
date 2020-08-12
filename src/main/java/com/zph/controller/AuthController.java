package com.zph.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zph.domain.User;
import com.zph.handler.AuthInterceptorHandler;
import com.zph.service.UserService;
import com.zph.utils.Jwt;
import com.zph.utils.Msg;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@Validated
public class AuthController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public Msg login(@Valid User user) throws JsonProcessingException {
        User userInfo = userService.selectUserByNameAndPassword(user);
        if (userInfo != null){
            // 登录成功：有效时间：一天
            long ttlMillis = 24 * 60 * 60 * 1000;
            ObjectMapper mapper = new ObjectMapper();
            String jws = Jwt.createJWT(userInfo.getId().toString(),mapper.writeValueAsString(userInfo),ttlMillis);
            return Msg.success("登录成功").add("token",jws);
        }
        return Msg.message(401,"登录失败");
    }

    @GetMapping(value = "/infoInfo")
    public Msg userInfo() throws IOException {
        User userInfo = AuthInterceptorHandler.userInfo();
        return Msg.success("用户信息").add("userInfo",userInfo);
    }

}
