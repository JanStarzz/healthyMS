package com.yzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzy.entity.Province;

/**
 * @author LuBaby
 * @date 2021/5/11 22:54
 */
public interface ProvinceService extends IService<Province> {
    /**
     * 用名称获取代码
     * @param name
     * @return
     */
    Integer getCodeByName(String name);
}
