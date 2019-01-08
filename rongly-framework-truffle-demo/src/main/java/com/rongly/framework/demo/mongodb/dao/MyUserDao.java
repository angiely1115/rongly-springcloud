package com.rongly.framework.demo.mongodb.dao;

import com.rongly.framework.demo.mongodb.entity.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/7 16:17
 * @Version: 1.0
 * modified by:
 */
@Repository
public interface MyUserDao extends MongoRepository<MyUser, Serializable> {

    List<MyUser> findMyUsersByUserName(String userName);

    List<MyUser> findMyUserByUserAgeBetween(int start,int end);
}
