package com.yzy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 * @date 2021/5/13 20:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO {
    private String userId;
    private String realName;
    private String idCard;
    private String phone;
    private String county;
    private String city;
    private String province;
    private String detailAddr;
    private Boolean isSymptom;
    private Boolean foreign;
    private Integer risk;
    private Boolean contactPatient;
    private Float bodyHeat;
    private String healthyCode;
    private String createTime;
}
