package com.yzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzy.entity.Province;
import com.yzy.entity.vo.ProvinceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LuBaby
 * @date 2021/5/11 22:48
 */
@Mapper
public interface ProvinceMapper extends BaseMapper<Province> {
    /**
     * 用sql映射直接获取VO对象
     * @param status
     * @return
     */
    List<ProvinceVO> getVOByStatus(Integer status);
}
