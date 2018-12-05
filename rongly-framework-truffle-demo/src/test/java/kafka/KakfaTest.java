package kafka;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.xs.rongly.framework.stater.kafka.autoConfig.RonglyKafkaProduce;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.network.Send;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/27 11:05
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkDemoApplication.class)
@Slf4j
public class KakfaTest {
    @Autowired
    private RonglyKafkaProduce ronglyKafkaProduce;

    public void sendMessage(String topic,String message){
        String opid = UUID.randomUUID().toString().replace("-","");
        ListenableFuture<SendResult<byte[], byte[]>> listenableFuture =   ronglyKafkaProduce.sendImmediateMessge(topic,message,opid);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<byte[], byte[]>>(){
            @Override
            public void onSuccess(SendResult<byte[], byte[]> result) {
                System.out.println("消息发送成功 o:"+result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("消息发送失败");
                throwable.printStackTrace();
            }
        });
    }

    @Test
    public void kafkatest1(){
        log.info("99999999999999");
        this.sendMessage("test-hello-kafka-topic","hello-kafka");
        this.sendMessage("test-hello-kafka-topic","hello-kafka1");
        this.sendMessage("test-hello-kafka-topic","hello-kafka2");
//        this.sendMessage("test-hello-kafka-ack-topic","hello-fafka-ack");
//        this.sendMessage("test-hello-kafka-not-ack-topic","hello-fafka-not-ack");
    }
}
