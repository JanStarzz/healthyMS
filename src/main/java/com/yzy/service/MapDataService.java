package com.yzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzy.entity.MapData;
import com.yzy.entity.vo.MapDataVO;

import java.util.List;

/**
 * @author LuBaby
 * @date 2021/5/11 22:10
 */

public interface MapDataService extends IService<MapData> {


    /**
     * 根据视图对象返回MapData对象
     * @param mapDataVOS
     * @return
     */
    List<MapData> findByMapDataVO(List<MapDataVO> mapDataVOS);
}
