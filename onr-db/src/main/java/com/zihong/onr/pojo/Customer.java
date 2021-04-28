package com.zihong.onr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 20:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;

    private String name;

    private String phone;
}
