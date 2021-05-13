package com.yzy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/12 12:58
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AreaVO {
    private Integer areaCode;
    private String areaName;
    private Integer status;
}
