package com.yzy.enumentity;

/**
 * @author LuBaby
 * @date 2021/5/14 22:29
 */
public enum HealthyCodeColor {
    RED(2),
    ORANGE(1),
    GREEN(0)
    ;


    private Integer code;

    private Integer rgbColor;

    HealthyCodeColor(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
