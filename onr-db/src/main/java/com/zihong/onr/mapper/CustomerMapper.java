package com.zihong.onr.mapper;

import com.zihong.onr.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 21:56
 */
@Mapper
public interface CustomerMapper {
    List<Customer> getAllCustomers();

    Customer selectByPrimaryKey(Integer id);

    Customer selectByPhone(String phone);

    List<Customer> selectByNameFuzzy(String keyword);

    int insert(Customer record);

    int deleteByPrimaryKey(Integer id);
}
