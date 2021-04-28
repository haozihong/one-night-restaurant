package com.zihong.onr.controller;

import com.zihong.onr.pojo.Food;
import com.zihong.onr.service.FoodService;
import com.zihong.onr.utils.RespUtils;
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
    public RespUtils insert(Food food) {
        if (foodService.insert(food) == 1) {
            return RespUtils.ok("[SUCCESS] insert food[" + food.getName() + "] success");
        }
        return RespUtils.error("[FAIL] insert food[" + food.getName() + "] fail");
    }

    @DeleteMapping("/foods/{id}")
    public RespUtils deleteByPrimaryKey(@PathVariable("id") Integer id) {
        if (foodService.deleteByPrimaryKey(id) == 1) {
            return RespUtils.ok("[SUCCESS] delete food#" + id + " success");
        }
        return RespUtils.error("[FAIL] delete food#" + id + " fail");
    }
}
