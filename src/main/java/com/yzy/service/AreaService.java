package com.yzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzy.entity.Area;
import com.yzy.entity.Result;

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
}
