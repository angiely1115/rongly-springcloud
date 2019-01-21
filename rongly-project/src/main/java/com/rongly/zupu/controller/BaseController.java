package com.rongly.zupu.controller;

import com.rongly.zupu.entity.system.UserDO;
import com.rongly.zupu.utils.ShiroUtils;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Integer getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}