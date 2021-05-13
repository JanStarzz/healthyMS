package com.yzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzy.entity.MapData;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author LuBaby
 * @date 2021/5/11 22:09
 */
@Mapper
public interface MapDataMapper extends BaseMapper<MapData> {

    /**
     * 使用城市名查询MapDataId
     * @param name
     * @return
     */
    Integer getOneByName(String name);

}
