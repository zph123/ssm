package com.zph.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zph.domain.User;
import com.zph.utils.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInterceptorHandler implements HandlerInterceptor {
    // 定义一个线程域，存放登录用户
    private static final ThreadLocal<String> tl = new ThreadLocal<>();
    /**
     * 预处理方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null){
            throw new JwtException("token不存在");
        }
        if(!authHeader.startsWith("Bearer ")){
            throw new JwtException("token格式不正确");
        }
        String token=authHeader.substring(7);//提取token
        Claims user = Jwt.validateJWT(token);
        tl.set(user.getSubject());
        //System.out.println("preHandle拦截器");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //程序运行结束之后，删除线程
        tl.remove();
    }
    public static User userInfo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User userInfo = mapper.readValue(tl.get(),User.class);
        return userInfo;
    }
}

