package com.rongly.framework.demo.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.ZonedDateTime;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/30 11:00
 * @Version: 1.0
 * modified by:
 */
@Data
@TableName(value = "cart")
public class CartEntity implements Serializable {
    @TableId
    private Long id;
    @TableField
    private Long productId;
    @TableField
    private Integer qty;
    @TableField
    private Long buyUserId;
    @TableField
    private Integer sourceFrom;
    @TableField(update = "now()")
    /**
     * 如果数据库是datetime保存大库里相差8小时 查询出来是对的
     * 如果使用数据库now()函数 保存到数据库是正常的 但是查询出来错误
     */
    private ZonedDateTime createTime = ZonedDateTime.now();
}
