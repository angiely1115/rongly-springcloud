package com.rongly.zupu.sso.web.sample.controller;

import com.rongly.zupu.core.conf.Conf;
import com.rongly.zupu.core.entity.ReturnT;
import com.rongly.zupu.core.user.XxlSsoUser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        model.addAttribute("xxlUser", xxlUser);
        return "/main";
    }

    @RequestMapping(value = "/json",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ReturnT<XxlSsoUser> json(Model model, HttpServletRequest request) {
        XxlSsoUser xxlUser = (XxlSsoUser) request.getAttribute(Conf.SSO_USER);
        return new ReturnT(xxlUser);
    }

}