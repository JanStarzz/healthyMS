package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/11 16:38
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Area {
    @TableId
    private int code;
    private int provinceCode;
    private int cityCode;
    private String name;
    private int status;
}
