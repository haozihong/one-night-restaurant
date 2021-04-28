package com.zihong.onr.controller;

import com.zihong.onr.pojo.Customer;
import com.zihong.onr.service.CustomerService;
import com.zihong.onr.utils.RespUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 22:08
 */
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(String phone, String keyword) {
        System.out.println("[MY DEBUG] phone=>" + phone);
        if (phone != null) {
            return Collections.singletonList(customerService.selectByPhone(phone));
        } if (keyword != null) {
            return keyword.length() > 1 ? customerService.selectByNameFuzzy(keyword) : Collections.emptyList();
        } else {
            return customerService.getAllCustomers();
        }
    }

    @GetMapping("/customers/{id}")
    public Customer selectByPrimaryKey(@PathVariable("id") Integer id) {
        return customerService.selectByPrimaryKey(id);
    }

    @PostMapping("/customers")
    public RespUtils insert(@RequestBody Customer customer) {
        if (customerService.insert(customer) == 1) {
            return RespUtils.ok("[SUCCESS] insert customer[" + customer.getName() + "] success", customer);
        }
        return RespUtils.error("[FAIL] insert customer[" + customer.getName() + "] fail");
    }

    @DeleteMapping("/customers/{id}")
    public RespUtils deleteByPrimaryKey(@PathVariable("id") Integer id) {
        if (customerService.deleteByPrimaryKey(id) == 1) {
            return RespUtils.ok("[SUCCESS] delete customer#" + id + " success");
        }
        return RespUtils.error("[FAIL] delete customer#" + id + " fail");
    }
}
