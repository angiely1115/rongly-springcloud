package com.rongly.framework.demo.dao.write;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rongly.framework.demo.entity.CartEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/30 10:58
 * @Version: 1.0
 * modified by:
 */
@Repository
@Mapper
public interface CartWriteDao extends BaseMapper<CartEntity> {

    int addCart(CartEntity cartEntity);

}
