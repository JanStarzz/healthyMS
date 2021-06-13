package com.yzy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * @author LuBaby
 * @date 2021/6/7 23:02
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAreaVO {
    private String province;
    private String userId;
    private String city;
    private String area;
    private String timestampO;
}
