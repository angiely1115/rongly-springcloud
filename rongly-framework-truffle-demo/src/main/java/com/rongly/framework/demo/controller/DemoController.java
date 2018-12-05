package com.rongly.framework.demo.controller;

import com.rongly.framework.demo.entity.CartEntity;
import com.rongly.framework.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/3 11:46
 * @Version: 1.0
 * modified by:
 */
@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private CartService cartService;
    /**
     * 测试事务
     * @return
     */
    @RequestMapping("transaction")
    public String testTransaction(){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(8294L);
        cartEntity.setProductId(888933L);
        cartService.updateById(cartEntity);
        cartEntity = cartService.selectById(8294);
        //没有使用到事务
        int  i = 1/0;
        System.out.println(cartEntity);
        return "shiwu";

    }

    @RequestMapping("transaction2")
    public String testTransaction2(){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(8294L);
        cartEntity.setProductId(888933L);
        cartService.updateById(cartEntity);
        cartEntity = cartService.selectById(8294);
        //没有使用到事务
        int  i = 1/0;
        System.out.println(cartEntity);
        return "shiwu";

    }
}
