package com.rongly.zupu.system.shiro.config;

import com.rongly.zupu.utils.SerializeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/16 14:35
 * @Version: 1.0
 * modified by:
 * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
 * MemorySessionDAO 直接在内存中进行会话维护
 * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
 */
//@Component
@Slf4j
public class RedisSessionDao extends EnterpriseCacheSessionDAO {
    @Autowired
   private ShiroRedisCacheManager shiroRedisCacheManager;

    private ShiroRedisCache shiroRedisCache;
    @PostConstruct
    public void init(){
        shiroRedisCache = (ShiroRedisCache) shiroRedisCacheManager.createCache("redis_session_dao:");
    }

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        log.info("创建session：{} sessionId:{}",session,sessionId);
        shiroRedisCache.put(sessionId.toString().getBytes(), SerializeUtil.serialize(session));
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        log.info("获取session:{} sessionId：{}",session,sessionId);
        if(session == null){
            byte[] bytes = (byte[]) shiroRedisCache.get(sessionId.toString().getBytes());;
            if(bytes != null && bytes.length > 0){
                session = (Session) SerializeUtil.unserialize(bytes);
            }
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
//        session.touch();
//        super.doUpdate(session);
        //如果会话过期/停止 没必要再更新了
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }

            if (session instanceof ShiroSession) {
                // 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
                ShiroSession ss = (ShiroSession) session;
                if (!ss.isChanged()) {
                    return;
                }
                //如果没有返回 证明有调用 setAttribute往redis 放的时候永远设置为false
                ss.setChanged(false);
            }
        log.info("session 最后一个更新时间:{},session 开始时间:{},session过期时间:{}",session.getLastAccessTime(),session.getStartTimestamp(),session.getTimeout());
        log.info("修改session：{} sessionId:{}",session,session.getId());
        shiroRedisCache.put(session.getId().toString().getBytes(), SerializeUtil.serialize(session));
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
//        super.doDelete(session);
        shiroRedisCache.remove(session.getId() + "");
        log.info("删除session：{} sessionId:{}",session,session.getId());
    }

    /**
     * 获取所有在线的用户
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
       return shiroRedisCache.values();
    }
}
