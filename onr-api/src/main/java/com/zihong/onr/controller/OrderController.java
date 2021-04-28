package com.zihong.onr.controller;

import com.zihong.onr.pojo.Order;
import com.zihong.onr.pojo.OrderFoods;
import com.zihong.onr.service.FoodService;
import com.zihong.onr.service.OrderService;
import com.zihong.onr.utils.RespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 23:40
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/orders")
    public List<Order> getOrders(Integer id, Integer customerId, String phone, Integer status) {
        System.out.println("[MY DEBUG] id=>" + id +
                "; customerId=>" + customerId +
                "; phone=>" + phone +
                "; status=>" + status);
        if (id != null) {
            return Collections.singletonList(orderService.selectByPrimaryKey(id));
        } else if (customerId != null) {
            return orderService.selectByCustomerId(customerId);
        } else  if (phone != null) {
            if (status != null && status.equals(0)) {
                System.out.println("[MY DEBUG] select pending");
                return orderService.selectPendingByPhone(phone);
            } else {
                return orderService.selectByPhone(phone);
            }
        } else {
            return orderService.getAllOrders();
        }
    }

    @GetMapping("/orders/{id}")
    public Order selectByPrimaryKey(@PathVariable("id") Integer id) {
        return orderService.selectByPrimaryKey(id);
    }

    @PostMapping("/orders")
    public RespUtils insert(@RequestBody Order order) {
        System.out.println("[MY DEBUG] new order: \n" + order);
        // TODO: 2021/04/28 validate customerId and all foodIds
        
        // calculate order's total price
        double orderPrice = 0;
        for (OrderFoods orderFoods : order.getFoodsList()) {
            double foodPrice = foodService.selectByPrimaryKey(orderFoods.getFoodId()).getPrice() * orderFoods.getNumber();
            orderPrice += foodPrice;
            orderFoods.setTotalPrice(foodPrice);
        }
        order.setOrderPrice(orderPrice);
        // status cannot be null
        if (order.getStatus() == null) order.setStatus(0);
        // set createTime to now
        if (order.getCreateTime() == null) order.setCreateTime(new Date());
        if (orderService.insert(order) == 1) {
            return RespUtils.ok("[SUCCESS] insert order success");
        }
        return RespUtils.error("[FAIL] insert order fail");
    }

    @PutMapping("/orders/pickup/{id}")
    public RespUtils pickUpOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.selectByPrimaryKey(id);
        if (order.getStatus().equals(1)) {
            return RespUtils.error("[FAIL] order#" + id + " was picked up on " + order.getPickedUpTime());
        }
        if (orderService.pickUpOrderById(id, new Date()) == 1) {
            return RespUtils.ok("[SUCCESS] pick up order#" + id + " success");
        }
        return RespUtils.error("[FAIL] pick up order#" + id + " fail");
    }

    @DeleteMapping("/orders/{id}")
    public RespUtils deleteByPrimaryKey(@PathVariable("id") Integer id) {
        if (orderService.deleteByPrimaryKey(id) == 1) {
            return RespUtils.ok("[SUCCESS] delete order#" + id + " success");
        }
        return RespUtils.error("[FAIL] delete order#" + id + " fail");
    }
}
