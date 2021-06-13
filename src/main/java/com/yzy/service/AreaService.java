package com.yzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzy.entity.Area;
import com.yzy.entity.Result;
import com.yzy.entity.vo.UserAreaVO;

import java.sql.ResultSet;
import java.util.Map;

/**
 * @author LuBaby
 * @date 2021/5/12 13:04
 */
public interface AreaService extends IService<Area> {
    /**
     * 根据风险 情况查询数据
     * @param status
     * @return
     */
    Result getRiskAreaByStatus(Integer status);

    /**
     * 设置风险地区
     * @param map
     * @return
     */
    Result setRiskArea(Map map);

    /**
     * 根据地区名称查询code
     * @param city
     * @param area
     * @return
     */
    Integer areaUtil(String city,String area);

    /**
     * 获取地区的风险状况
     * @param areaCode
     * @return
     */
    Area getRiskStatusByAreaCode(Integer areaCode);

    /**
     * 保存定位信息
     * @param userAreaVO
     * @return
     */
    Result saveUserArea(UserAreaVO userAreaVO);

    /**
     * 根据userId查询经过的地方
     * @param userId
     * @return
     */
    Result getUserArea(String userId);
}
