package com.zihong.onr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 20:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private Integer id;

    private String name;

    private Double price;
}
