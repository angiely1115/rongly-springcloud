package com.rongly.zupu.common.aspect;

import com.rongly.zupu.common.annotation.Log;
import com.rongly.zupu.dao.system.LogDao;
import com.rongly.zupu.entity.system.LogDO;
import com.rongly.zupu.entity.system.UserDO;
import com.rongly.zupu.utils.HttpContextUtils;
import com.rongly.zupu.utils.IPUtils;
import com.rongly.zupu.utils.R;
import com.rongly.zupu.utils.ShiroUtils;
import com.vip.vjtools.vjkit.time.DateUtil;
import com.xs.rongly.framework.stater.core.base.autoJsonConfig.ObjectMapperJson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/14 16:34
 * @Version: 1.0
 * modified by:
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private Executor executor;
    @Autowired
    private LogDao logMapper;
    @Autowired
    private ObjectMapperJson mapperJson;

    @Pointcut(value = "@annotation(com.rongly.zupu.common.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "logPointCut()")
    public Object aroundHandle(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Object object = R.error();
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("执行异常", throwable);
        }

        HttpServletRequest httpServletRequest = HttpContextUtils.getHttpServletRequest();
        final String result = " 请求返回结果:"+mapperJson.obj2string(object);
        final long time = System.currentTimeMillis() - startTime;
        executor.execute(() -> {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Log annotation = method.getAnnotation(Log.class);
            String desc = annotation.value();
            LogDO logDO = new LogDO();
            logDO.setGmtCreate(ZonedDateTime.now());
            String className = joinPoint.getTarget().getClass().getName();
            logDO.setMethod(className+"#"+method.getName());
            logDO.setOperation(desc+result);
            String ip = IPUtils.getIpAddr(httpServletRequest);
            Object[] objects = joinPoint.getArgs();
            if (objects!=null) {
                objects = Arrays.copyOf(objects,objects.length-1);
                String params =  mapperJson.obj2string(objects);
                if (params.length() > 500) {
                    params = params.substring(0,500);
                }
                logDO.setParams(params);
            }
            logDO.setTime(time);


            logDO.setIp(ip);
            UserDO userDO = ShiroUtils.getUser();
            if(userDO!=null){
                logDO.setUserId(userDO.getUserId());
                logDO.setUsername(userDO.getUsername());
            }else {
                logDO.setUserId(-1);
                logDO.setUsername("匿名操作");
            }
            logMapper.save(logDO);
        });
        return object;

    }
}
