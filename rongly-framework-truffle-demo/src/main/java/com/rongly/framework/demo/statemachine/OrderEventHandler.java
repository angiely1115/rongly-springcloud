package com.rongly.framework.demo.statemachine;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongly.framework.demo.dao.read.OrderReadDao;
import com.rongly.framework.demo.dao.write.OrderWriteDao;
import com.rongly.framework.demo.entity.OrderEntity;
import com.rongly.framework.demo.statemachine.event.OrderEvent;
import com.rongly.framework.demo.statemachine.state.OrderStates;
import com.xs.rongly.framework.stater.core.base.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableWithStateMachine;

/**
 * @Author: lvrongzhuan
 * @Description: 事件处理器
 * @Date: 2018/12/19 15:59
 * @Version: 1.0
 * modified by:
 */
@WithStateMachine
@Slf4j
public class OrderEventHandler {
    @Autowired
    private OrderReadDao orderReadDao;
    @Autowired
    private OrderWriteDao orderWriteDao;
    @Autowired
    private StateMachine<OrderStates, OrderEvent> stateMachine;

    /**
     * 创建订单
     * @param orderEntity
     * @return
     */
    @OnTransition(target = "UN_PAY")
    public void createOrder(){
        log.info("创建订单......{}");
//        stateMachine.start();
//        return orderEntity;
    }

    @OnTransition(source = "UN_PAY",target = "PAY")
    public BaseResult pay(){
        log.info("变成已支付的》》》》》》》》》");
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderCode("D201711291511936994000100063");
        orderEntity = orderReadDao.selectOne(orderEntity);
        if(null==orderEntity){
            return BaseResult.fail("100001","订单不存在");
        }
        orderEntity.setState(1);
        EntityWrapper<OrderEntity> entityWrapper = new EntityWrapper<>(orderEntity);
        int i = orderWriteDao.update(orderEntity,entityWrapper);
        if (i==0){
            return BaseResult.fail("100002","更新订单失败");
        }
        return BaseResult.success("更新成功");
    }
}
