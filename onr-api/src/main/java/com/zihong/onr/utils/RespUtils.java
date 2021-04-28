package com.zihong.onr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Zihong Hao
 * @github https://github.com/haozihong
 * @date 2021-04-27 20:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RespUtils {
    private Integer status;
    private String msg;
    private Object obj;

    public static RespUtils build() {
        return new RespUtils();
    }

    public static RespUtils ok(String msg) {
        return new RespUtils(200, msg, null);
    }

    public static RespUtils ok(String msg, Object obj) {
        return new RespUtils(200, msg, obj);
    }

    public static RespUtils error(String msg) {
        return new RespUtils(500, msg, null);
    }

    public static RespUtils error(String msg, Object obj) {
        return new RespUtils(500, msg, obj);
    }
}
