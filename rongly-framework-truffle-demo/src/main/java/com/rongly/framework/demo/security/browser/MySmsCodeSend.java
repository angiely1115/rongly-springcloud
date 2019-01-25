package com.rongly.framework.demo.security.browser;

import com.xs.rongly.framework.stater.security.spring.security.core.code.sms.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: lvrongzhuan
 * @Description: 验证码发送器
 * @Date: 2019/1/25 16:34
 * @Version: 1.0
 * modified by:
 */
@Component
@Slf4j
public class MySmsCodeSend implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
      long codeR = ThreadLocalRandom.current().nextLong(1000,9999);
      log.info("手机号：{}接收验证码为：{}",mobile,codeR);
    }
}
