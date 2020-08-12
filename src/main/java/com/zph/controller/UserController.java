package com.zph.controller;

import com.zph.domain.User;
import com.zph.handler.AuthInterceptorHandler;
import com.zph.service.UserService;
import com.zph.utils.Msg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public Msg selectArticleForUser(User user) throws IOException {
        User userInfo = AuthInterceptorHandler.userInfo();
        user.setId(userInfo.getId());
        User selectArticleForUser = userService.selectArticleForUser(user);
        return Msg.success("ok").add("list",selectArticleForUser);
    }
}
