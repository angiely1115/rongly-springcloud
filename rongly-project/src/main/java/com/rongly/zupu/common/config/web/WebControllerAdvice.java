package com.rongly.zupu.common.config.web;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/15 5:30
 * @Version: 1.0
 * modified by:
 */
@ControllerAdvice
public class WebControllerAdvice {
    private final static String ERROR_STATE_CODE = "javax.servlet.error.status_code";

    /**
     * 会自动处理返回页面还是json
     * @param httpServletRequest
     * @return
     */
    @ExceptionHandler(value = {AuthorizationException.class})
    public String authorizationException(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute(ERROR_STATE_CODE,403);
        return "forward:/error";
    }
}
