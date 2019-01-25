package com.rongly.framework.demo.security.browser;

import com.xs.rongly.framework.stater.core.base.autoJsonConfig.ObjectMapperJson;
import com.xs.rongly.framework.stater.web.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/24 16:46
 * @Version: 1.0
 * modified by:
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private ObjectMapperJson objectMapperJson;

    @ResponseBody
    @GetMapping("me")
    public Object getUserInfo(Authentication user, HttpServletRequest request) {
        log.info(" user.getDetails():{}", user.getDetails());
        return user.getPrincipal();
    }


    @ResponseBody
    @GetMapping(value = "exception", produces = MediaType.TEXT_HTML_VALUE)
    public String exception() {
       throw new BizException("9999","大花朵333，异常啦");
    }

    @ResponseBody
    @GetMapping(value = "exception")
    public String exception2() {
        throw new BizException("9999","大花朵，异常啦");
    }
}
