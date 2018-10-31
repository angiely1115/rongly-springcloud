package com.rongly.springcloud.client.dept.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/1 15:07
 * @Version: 1.0
 * modified by:
 */
@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
    public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response){
        e.printStackTrace();
        Map map = new HashMap();
        map.put("name","白素贞");
        map.put("age",1000);
       Integer status = response.getStatus();
        System.out.println("javax.servlet.error.status_code:"+status);
        request.setAttribute("javax.servlet.error.status_code",500);
        request.setAttribute("ext",map);
        //自适应接口和浏览器访问
        return "forward:/error";
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.error("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        int errorCode = 700004 ;
    }

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.error("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        int errorCode = 700005 ;
        //自适应接口和浏览器访问
        return "forward:/error";
    }
}
