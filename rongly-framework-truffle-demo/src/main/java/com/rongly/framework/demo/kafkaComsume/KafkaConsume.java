package com.rongly.framework.demo.kafkaComsume;

import com.xs.rongly.framework.stater.kafka.autoConfig.RonglyKakfaMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description: kakfa客户端
 * @Date: 2018/11/27 13:59
 * @Version: 1.0
 * modified by:
 */
@Component
@Slf4j
public class KafkaConsume {

    @KafkaListener(topics = "test-hello-kafka-topic",containerFactory = "kafkaListenerContainerFactory")
    public void helloTopicComsume(RonglyKakfaMessage ronglyKakfaMessage,Acknowledgment acknowledgment) throws InterruptedException {
        log.info("消费 topic:{},参数:{}","test-hello-kafka-topic",ronglyKakfaMessage);
//        TimeUnit.MINUTES.sleep(5);
        acknowledgment.acknowledge();
    }

    /**
     * 收动提交
     * @param ronglyKakfaMessage
     * @param acknowledgment
     */
    @KafkaListener(topics = "test-hello-kafka-ack-topic",containerFactory = "kafkaListenerContainerFactory")
    public void helloTopicAckComsume(RonglyKakfaMessage ronglyKakfaMessage, Acknowledgment acknowledgment){
        log.info("消费 topic:{},参数:{}","test-hello-kafka-ack-topic",ronglyKakfaMessage);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "test-hello-kafka-not-ack-topic",containerFactory = "kafkaListenerContainerFactory")
    public void helloTopicNoAckComsume(RonglyKakfaMessage ronglyKakfaMessage, Acknowledgment acknowledgment){
        log.info("消费 topic:{},参数:{}","test-hello-kafka-not-ack-topic",ronglyKakfaMessage);
        acknowledgment.acknowledge();
    }


}
