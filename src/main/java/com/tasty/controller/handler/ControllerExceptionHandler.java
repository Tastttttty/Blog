package com.tasty.controller.handler;

/*
 *@author:zhouxin
 *@date:2021/12/13 19:17
 *@description: 编写一个自定义拦截器，针对如资源找不到异常
 */

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //表明所有的Controller请求都将通过此过滤器
public class ControllerExceptionHandler {

    //将异常记录到日志中
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    //异常时的处理方法
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        //记录异常信息：请求的url，具体的异常信息
        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(), e);

        //当标识了状态码的时候就不拦截
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        //将记录的异常信息返回到error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURI());
        mv.addObject("exception", e);
        mv.setViewName("error/error");//设置跳转的html页面
        return mv;
    }

}
