package com.rongly.springcloud.zuul.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/6 14:37
 * @Version: 1.0
 * modified by:
 */
public class MyLoadBalancerRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        return null;
    }
}
