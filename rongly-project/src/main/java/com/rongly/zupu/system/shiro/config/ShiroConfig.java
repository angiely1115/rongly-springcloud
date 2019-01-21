package com.rongly.zupu.system.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.rongly.zupu.system.shiro.ShiroLoginFilter;
import com.rongly.zupu.system.shiro.UserRealm;
import com.vip.vjtools.vjkit.collection.ListUtil;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;
//@Configuration
//@EnableConfigurationProperties(value = {ShiroProperties.class})
public class ShiroConfig {
	/*public ShiroConfig(ShiroProperties shiroProperties) {
		this.shiroProperties = shiroProperties;
	}*/

	public static final LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
	public static final String anon = "anon";
	public static final String logout = "logout";
	public static final String authc = "authc";
	//定义拦截链
	static{
		filterChainDefinitionMap.put("/favicon.ico", anon);
		filterChainDefinitionMap.put("/css/**", anon);
		filterChainDefinitionMap.put("/js/**", anon);
		filterChainDefinitionMap.put("/fonts/**", anon);
		filterChainDefinitionMap.put("/img/**", anon);
		filterChainDefinitionMap.put("/docs/**", anon);
		filterChainDefinitionMap.put("/druid/**", anon);
		filterChainDefinitionMap.put("/upload/**", anon);
		filterChainDefinitionMap.put("/files/**", anon);
		filterChainDefinitionMap.put("/logout", logout);
		filterChainDefinitionMap.put("/login", anon);
		filterChainDefinitionMap.put("/salt", anon);
		filterChainDefinitionMap.put("/chgPass", anon);
		filterChainDefinitionMap.put("/pay/order", anon);
		filterChainDefinitionMap.put("/pay/return/**", anon);
		filterChainDefinitionMap.put("/pay/card", anon);
		filterChainDefinitionMap.put("/pay/card/**", anon);
		filterChainDefinitionMap.put("/pay/qr", anon);
		filterChainDefinitionMap.put("/pay/qr/**", anon);
		filterChainDefinitionMap.put("/pay/pali", anon);
		filterChainDefinitionMap.put("/pay/pali/**", anon);
		filterChainDefinitionMap.put("/pay/mybank", anon);
		filterChainDefinitionMap.put("/pay/mybank/**", anon);
		filterChainDefinitionMap.put("/pay/notify/**", anon);
		filterChainDefinitionMap.put("/pay/order/**", anon);
		filterChainDefinitionMap.put("/**", authc);
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager,ShiroProperties shiroProperties) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//可以不需要这个拦截器
	/*	Map<String, Filter> filtersMap = new LinkedHashMap<>();
		filtersMap.put("shiroLoginFilter", shiroLoginFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);*/
		shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
		shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
		shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
//		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		List<String> stringList =  shiroProperties.getAnonUrl();
		if(ListUtil.isNotEmpty(stringList)){
			for (String url : stringList) {
				filterChainDefinitionMap.put(url,anon);
			}
		}
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	@Bean
	@ConditionalOnProperty(name = "rongly.shiro.cache",havingValue = "ehcache")
	public CacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:config/ehcache.xml");
		return em;
	}

/*	@Bean
	@ConditionalOnProperty(name = "shiro.cache",havingValue = "redis")
	@Primary
	public CacheManager getRedisCacheManager(RedisTemplate template) {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:config/ehcache.xml");
		return em;
	}

	private ShiroRedisCacheManager cacheManager(RedisTemplate template){
		return new ShiroRedisCacheManager(template);
	}*/

	/**
	 * @param userRealm
	 * @return
	 */
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager(UserRealm userRealm,CacheManager cacheManager,ShiroProperties shiroProperties,RedisSessionDao redisSessionDao) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm);
		manager.setCacheManager(cacheManager);
		manager.setSessionManager(sessionManager(shiroProperties,redisSessionDao));
		return manager;
	}

	@Bean
	UserRealm userRealm(CacheManager cacheManager) {
		UserRealm userRealm = new UserRealm();
		userRealm.setCacheManager(cacheManager);
		return userRealm;
	}


	/*@Bean
	SessionDAO sessionDAO() {
		MemorySessionDAO sessionDAO = new MemorySessionDAO();
		return sessionDAO;
	}*/
	@Bean("sessionFactory")
	public ShiroSessionFactory sessionFactory(){
		ShiroSessionFactory sessionFactory = new ShiroSessionFactory();
		return sessionFactory;
	}
	@Bean
	public SessionManager sessionManager(ShiroProperties shiroProperties,RedisSessionDao redisSessionDao) {
		DefaultWebSessionManager sessionManager = new ShiroSessionManager();
		Collection<SessionListener> listeners = new ArrayList<SessionListener>();
		listeners.add(new BDSessionListener());
		sessionManager.setSessionListeners(listeners);
		sessionManager.setSessionDAO(redisSessionDao);
		//超时时间
		sessionManager.setGlobalSessionTimeout(shiroProperties.getSessionTimeout());
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
		//去掉地址栏中的sessionId
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setSessionFactory(sessionFactory());
		return sessionManager;
	}


	@Bean("lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean("shiroLoginFilter")
    public ShiroLoginFilter shiroLoginFilter(){
        return  new ShiroLoginFilter();
    }

}
