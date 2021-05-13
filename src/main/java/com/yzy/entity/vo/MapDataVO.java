package com.yzy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/11 20:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MapDataVO {

    private String name;

    private Integer confirm;

    private Integer nowConfirm;

    private Integer dead;
}
