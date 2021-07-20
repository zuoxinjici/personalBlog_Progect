package com.example.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 获取流切片得到相应信息
 * @author QianMo
 * @date 2021/7/18 21:54
 */

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.blog.controller.*.*(..))")
    public void logAspect(){}

    @Before("logAspect()")
    public void BeforeAop(JoinPoint joinPoint){

        //通过attribute获取请求request进而获取ip,url,通过连接点joinpoint获取method和请求参数args[]
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //调用方法名称
        String Method = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,Method,args);
        logger.info("Request : {}",requestLog);
        logger.info("---------------BeforeAop----------------");
    }

    @After("logAspect()")
    public void AfterAop(){
        logger.info("------------------AfterAop--------------------");

    }

    @AfterReturning(returning = "result",pointcut = "logAspect()")
    public void AfterAopReturn(Object result){
        logger.info("result : {}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String Method;
        private Object[] args;

        public RequestLog(String url, String ip, String Method, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.Method = Method;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", Method='" + Method + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}


