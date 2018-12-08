package com.rongly.framework.demo.dao.read;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rongly.framework.demo.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/8 17:28
 * @Version: 1.0
 * modified by:
 */
@Repository
@Mapper
public interface OrderReadDao extends BaseMapper<OrderEntity> {
}
