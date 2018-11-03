package com.rongly.springcloud.zuul.config;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/1 14:30
 * @Version: 1.0
 * modified by:
 */
@Component
public class GroovyRunner implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        MonitoringHelper.initMocks();
        FilterLoader.getInstance().setCompiler(new GroovyCompiler());
        FilterFileManager.setFilenameFilter(new GroovyFileFilter());
        FilterFileManager.init(10,"D:\\resource\\workspace\\fenbushi2017\\rongly-springcloud-zuul\\src\\main\\java\\com\\rongly\\springcloud\\zuul\\groovy");
    }
}
