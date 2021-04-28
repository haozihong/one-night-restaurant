package com.zihong.onr.controller;

import com.zihong.onr.pojo.Food;
import com.zihong.onr.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 21:17
 */
@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/foods")
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/foods/{id}")
    public Food selectByPrimaryKey(@PathVariable("id") Integer id) {
        return foodService.selectByPrimaryKey(id);
    }

    @PostMapping("/foods")
    public int insert(Food record) {
        return foodService.insert(record);
    }

    @DeleteMapping("/foods/{id}")
    public int deleteByPrimaryKey(@PathVariable("id") Integer id) {
        return foodService.deleteByPrimaryKey(id);
    }
}
