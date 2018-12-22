package com.rongly.framework.demo.statemachine;

import com.rongly.framework.demo.statemachine.event.OrderEvent;
import com.rongly.framework.demo.statemachine.state.OrderStates;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @Author: lvrongzhuan
 * @Description: 状态机配置
 * @Date: 2018/12/19 15:16
 * @Version: 1.0
 * modified by:
 */
@Configuration
@EnableStateMachine
public class StatemachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvent> states) throws Exception {
        states.withStates().initial(OrderStates.UN_PAY)
                .states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvent> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStates.UN_PAY).target(OrderStates.PAY)
                .event(OrderEvent.PAY)
                .and()
                .withExternal()
                .source(OrderStates.PAY).target(OrderStates.WAITING_FOR_RECEIVE)
                .event(OrderEvent.SHIP)
                .and()
                .withExternal()
                .source(OrderStates.WAITING_FOR_RECEIVE).target(OrderStates.PERFECTION)
                .event(OrderEvent.RECEIVE);

    }
}
