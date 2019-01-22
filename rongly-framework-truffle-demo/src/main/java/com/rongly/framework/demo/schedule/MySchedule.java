package com.rongly.framework.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description: 计划任务
 * @Date: 2019/1/22 14:43
 * @Version: 1.0
 * modified by:
 * 使用方式
 * 如果要停止某个定时任务
 *
 * curl  -X"DELETE" '127.0.0.1:8082/actuator/hn-scheduledtasks?name=方法名称'
 *
 * 例子：curl  -X"DELETE" '127.0.0.1:8082/actuator/hn-scheduledtasks?name=com.huinong.framework.sample.shedlock.job.ShedLockJob.checkTask1'
 *
 *
 *
 * 如果要修改某个定时任务的执行周期
 *
 * curl -XPOST '127.0.0.1:8082/actuator/rongly-scheduledtasks' --header "Content-Type: application/json" --data '{"name":"方法名称","cron":"执行周期"}'
 *
 * 例子：
 *
 * curl -XPOST '127.0.0.1:8082/actuator/rongly-scheduledtasks' --header "Content-Type: application/json" --data '{"name":"com.huinong.framework.sample.shedlock.job.ShedLockJob.checkTask1","cron":"0/7 * * * * ?"}'
 *
 *
 *
 * 如果要触发某个定时任务
 *
 * curl  -XGET '127.0.0.1:8082/actuator/rongly-scheduledtasks?name=方法名称'
 *
 * curl  -XGET '127.0.0.1:8082/actuator/rongly-scheduledtasks?name=com.huinong.framework.sample.shedlock.job.ShedLockJob.checkTask1'
 */
@Slf4j
@Component
public class MySchedule {

    @Scheduled(cron = "0/2 * * * * ? ")
    @SchedulerLock(name = "testSchedule1",lockAtLeastForString = "3000")
    public void testSchedule1(){
      log.info("testSchedule 1 执行>>>>>>>");
    }

    @Scheduled(cron = "0/2 * * * * ? ")
    @SchedulerLock(name = "testSchedule1",lockAtLeastForString = "3000")
    public void testSchedule2(){
        log.info("testSchedule 2 执行>>>>>>>");
    }
}
