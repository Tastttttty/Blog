package com.tasty.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 *@author:zhouxin
 *@date:2021/12/14 16:39
 *@description:springboot自定义配置主类
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
        //重写其addInterceptors方法
    public void addInterceptors(InterceptorRegistry registry) {
        //将同级目录先的LoginInterceptor加入主配置文件,并为其指定拦截的内容
        registry.addInterceptor(new LoginInterceptor())
        //过滤的路径，除了登录所需的路径之外的所有/admin/*路径
            .addPathPatterns("/admin/*")
            .excludePathPatterns("/admin")
            .excludePathPatterns("/admin/login");
    }
}
/*
@Configuration注解：表明是一个有效的配置类
重写addInterceptors方法
指定要拦截的路径，这里拦截"admin"访问路径
*/