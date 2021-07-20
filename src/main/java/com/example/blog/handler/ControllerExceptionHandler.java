package com.example.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author QianMo
 * @date 2021/7/18 17:56
 * 拦截器，统一异常处理
 * ControllerAdvice拦截所有带有Controller注解的控制器
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * ModelAndView为返回页面加上后台数据的类
     * ExceptionHandler(Exception.class)表示方法可以进行异常处理，Exception级别的异常都会被拦截
     * @param request,exception
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception exception) throws Exception{
        logger.error("Resqust URL : {},Exception : {}",request.getRequestURL(),exception);

        //出现状态类异常转交给springboot本身处理（跳转页面）
        if(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null){
            throw exception;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("error/error");
        return modelAndView;

    }
}
