package com.rongly.zupu.system.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
public class BDSessionListener implements SessionListener {

	private final AtomicInteger sessionCount = new AtomicInteger(0);

	@Override
	public void onStart(Session session) {
		sessionCount.incrementAndGet();
		log.info("session 开始sessionCount:{} ",sessionCount);
	}

	@Override
	public void onStop(Session session) {
		sessionCount.decrementAndGet();
		log.info("session 停止sessionCount:{} ",sessionCount);
	}

	@Override
	public void onExpiration(Session session) {
		sessionCount.decrementAndGet();
		log.info("session 过期sessionCount:{} ",sessionCount);
	}

	public int getSessionCount() {
		return sessionCount.get();
	}

}
