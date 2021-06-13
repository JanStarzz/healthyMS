package com.yzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzy.entity.Area;
import com.yzy.entity.City;
import com.yzy.entity.Result;
import com.yzy.entity.UserArea;
import com.yzy.entity.vo.CityVO;
import com.yzy.entity.vo.ProvinceVO;
import com.yzy.entity.vo.UserAreaVO;
import com.yzy.enumentity.RiskEnum;
import com.yzy.mapper.AreaMapper;
import com.yzy.mapper.CityMapper;
import com.yzy.mapper.ProvinceMapper;
import com.yzy.mapper.UserAreaMapper;
import com.yzy.service.AreaService;
import com.yzy.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author LuBaby
 * @date 2021/5/12 13:07
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
    @Autowired
    AreaMapper areaMapper;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    ProvinceMapper provinceMapper;

    @Autowired
    UserAreaMapper userAreaMapper;

    @Override
    public Result getRiskAreaByStatus(Integer status) {
        if (status == null) {
            return ResultUtil.paramError();
        }
        List<ProvinceVO> voByStatus;
        try {
            voByStatus = provinceMapper.getVOByStatus(status);
        } catch (Exception e) {
            return ResultUtil.dataBaseError();
        }
        for (ProvinceVO p : voByStatus) {
            for (CityVO cityVO : p.getCities()) {
                cityVO.setValue();
            }
            p.setValue();

        }
        voByStatus.stream().sorted(Comparator.comparing(ProvinceVO::getProvinceCode));
        return ResultUtil.success(voByStatus);
    }

    @Override
    public Result setRiskArea(Map map) {
        Integer status = (Integer) map.get("status");
        ArrayList<String> arrayList = (ArrayList) map.get("code");
        String code;
        UpdateWrapper<Area> wrapper = new UpdateWrapper<>();
        wrapper.set("status", status);


        if (arrayList.size() == 3) {
            String s = arrayList.get(2);
            if (s.equals("")) {
                code = arrayList.get(1).substring(0, 4);
                wrapper.eq("city_code", code);
            } else {
                code = arrayList.get(2);
                wrapper.eq("code", code);
            }
        } else if (arrayList.size() == 2) {
            String s = arrayList.get(1);
            if (s.equals("")) {
                code = arrayList.get(0).substring(0, 2);
                wrapper.eq("province_code", code);
            } else {
                code = arrayList.get(1).substring(0, 4);
                wrapper.eq("city_code", code);
            }
        } else {
            String s = arrayList.get(0);
            if (!s.equals("")) {
                code = arrayList.get(0).substring(0, 2);
                wrapper.eq("province_code", code);
            }
        }
        update(wrapper);
        return ResultUtil.success();
    }

    @Override
    public Integer areaUtil(String city, String area) {
        QueryWrapper<Area> areaQueryWrapper = new QueryWrapper<>();
        areaQueryWrapper.like("name", area);
        List<Area> areas = areaMapper.selectList(areaQueryWrapper);
        if (areas.size() > 1) {
            QueryWrapper<City> cityQueryWrapper = new QueryWrapper<>();
            cityQueryWrapper.like("name", city);
            City city1 = cityMapper.selectOne(cityQueryWrapper);
            for (Area a : areas) {
                if (a.getCityCode() == city1.getCode()) {
                    return a.getCode();
                }
            }
        }
        return areas.get(0).getCode();
    }

    @Override
    public Area getRiskStatusByAreaCode(Integer areaCode) {
        return getById(areaCode);
    }

    @Override
    public Result saveUserArea(UserAreaVO userAreaVO) {
        UserArea build = UserArea.builder().userId(userAreaVO.getUserId())
                .areaCode(areaUtil(userAreaVO.getCity(), userAreaVO.getArea()))
                .time(new Timestamp(System.currentTimeMillis())).build();

        userAreaMapper.insert(build);


        return ResultUtil.success();
    }

    @Override
    public Result getUserArea(String userId) {

        return ResultUtil.success(userAreaMapper.getUserAreaVO(userId));
    }
}
