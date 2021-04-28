package com.zihong.onr.mapper;

import com.zihong.onr.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 22:46
 */
@Mapper
public interface OrderMapper {
    List<Order> getAllOrders();

    Order selectByPrimaryKey(Integer id);

    List<Order> selectByPhone(String phone);

    List<Order> selectByCustomerId(Integer id);

    List<Order> selectPendingByPhone(String phone);

    int insert(Order record);

    int updateByPrimaryKeySelective(Order record);

    int deleteByPrimaryKey(Integer id);
}
