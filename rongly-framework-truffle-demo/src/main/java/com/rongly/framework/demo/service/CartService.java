package com.rongly.framework.demo.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rongly.framework.demo.dao.write.CartWriteDao;
import com.rongly.framework.demo.entity.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/30 11:09
 * @Version: 1.0
 * modified by:
 */
@Service
public class CartService extends ServiceImpl<CartWriteDao, CartEntity> {
    @Autowired
    private CartWriteDao cartWriteDao;

    @Transactional(rollbackFor = Exception.class)
    public CartEntity addCart(CartEntity cartEntity){
        cartWriteDao.addCart(cartEntity);
//        super.insert(cartEntity);
        this.addCart2(cartEntity);
//        int i = 5/0;
        return cartEntity;
    }

    public CartEntity addCart2(CartEntity cartEntity){
        cartWriteDao.insert(cartEntity);
        return cartEntity;
    }

    public Integer updateCartByProductId(CartEntity cartEntity){
        return cartWriteDao.updateCartByProductId(cartEntity);
    }
}
