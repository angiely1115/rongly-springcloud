package com.rongly.framework.demo.security.browser;

import com.xs.rongly.framework.stater.security.spring.security.core.code.ValidateCode;
import com.xs.rongly.framework.stater.security.spring.security.core.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: lvrongzhuan
 * @Description: 自定义验证码生成器
 * @Date: 2019/1/28 12:01
 * @Version: 1.0
 * modified by:
 */
@Component("smsValidateCodeGenerator")
public class MySmsCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(ServletWebRequest servletWebRequest) {
        String code = ThreadLocalRandom.current().nextInt(1000,9999)+"";
        return new ValidateCode(code, 60);
    }
}
