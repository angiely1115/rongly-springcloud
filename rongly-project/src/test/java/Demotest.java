import com.rongly.zupu.ZupuRonlyApplication;
import com.rongly.zupu.system.shiro.config.ShiroProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/16 13:02
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZupuRonlyApplication.class)
public class Demotest {
    @Autowired
    private ShiroProperties shiroProperties;
    @Test
    public void test(){
        System.out.println(shiroProperties);
    }
}
