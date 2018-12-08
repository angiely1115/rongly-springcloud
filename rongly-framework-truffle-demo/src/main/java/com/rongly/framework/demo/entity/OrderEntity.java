package com.rongly.framework.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/8 17:21
 * @Version: 1.0
 * modified by:
 */
@Data
@TableName("hn_order")
public class OrderEntity implements Serializable {

    /** 主键id */
    @TableId
    private Long id;

    /** 子订单编号 */
    private String orderCode;

    /** 主订单编号 */
    private String mainOrderCode;

    /** 买家id */
    private Long buyUserId;

    /** 卖家id */
    private Long saleUserId;

    /** 类型（1：采购；2：供应 3 活动 4.零批） */
    private Integer fromType;

    /** 0:待付款1 1:待发货; 2:待确认收货; 3:交易成功; 4:关易关闭,5:待付款2 */
    private Integer state;

    /** 账户中心流水 */
    private String paymentNo;

    /** 应付金额 */
    private Double totalAmount;

    /** 实付金额 */
    private Double amount;

    /** 是否退货退款(0:未退货退款;1:退货退款中;2:已退货退款;3：卖家拒绝退货退款) */
    private Integer backFlag;

    /** 买家留言 */
    private String buyerRemark;

    /** 发货时间 */
    private Date sendTime;

    /** 确认收货时间 */
    private Date receiveTime;

    /** 自动收货时间 */
    private Date autoReceiveTime;

    /** 是否评价（0：未评价，1：已评价） */
    private Integer evaluate;

    /** 评价时间 */
    private Date evaluateTime;

    /** 延长自动收货次数 */
    private Integer delayNum;

    /** 创建人 */
    private String createUserId;

    /** 创建时间 */
    private Date createTime;

    /** 修改人 */
    private String modifyUserId;

    /** 修改时间 */
    private Date modifyTime;

    /** 关闭时间 */
    private Date cancelTime;

    /** 取消类型(1.买家取消;2.卖家取消;3系统自动取消) */
    private Integer cancelType;

    /** 取消原因 */
    private String cancelReason;

    /** 取消说明 */
    private String cancelCaption;

    /** 买家名称 */
    private String buyer;

    /** 卖家名称 */
    private String seller;

    /**订单属性 1正常单（默认） 2买家保障单 464版本增加*/
    private Integer orderProperty = 1;
    /**期望发货时间(付款后的几天后) 464版本增加*/
    private Integer expectSendGoodsTime;
    /**期望发货时间 464版本增加*/
    private Date expectTime;

    private Integer deleteBySystem;//系统删除

    private Integer deleteByBuyUser;//买家删除

    private Integer deleteBySaleUser;//卖家删除

    /** 付款状态(0:待付款;1:买家已付款到平台;2:已付款到卖家) **/
    private Integer payFlag;
    /** 买家退款金额 冗余字段 **/
    @TableField(value = "back_amount")
    private Double backAmount;
    /**活动ID**/
    private Long activitieId;
    /**
     ** 订单类型 fromType (1.供应 2.采购,3 活动 4.零批 5.样品,6.IM消息订单)
     * 订单流程类型 1 大宗流程  fromType = 1、2  ，2 零批流程 fromType = 3、4、5
     */
    private Integer flowType;

}
