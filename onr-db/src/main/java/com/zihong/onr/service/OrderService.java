package com.zihong.onr.service;

import com.zihong.onr.mapper.OrderFoodsMapper;
import com.zihong.onr.mapper.OrderMapper;
import com.zihong.onr.pojo.Order;
import com.zihong.onr.pojo.OrderFoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 23:32
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderFoodsMapper orderFoodsMapper;

    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    public Order selectByPrimaryKey(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    public List<Order> selectByPhone(String phone) {
        return orderMapper.selectByPhone(phone);
    }

    public List<Order> selectByCustomerId(Integer id) {
        return orderMapper.selectByCustomerId(id);
    }

    public List<Order> selectPendingByPhone(String phone) {
        return orderMapper.selectPendingByPhone(phone);
    }

    public int insert(Order order) {
        if (orderMapper.insert(order) == 0) return 0;
        for (OrderFoods orderFoods : order.getFoodsList()) {
            if (orderFoodsMapper.insert(orderFoods) == 0) return 0;
        }
        return 1;
    }

    public int deleteByPrimaryKey(Integer id) {
        return orderMapper.deleteByPrimaryKey(id);
    }
}
