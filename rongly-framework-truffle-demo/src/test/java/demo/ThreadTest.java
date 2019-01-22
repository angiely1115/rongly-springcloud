package demo;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.vip.vjtools.vjkit.concurrent.Concurrents;
import com.xs.rongly.framework.stater.core.base.util.concurrent.ConcurrentTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/14 17:10
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkDemoApplication.class)
@Slf4j
public class ThreadTest {
    @Autowired
    private Executor executor;

    public void test(){
        Integer str = 1;
        ConcurrentTest<Integer> integerConcurrentTest = new ConcurrentTest<>();
        integerConcurrentTest.start(10, 10, executor,str1->{});
        executor.execute(()->{
            System.out.println(str);
        });
    }

}
