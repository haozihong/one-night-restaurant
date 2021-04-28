package com.zihong.onr.mapper;

import com.zihong.onr.pojo.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 21:02
 */
@Mapper
public interface FoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    Food selectByPrimaryKey(Integer id);

    List<Food> getAllFoods();
}
