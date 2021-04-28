package com.zihong.onr.service;

import com.zihong.onr.mapper.FoodMapper;
import com.zihong.onr.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 21:15
 */
@Service
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;

    public List<Food> getAllFoods() {
        return foodMapper.getAllFoods();
    }

    public Food selectByPrimaryKey(Integer id) {
        return foodMapper.selectByPrimaryKey(id);
    }

    public int insert(Food record) {
        return foodMapper.insert(record);
    }

    public int deleteByPrimaryKey(Integer id) {
        return foodMapper.deleteByPrimaryKey(id);
    }
}
