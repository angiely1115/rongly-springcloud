package com.rongly.framework.demo.sharingStrategy;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * 自定义标准分片策略，使用精确分片算法（=与IN）
 * @author JiHao
 *
 */
@Slf4j
public class PreciseModuleTableShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames,
                             PreciseShardingValue<Long> preciseShardingValue) {
        log.info("collection:" + availableTargetNames+ ",preciseShardingValue:" + preciseShardingValue);
        for (String name : availableTargetNames) {
            // =与IN中分片键对应的值
            String value = String.valueOf(preciseShardingValue.getValue());
            // 分库的后缀
            int i = 1 ;
            // 求分库后缀名的递归算法
            if (name.endsWith("_" + countTableNum(Long.parseLong(value), i))) {
                log.info("路由到的表：{}",name);
                return name;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 计算该量级的数据在哪个表
     * @return
     */
    private String countTableNum(long columnValue, int i){
        // ShardingSphereConstants每个库中定义的数据量
        long left = ShardingSphereConstants.tableAmount * (i-1);
        long right = ShardingSphereConstants.tableAmount * i;
        if(left < columnValue && columnValue <= right){
            return String.valueOf(i);
        }else{
            i++;
            return countTableNum(columnValue, i);
        }
    }

}