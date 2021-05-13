package com.yzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzy.entity.Province;
import com.yzy.mapper.ProvinceMapper;
import com.yzy.service.ProvinceService;
import org.springframework.stereotype.Service;

/**
 * @author LuBaby
 * @date 2021/5/11 22:55
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {
    @Override
    public Integer getCodeByName(String name) {
        QueryWrapper<Province> provinceQueryWrapper = new QueryWrapper<>();
        provinceQueryWrapper.like("name",name);
        Province one = getOne(provinceQueryWrapper);
        return one.getCode();
    }
}
