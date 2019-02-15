package com.rongly.zupu.sso.server.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xuxueli 2018-03-22 23:51:51
 */
@Getter
@Setter
@ToString
public class UserInfo {

    private int userid;
    private String username;
    private String password;

}
