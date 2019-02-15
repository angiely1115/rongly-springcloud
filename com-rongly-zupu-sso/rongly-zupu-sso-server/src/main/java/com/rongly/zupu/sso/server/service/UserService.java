package com.rongly.zupu.sso.server.service;


import com.rongly.zupu.core.entity.ReturnT;
import com.rongly.zupu.sso.server.core.model.UserInfo;

public interface UserService {

     ReturnT<UserInfo> findUser(String username, String password);

}
