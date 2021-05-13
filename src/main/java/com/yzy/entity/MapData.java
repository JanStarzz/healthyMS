package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/11 22:21
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MapData {
    @TableId
    private Integer code;

    private Integer confirm;

    private Integer nowConfirm;

    private Integer dead;
}
