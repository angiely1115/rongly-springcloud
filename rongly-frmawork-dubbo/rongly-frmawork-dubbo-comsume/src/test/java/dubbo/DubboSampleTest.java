package dubbo;

import com.rongly.dubbo.module.content.DemoDubboComsume;
import com.rongly.dubbo.module.content.DubboComsumeDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/30 11:33
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboComsumeDemoApplication.class)
public class DubboSampleTest {
    @Autowired
    private DemoDubboComsume demoDubboComsume;

    /**
     * dubbo demo 连通性测试
     */
    @Test
    public void test1(){
        demoDubboComsume.demoDubbo1();
    }
}
