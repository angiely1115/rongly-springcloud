package com.rongly.framework.demo.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/1/7 16:08
 * @Version: 1.0
 * modified by:
 */
@Data
@Document(collection="rongly_user")
public class MyUser implements Serializable {
    @Field(value = "user_name")
    private String userName;
    @Field(value = "user_age")
    private int userAge;
    @Field(value = "user_address")
    private String userAddress;
    @Field(value = "sex")
    private String sex;
    /*@Field(value = "_id")
    private String objectId;*/
    //id 插入和查询都可以返回
    private String id;
}
