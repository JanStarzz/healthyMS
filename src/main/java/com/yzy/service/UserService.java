package com.yzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzy.entity.Result;
import com.yzy.entity.User;
import com.yzy.entity.vo.UserInfoVO;

/**
 * @author LuBaby
 * @date 2021/5/13 15:49
 */
public interface UserService extends IService<User> {

    /**
     * 保存用户当前信息
     * @param userInfoVO
     * @return
     */
    Result saveUserCurrent(UserInfoVO userInfoVO);

    /**
     * 获取健康码
     * @param userId
     * @return
     */
    Result gerQRCode(String userId) throws Exception;

    /**
     * 获取用户目前的状态
     * @param userId
     * @return
     */
    Result getUserCurrent(String userId);
}
