package com.yzy.controller;

import com.yzy.entity.Result;
import com.yzy.entity.vo.UserInfoVO;
import com.yzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LuBaby
 * @date 2021/5/13 20:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/userInfo")
    public Result setInfo(@RequestBody UserInfoVO userInfoVO){

        return userService.saveUserCurrent(userInfoVO);
    }

    @GetMapping("/refresh/{userId}")
    public Result getQrcode(@PathVariable String userId) throws Exception {
        return userService.gerQRCode(userId);
    }

    @GetMapping("/userCurrent/{userId}")
    public Result getUserCurrent(@PathVariable String userId){
        return userService.getUserCurrent(userId);
    }
}
