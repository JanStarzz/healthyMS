package com.yzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzy.entity.MapData;
import com.yzy.entity.vo.MapDataVO;
import com.yzy.mapper.MapDataMapper;
import com.yzy.service.MapDataService;
import com.yzy.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuBaby
 * @date 2021/5/11 22:10
 */

@Service
public class MapDataServiceImpl extends ServiceImpl<MapDataMapper, MapData> implements MapDataService {

    @Autowired
    MapDataMapper mapper;

    @Autowired
    ProvinceService provinceService;



    @Cacheable(cacheNames = "provinces")
    @Override
    public List<MapData> findByMapDataVO(List<MapDataVO> mapDataVOS) {
        List<MapData> mapDataList = new ArrayList<>();

        for(MapDataVO m:mapDataVOS){
            MapData build = MapData.builder()
                    .confirm(m.getConfirm())
                    .dead(m.getDead())
                    .nowConfirm(m.getNowConfirm())
                    .code(provinceService.getCodeByName(m.getName())).build();
            mapDataList.add(build);
        }
        return mapDataList;
    }
}
