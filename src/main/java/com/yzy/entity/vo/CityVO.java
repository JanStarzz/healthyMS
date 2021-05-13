package com.yzy.entity.vo;

import com.yzy.enumentity.RiskEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LuBaby
 * @date 2021/5/12 12:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityVO {
    private Integer cityCode;
    private String cityName;
    private List<AreaVO> areas;
    private Integer count;
    private Integer low;
    private Integer mid;
    private Integer high;

    public void setValue() {
        this.count = areas.size();
        this.high = Math.toIntExact(areas.stream().filter(a -> a.getStatus()==2).count());
        this.low = Math.toIntExact(areas.stream().filter(a -> a.getStatus()==0).count());
        this.mid = Math.toIntExact(areas.stream().filter(a -> a.getStatus()==1).count());
    }
}
