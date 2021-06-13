package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/11 16:35
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName("provinces")
public class Province {
    @TableId
    private Integer code;
    private String name;
}
