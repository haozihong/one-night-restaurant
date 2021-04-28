package com.zihong.onr.mapper;

import com.zihong.onr.pojo.OrderFoods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 23:34
 */
@Mapper
public interface OrderFoodsMapper {
    int insert(OrderFoods record);

    int deleteByPrimaryKey(Integer id);
}
