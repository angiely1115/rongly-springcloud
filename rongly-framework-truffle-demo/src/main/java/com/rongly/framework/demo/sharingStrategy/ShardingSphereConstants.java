package com.rongly.framework.demo.sharingStrategy;

/**
 * ShardingSphere中用到的常量
 * @author JiHao
 *
 */
public class ShardingSphereConstants {

    /**
     * 订单、优惠券相关的表，按用户数量分库，64w用户数据为一个库
     * (0,64w]
     */
    public static int databaseAmount = 640000;

    /**
     * 一个订单表里存10000的用户订单
     * (0,1w]
     */
    public static int tableAmount = 10000;

}