package com.tasty.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

/*
 *@author:zhouxin
 *@date:2021/12/14 16:41
 *@description: 登录拦截器，在没有登录的情况下，不能让游客访问到后台管理页面
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
//    1.继承HandlerInterceptorAdapter适配器，重写预处理方法preHandle
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        2.对session进行判断，看是否有用户，没有的话重定向到登录页面，给拦截掉
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
//    3.还需在主配置文件WebConfig中指定拦截的内容
}
