package com.rongly.zupu.system.shiro;

import com.rongly.zupu.dao.system.UserDao;
import com.rongly.zupu.entity.system.UserDO;
import com.rongly.zupu.service.system.MenuService;
import com.rongly.zupu.utils.CacheUtils;
import com.rongly.zupu.utils.Md5Util;
import com.rongly.zupu.utils.ParamUtil;
import com.rongly.zupu.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserDao userMapper;
	@Autowired
	private MenuService menuService;

	/**
	 * 获取用户所有的权限
	 * @param arg0
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Integer userId = ShiroUtils.getUserId();
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	/**
	 * 登陆认证授权
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		// 查询用户信息
		UserDO user = userMapper.getByUserName(username);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		String salt = CacheUtils.getLoginSalt(username);
		if(ParamUtil.isEmpty(salt)){
			throw new UnknownAccountException("登录验证失败");
		}
		// 密码错误
		if (!password.equals(Md5Util.MD5(username + user.getPassword() + salt))) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		user.setPassword("");
		user.setFundPassword("");
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
