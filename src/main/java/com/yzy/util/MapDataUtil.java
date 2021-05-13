package com.yzy.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzy.entity.MapData;
import com.yzy.entity.vo.MapDataVO;
import com.yzy.service.MapDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LuBaby
 * @date 2021/5/11 20:25
 */
@Slf4j
@Component
public class MapDataUtil {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MapDataService mapDataService;

    private static String URL = "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5";


    public  List<MapDataVO> getMapData(){
        ArrayList<MapDataVO> list = new ArrayList<>();
        HashMap forObject = restTemplate.getForObject(URL, HashMap.class);
        Map data = JSONObject.parseObject((String) forObject.get("data"), Map.class);
        JSONArray areaTree = (JSONArray) data.get("areaTree");
        Map map = JSONObject.parseObject(areaTree.get(0).toString(),Map.class) ;
        JSONArray children = (JSONArray) map.get("children");
        for (Object j:children) {
            Map result = JSONObject.parseObject(j.toString(), Map.class);
            Map total = JSONObject.parseObject(result.get("total").toString(), Map.class);
            MapDataVO build = MapDataVO.builder()
                    .name((String) result.get("name"))
                    .confirm((Integer) total.get("confirm"))
                    .nowConfirm((Integer) total.get("nowConfirm"))
                    .dead((Integer) total.get("dead"))
                    .build();
            list.add(build);
        }
        return list;
    }
    @Scheduled(fixedRate = 1000*60*10)
    public void refreshMapData(){
        List<MapDataVO> mapData = this.getMapData();
        List<MapData> byMapDataVO = mapDataService.findByMapDataVO(mapData);
        log.info("更新疫情地图数据："+byMapDataVO);
        mapDataService.saveOrUpdateBatch(byMapDataVO);
    }

}
