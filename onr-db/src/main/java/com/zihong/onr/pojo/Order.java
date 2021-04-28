package com.zihong.onr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 20:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;

    private Integer customerId;

    private Customer customer;

    private Integer status;

    private Double orderPrice;

    private Date createTime;

    private Date pickedUpTime;

    private List<OrderFoods> foodsList;
}
