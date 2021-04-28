package com.zihong.onr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 20:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFoods {
    private Integer id;

    private Integer orderId;

    private Integer foodId;

    private Food food;

    private Integer number;

    private Double totalPrice;
}
