package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/11 16:37
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class City {
    @TableId
    private Integer code;
    private Integer provinceCode;
    private String name;
}
