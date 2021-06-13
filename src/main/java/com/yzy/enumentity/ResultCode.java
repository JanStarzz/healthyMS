package com.yzy.enumentity;

import lombok.AllArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/11 16:57
 */
@AllArgsConstructor
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 参数不合法
     */
    PARAM_ERROR(201, "参数不合法"),
    /**
     * 用户名或者密码错误
     */

    LOGIN_ERROR(1000, "用户名或密码错误"),

    First_LOGIN(303,"初次登陆请填写完整信息"),

    UNKNOWN_ERROR(-1, "未知错误"),

    /**
     * 数据库异常
     *
     */
    DATABASE_ERROR(202, "数据库异常");
    ;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
