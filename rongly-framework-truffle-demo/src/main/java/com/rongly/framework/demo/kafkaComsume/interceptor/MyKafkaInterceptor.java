package com.rongly.framework.demo.kafkaComsume.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: kafka拦截器
 * @Date: 2018/11/27 16:22
 * @Version: 1.0
 * modified by:
 */
@Slf4j
public class MyKafkaInterceptor implements ProducerInterceptor<byte[],byte[]> {
    /**
     * 消息发送之前，可以修改消息信息 但是修改原消息分区信息
     * 也可以创建一个新的消息
     * @param producerRecord
     * @return
     */
    @Override
    public ProducerRecord<byte[], byte[]> onSend(ProducerRecord<byte[], byte[]> producerRecord) {
        log.info("消息发送之前：{}",producerRecord);
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        log.info("onAcknowledgement:消息发送消费之前或者消息发送失败");

    }

    @Override
    public void close() {
        log.info("interceptor close");
    }

    /**
     * 获取一些配置信息和增加一些配置
     * @param map
     */
    @Override
    public void configure(Map<String, ?> map) {
        log.info("interceptor configure：{}",map);

    }
}
