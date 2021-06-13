package com.yzy.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzy.entity.Result;
import com.yzy.entity.User;
import com.yzy.entity.vo.WeChatSessionModel;
import com.yzy.service.UserService;
import com.yzy.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author LuBaby
 * @date 2021/5/13 15:53
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    private static final String APPID = "wxfd28bb7875ca0a08";

    private static final String APPSECRET = "057d7fc8ed390aae13b454263376bfc1";

    @GetMapping("/wxlogin/{code}")
    public Result wxLogin(@PathVariable String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+ "&secret="+APPSECRET+"&js_code="+ code +"&grant_type=authorization_code";
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String body = exchange.getBody();
        WeChatSessionModel weChatSessionModel = JSONObject.parseObject(body,WeChatSessionModel.class);
        User user = User.builder().userId(weChatSessionModel.getOpenid()).build();
        User byId = userService.getById(weChatSessionModel.getOpenid());
        if (byId==null){
            userService.save(user);
        }else if (byId.getRealName()==null){
            return ResultUtil.firstLogin(byId.getUserId());
        }
        return ResultUtil.success(user);
    }


}
