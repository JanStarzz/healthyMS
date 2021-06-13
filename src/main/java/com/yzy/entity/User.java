package com.yzy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/13 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @TableId
    private String userId;
    private String realName;
    private String nickName;
    private String phone;
    private String idNumber;
}
