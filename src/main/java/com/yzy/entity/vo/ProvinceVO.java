package com.yzy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LuBaby
 * @date 2021/5/12 12:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceVO {
    private Integer provinceCode;
    private String provinceName;
    private List<CityVO> cities;
    private Integer count;
    private Integer low;
    private Integer mid;
    private Integer high;

    public void setValue() {
        this.count = cities.size();
        this.mid = cities.stream().collect(Collectors.summingInt(CityVO::getMid));
        this.high = cities.stream().collect(Collectors.summingInt(CityVO::getHigh));
        this.low = cities.stream().collect(Collectors.summingInt(CityVO::getLow));
    }
}
