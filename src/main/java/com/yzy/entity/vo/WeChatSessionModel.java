package com.yzy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/3/4 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatSessionModel {
    private String openid;
    private String session_key;
    private String errcode;
    private String errmsg;
}
