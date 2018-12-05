package demo;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.rongly.framework.demo.entity.CartEntity;
import com.rongly.framework.demo.service.CartService;
import io.shardingsphere.api.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/30 11:11
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkDemoApplication.class)
public class DemoTest {
    @Autowired
    private CartService cartService;

    @Test
    public void testAddCart(){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setBuyUserId(111L);
        cartEntity.setProductId(333L);
        cartEntity.setQty(99);
        cartEntity.setSourceFrom(1);
        cartService.addCart(cartEntity);
        System.out.println(ZonedDateTime.now());
    }

    @Test
    @Transactional(readOnly = true) //开启只读事务走mybatis一级缓存
    public void testQuery1(){
        CartEntity cartEntity = cartService.selectById(8294);
        System.out.println(cartEntity);
        cartEntity = cartService.selectById(8294);
        System.out.println(cartEntity);
        cartEntity = cartService.selectById(8294);
        System.out.println(cartEntity);
    }

    @Test
    public void testQuery2(){
        CartEntity cartEntity = cartService.selectById(8294);
        System.out.println(cartEntity);
        //强制读主库 使用twr方式关闭
        try(HintManager hintManager = HintManager.getInstance()){
            hintManager.setMasterRouteOnly();
            System.out.println("*******************强制读主库*****************");
            cartEntity = cartService.selectById(8294);
            System.out.println(cartEntity);
            hintManager.close();//用完需要关闭
        }

        //强制读主库只对相邻的一条sql有效
       /* hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();*/
        cartEntity = cartService.selectById(8294);
        System.out.println(cartEntity);
    }

    @Test
    public void testUpdate1(){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(8294L);
        cartEntity.setProductId(8889L);
        cartService.updateById(cartEntity);
         cartEntity = cartService.selectById(8294);
         //没有使用到事务 不能加到这里
         int  i = 1/0;
        System.out.println(cartEntity);
    }

    /**
     * 多个从库轮流查询
     */
    @Test
    public void testMoreSlaver(){
        CartEntity cartEntity = cartService.selectById(8294);

        System.out.println(cartEntity);
        cartEntity = cartService.selectById(8294);
        System.out.println(cartEntity);
    }

}
