package com.rongly.framework.demo.security.browser;

import com.rongly.framework.demo.security.domain.UserInfo;
import com.xs.rongly.framework.stater.web.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author: lvrongzhuan
 * @Description: 用户detailsservice
 * @Date: 2019/1/24 16:49
 * @Version: 1.0
 * modified by:
 */
@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)  {

        //根据用户名查出用户信息
        if("baisuz".equals(username)){
            throw new BizException("100","用户不存在");
        }
        String passwd = passwordEncoder.encode("123456");
        System.out.println("数据库加密后的密码:"+passwd);
        UserInfo userInfo = new UserInfo(username,"$2a$11$XEv6Nw/5aiHkcuVZPHM4/utuIFXjI.g0MmYYYsLtDnhXkPqMnNHdi", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        userInfo.setEmail("522516433@qq.com");
        userInfo.setUserId(1000L);
        userInfo.setMobile("18811896321");
        return userInfo;
    }
}
