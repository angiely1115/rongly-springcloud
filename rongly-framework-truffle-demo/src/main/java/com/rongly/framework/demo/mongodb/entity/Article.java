package com.rongly.framework.demo.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @Author: lvrongzhuan
 * @Description: 文章
 * @Date: 2019/1/8 11:47
 * @Version: 1.0
 * modified by:
 */
@Document(collection = "rongly_article")
@Data
public class Article implements Serializable {
    //文章标题
    @Field
    private String title;
    //文章内容
    @Field
    private String content;
    //创建时间
    @Field(value = "create_time")
    private ZonedDateTime createTime;
   //主键ID
   @Field
    private String id;
}
