package com.rongly.zupu;

import com.xs.rongly.framework.stater.web.filter.xss.EnableXss;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/13 17:28
 * @Version: 1.0
 * modified by:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan(basePackages = "com.rongly.zupu.dao")
@EnableXss
@ServletComponentScan
public class ZupuRonlyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZupuRonlyApplication.class,args);
    }
}
