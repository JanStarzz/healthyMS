package com.yzy.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LuBaby
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class UserCurrent {

  @TableId
  private String userId;
  private Integer areaCode;
  private String detailAddr;
  //是否有症状
  private Boolean symptom;
  private Boolean atForeign;
  //是否去过高风险地区
  private Integer risk;
  //是否和新冠病人接触过
  private Boolean contactPatient;
  private Float bodyHeat;
  private String healthyCode;
  private String createTime;

}
