package zookeeper;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.vip.vjtools.vjkit.collection.ArrayUtil;
import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.collection.MapUtil;
import com.xs.rongly.framework.stater.core.base.util.concurrent.ConcurrentTest;
import com.xs.rongly.framework.stater.zookeeper.autoConfig.ZookeeperOper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Author: lvrongzhuan
 * @Description: zookeeper测试
 * @Date: 2018/11/24 12:59
 * @Version: 1.0
 * modified by:
 */
@RunWith(value= SpringRunner.class)
@SpringBootTest(classes=FrameworkDemoApplication.class)
public class ZookeeperDemoTest {
    @Autowired
    private ZookeeperOper zookeeperOper;
    @Autowired
    private Executor executor;

    /**
     * 线程不安全
     */
    @Test
    public void test(){
        ArrayList arrayList = ListUtil.newArrayList();
        ConcurrentTest<ArrayList> arrayListConcurrentTest = new ConcurrentTest();
        arrayListConcurrentTest.t = arrayList;
        Integer a = 1000;
        arrayListConcurrentTest.start(5000, 100, executor, new Consumer<ArrayList>() {
                    @Override
                    public void accept(ArrayList arrayList1) {
                        arrayList1.add(1);
                       /* try {
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100)*10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                });
//        System.out.println(zookeeperOper);
        System.out.println("objectMap 大小："+arrayList.size());
    }

    @Test
    public void testZookeeperLock(){
        ArrayList arrayList = ListUtil.newArrayList();
        ConcurrentTest<ArrayList> arrayListConcurrentTest = new ConcurrentTest();
        arrayListConcurrentTest.t = arrayList;
        String z_path = "/mylock";
        arrayListConcurrentTest.start(5000, 100, executor, new Consumer<ArrayList>() {
            @Override
            public void accept(ArrayList arrayList1) {
                //感觉连接不上
                zookeeperOper.distributeLock(z_path,()-> arrayList1.add(1));

                       /* try {
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100)*10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
            }
        });
//        System.out.println(zookeeperOper);
        System.out.println("objectMap 大小："+arrayList.size());
    }

    @Test
    public void test3(){
        String z_path = "/myData";
       zookeeperOper.delete(z_path);
      //  System.out.println(data);
    }
}
