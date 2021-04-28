package com.zihong.onr.controller;

import com.zihong.onr.pojo.Order;
import com.zihong.onr.service.OrderService;
import com.zihong.onr.utils.RespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public RespUtils insert(Order order) {
        // TODO: 2021/04/28 insert an order
        return RespUtils.error("[FAIL] insert order fail");
    }

    @PutMapping("/orders/pickup/{id}")
    public RespUtils pickUpOrderById(@PathVariable("id") Integer id) {
        // TODO: 2021/04/28 pick up an order by id
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
