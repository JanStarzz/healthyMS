package com.yzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzy.entity.UserArea;
import com.yzy.entity.vo.UserAreaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LuBaby
 * @date 2021/5/14 22:08
 */
@Mapper
public interface UserAreaMapper extends BaseMapper<UserArea> {

    /**
     * 获取经过地
     * @param userId
     * @return
     */
    List<UserAreaVO> getUserAreaVO(String userId);
}
