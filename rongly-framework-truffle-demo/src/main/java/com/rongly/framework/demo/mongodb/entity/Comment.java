package com.rongly.framework.demo.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @Author: lvrongzhuan
 * @Description: 评价
 * @Date: 2019/1/8 11:41
 * @Version: 1.0
 * modified by:
 */
@Data
@Document(collection = "rongly_comment")
public class Comment implements Serializable {
    //评价内容
    @Field
    private String content;
    @Field(value = "comment_time")
    private ZonedDateTime commentTime;
    @Field
    private String id;
    /**
     * 文章ID
     */
    @Field(value = "article_id")
    private String articleId;

}
