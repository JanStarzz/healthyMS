package com.yzy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作为响应体
 * @author LuBaby
 * @date 2021/5/11 16:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
