package com.rongly.framework.demo.security.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/24 17:05
 * @Version: 1.0
 * modified by:
 */
@Getter
@Setter
@ToString
public class UserInfo extends User implements Serializable{
     private long userId;
     private String email;
     private String mobile;

    public UserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
