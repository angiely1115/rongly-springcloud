package com.rongly.framework.demo.sharingStrategy;

import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义标准分库策略，使用范围分片算法（BETWEEN AND）
 * @author JiHao
 *
 */
@Slf4j
public class RangeModuleDatabaseShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(
            Collection<String> availableTargetNames,
            RangeShardingValue<Long> rangeShardingValue) {
        log.info("Range collection:" + availableTargetNames+ ",rangeShardingValue:" + rangeShardingValue);
        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = rangeShardingValue.getValueRange();
        // BETWEEN AND中分片键对应的最小值
        long lowerEndpoint = Long.parseLong(String.valueOf(valueRange.lowerEndpoint()));
        // BETWEEN AND中分片键对应的最大值
        long upperEndpoint = Long.parseLong(String.valueOf(valueRange.upperEndpoint()));
        // 分表的后缀
        int i = 1;
        List<Integer> arrs = new ArrayList<Integer>();
        // 求分表后缀名的递归算法
        List<Integer> list = countDatabaseNum(i, lowerEndpoint, upperEndpoint, arrs);
        for (Integer integer : list) {
            for (String each : availableTargetNames) {
                if (each.endsWith("_" + integer)) {
                  collect.add(each);
                }
            }
        }
        log.info("路由到的库：{}",collect);
        return collect;
    }

    /**
     * 计算该量级的数据在哪个表
     * @param i
     * @param lowerEndpoint 最小区间
     * @param upperEndpoint 最大区间
     * @return
     */
    private List<Integer> countDatabaseNum(int i, long lowerEndpoint, long upperEndpoint, List<Integer> arrs){
        long left = ShardingSphereConstants.databaseAmount * (i-1);
        long right = ShardingSphereConstants.databaseAmount * i;
        // 区间最大值小于分库最大值
        if(left < upperEndpoint && upperEndpoint <= right){
            arrs.add(i);
            return arrs;
        }else{
            if(left < lowerEndpoint && lowerEndpoint <= right){
                arrs.add(i);
            }
            i++;
            return countDatabaseNum(i, lowerEndpoint, upperEndpoint, arrs);
        }
    }

}