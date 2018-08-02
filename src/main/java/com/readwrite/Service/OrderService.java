package com.readwrite.Service;


import com.readwrite.entity.Order;
import com.readwrite.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    public String orderPay(Integer count, BigDecimal amount) throws Exception{
        final Order order = buildOrder(count, amount);
        final int rows = orderMapper.save(order);
        //异常回滚测试
        if (1==1){
            throw  new RuntimeException("测试抛异常");
        }
        return "success";
    }
    private Order buildOrder(Integer count, BigDecimal amount) {
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setNumber("123");
        //demo中的表里只有商品id为 1的数据
        order.setProductId("1");
        order.setStatus(1);
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中 表里面存的用户id为10000
        order.setUserId("10000");
        return order;
    }
}
