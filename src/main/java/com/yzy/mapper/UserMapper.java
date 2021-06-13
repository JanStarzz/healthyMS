package com.yzy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LuBaby
 * @date 2021/5/13 15:49
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
