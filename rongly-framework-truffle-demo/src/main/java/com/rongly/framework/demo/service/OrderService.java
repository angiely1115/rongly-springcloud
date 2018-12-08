package com.rongly.framework.demo.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rongly.framework.demo.dao.read.OrderReadDao;
import com.rongly.framework.demo.entity.OrderEntity;
import org.springframework.stereotype.Service;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/8 17:28
 * @Version: 1.0
 * modified by:
 */
@Service
public class OrderService extends ServiceImpl<OrderReadDao, OrderEntity> {

}
