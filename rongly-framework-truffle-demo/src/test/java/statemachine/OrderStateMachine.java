package statemachine;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.rongly.framework.demo.entity.OrderEntity;
import com.rongly.framework.demo.statemachine.OrderEventHandler;
import com.rongly.framework.demo.statemachine.event.OrderEvent;
import com.rongly.framework.demo.statemachine.state.OrderStates;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: lvrongzhuan
 * @Description: 订单状态机测试
 * @Date: 2018/12/19 16:21
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkDemoApplication.class)
@Slf4j
public class OrderStateMachine {
    @Autowired
    private OrderEventHandler orderEventHandler;
    @Autowired
    private StateMachine<OrderStates, OrderEvent> stateMachine;
    @Test
    public void start(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAmount(111.00);
        orderEntity.setBuyUserId(11111L);
//        orderEventHandler.createOrder();
        stateMachine.start();
        stateMachine.sendEvent(OrderEvent.PAY);
    }
}
