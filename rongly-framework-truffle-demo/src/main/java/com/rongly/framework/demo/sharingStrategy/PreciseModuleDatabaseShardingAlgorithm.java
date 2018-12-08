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
public class PreciseModuleDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames,
                             PreciseShardingValue<Long> preciseShardingValue) {
        log.info("collection:" + availableTargetNames+ ",preciseShardingValue:" + preciseShardingValue);
        for (String name : availableTargetNames) {
            // =与IN中分片键对应的值
            String value = String.valueOf(preciseShardingValue.getValue());
            // 分库的后缀
            int i = 1;
            // 求分库后缀名的递归算法
            if (name.endsWith("_" + countDatabaseNum(Long.parseLong(value), i))) {
                log.info("路由到的库：{}",name);
                return name;
            }
        }

        throw new UnsupportedOperationException();
    }

    /**
     * 计算该量级的数据在哪个数据库
     * @return
     */
    private String countDatabaseNum(long columnValue, int i){
        // ShardingSphereConstants每个库中定义的数据量
        long left = ShardingSphereConstants.databaseAmount * (i-1);
        long right = ShardingSphereConstants.databaseAmount * i;
        if(left < columnValue && columnValue <= right){
            return String.valueOf(i);
        }else{
            i++;
            return countDatabaseNum(columnValue, i);
        }
    }

}