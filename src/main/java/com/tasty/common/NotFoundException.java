package com.tasty.common;

/*
 *@author:zhouxin
 *@date:2021/12/13 19:47
 *@description:自定义一个异常类，专门用来应对资源找不到，将NotFoundException错误标记成404错误
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ResponseStatus(HttpStatus.NOT_FOUND)注解表示资源找不到的状态码，标识了状态码的时候就不拦截
@ResponseStatus(HttpStatus.NOT_FOUND)
//将NotFoundException错误标记成404错误
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
//资源找不到时，跳过拦截器，不走error下的页面，直接走404页面
//继承RuntimeException，实现继承RuntimeException的构造函数