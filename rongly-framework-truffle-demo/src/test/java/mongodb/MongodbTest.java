package mongodb;

import com.rongly.framework.demo.FrameworkDemoApplication;
import com.rongly.framework.demo.mongodb.dao.MyUserDao;
import com.rongly.framework.demo.mongodb.entity.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/7 15:56
 * @Version: 1.0
 * modified by:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrameworkDemoApplication.class)
@Slf4j
public class MongodbTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MyUserDao myUserDao;

    /**
     * mongodb尝试第一次基本测试
     */
    @Test
    public void testMongodb1(){
        System.out.println("获取数据库的名称:"+mongoTemplate.getDb().getName());
    }

    /**
     * 插入数据
     */
    @Test
    public void testInsert(){
        MyUser myUser = new MyUser();
        myUser.setSex("女");
        myUser.setUserAddress("青城山");
        myUser.setUserAge(1000);
        myUser.setUserName("白素贞");
        mongoTemplate.insert(myUser);
    }

    @Test
    public void testInsert2(){
        MyUser myUser = new MyUser();
        myUser.setSex("女");
        myUser.setUserAddress("青城山113");
        myUser.setUserAge(1000);
        myUser.setUserName("白素贞");
        myUser = myUserDao.insert(myUser);
        System.out.println(myUser);
    }

    @Test
    public void testSave(){
        MyUser myUser = new MyUser();
        myUser.setSex("女");
        myUser.setUserAddress("青城山113");
        myUser.setUserAge(1000);
        myUser.setUserName("白素贞");
        myUser = myUserDao.save(myUser);
        System.out.println(myUser);
    }

    /**
     * 基本查询测试
     */
    @Test
    public void testQuery01(){
       List<MyUser> myUsers = myUserDao.findAll();
        System.out.println(myUsers);
        System.out.println("findbyID:"+myUserDao.findById("5c330dd68ebf2e1438259428"));
    }

    /**
     * 根据条件查询
     */
    @Test
    public void testQueryByCondition(){
//           myUserDao.findAll(Example.of())
        System.out.println(myUserDao.findMyUsersByUserName("白素贞"));
        System.out.println(myUserDao.findMyUserByUserAgeBetween(1,1000));

    }

    /**
     * 修改也使用save方法 存在即修改 不存在则插入
     */
    @Test
    public void testUpdate(){
        MyUser myUser = new MyUser();
        myUser.setSex("女");
        myUser.setUserAddress("黄山");
        myUser.setUserAge(500);
        myUser.setUserName("媚娘");
        myUser.setId("5c32dbe22a4a1a6d1f1d17f1");
        mongoTemplate.save(myUser);
        this.testQuery01();
    }
}
