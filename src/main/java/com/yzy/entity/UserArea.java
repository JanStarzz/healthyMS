package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 用户15天内经过的地方
 * @author LuBaby
 * @date 2021/5/14 21:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserArea {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userId;
    private Integer areaCode;
    private Timestamp time;
}
