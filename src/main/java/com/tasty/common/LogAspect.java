package com.tasty.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/13 20:01
 *@description:使用SpringBoot中的AOP切面进行日志处理
 */

@Aspect
@Component
public class LogAspect {

//    获取日志信息
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    定义切面
    @Pointcut("execution(* com.tasty.controller.*.*(..))")//pointcut 的作用就是提供一组规则来匹配joinpoint, 给满足规则的 joinpoint 添加 advice
    public void log(){}

//    在切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){//joinpoint表示所有可能被织入 advice 的候选的点
        //获取request请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取url，ip
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();

        //获取请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        //获取请求参数
        Object[] args = joinPoint.getArgs();

        //获取需要的参数后，在下面将其封装成一个RequestLog类
        RequestLog log = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", log);
    }

//    在切面之后执行
    @After("log()")
    public void doAfter(){}

//    返回之后拦截
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result : {}", result);
    }

    //将日志信息封装成一个类
    public class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
        //全参构造方法
        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
/*
部分注释
@Aspect注解：AOP切面作用
@Component注解：开启组件扫描，通过注解找到要扫描的对象
@Pointcut("execution(* com.tasty.controller..(..))")：定义切面，申明log()是一个切面，通过execution来表示需要拦截的类，这里表示拦截控制器下面的所有类所有方法
RequestLog：将请求的参数封装成一个内部类
在访问页面（controller）之前，拦截请求的URL、IP、调用的方法、传递的参数、返回的内容，并记录到日志
*/