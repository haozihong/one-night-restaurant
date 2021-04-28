package com.zihong.onr.service;

import com.zihong.onr.mapper.CustomerMapper;
import com.zihong.onr.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 22:03
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerMapper.getAllCustomers();
    }

    public Customer selectByPrimaryKey(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    public Customer selectByPhone(String phone) {
        return customerMapper.selectByPhone(phone);
    }

    public List<Customer> selectByNameFuzzy(String keyword) {
        return customerMapper.selectByNameFuzzy(keyword);
    }

    public int insert(Customer record) {
        return customerMapper.insert(record);
    }

    public int deleteByPrimaryKey(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

}
