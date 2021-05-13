package com.yzy.enumentity;

/**
 * @author LuBaby
 * @date 2021/5/12 13:17
 */
public enum RiskEnum {
    LOW("低风险",0),
    MID("中风险",1),
    HIGH("高风险",2),
    ALL("全部地区",3)
    ;

    private String name;
    private Integer status;

    RiskEnum(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
