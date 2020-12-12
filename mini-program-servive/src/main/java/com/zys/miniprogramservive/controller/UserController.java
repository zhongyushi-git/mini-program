package com.zys.miniprogramservive.controller;


import com.alibaba.fastjson.JSONObject;
import com.zys.miniprogramservive.entity.User;
import com.zys.miniprogramservive.service.UserService;
import com.zys.miniprogramservive.util.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@PropertySource("classpath:applet.properties")
public class UserController {

    @Value("${applet.getOpenIdURL}")
    private String getOpenIdURL;

    @Value("${applet.appId}")
    private String appId;

    @Value("${applet.appSecret}")
    private String appSecret;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private UserService userService;

    /**
     * 验证小程序是否绑定平台用户
     *
     * @param code
     * @return
     */
    @GetMapping("/checkBind")
    public JSONObject checkBind(String code) {
        String url = getOpenIdURL.replace("APPID", appId).replace("SECRET", appSecret).replace("JSCODE", code);
        JSONObject bindInfo = httpClient.get(url);
        JSONObject result = new JSONObject();
        String openid = bindInfo.getString("openid");
        if (!StringUtils.isEmpty(openid)) {
            List<Map> bindUser = userService.checkBind(openid);
            if (bindUser != null && bindUser.size() > 0) {
                result.put("code", 1);
                result.put("msg", "此用户已绑定，无需登录验证");
            } else {
                result.put("code", 0);
                result.put("msg", "此用户未已绑定，需登录验证");
            }
            result.put("openId", openid);
        } else {
            result.put("code", -1);
            result.put("msg", "服务器繁忙，请稍后再试");
        }
        return result;
    }

    /**
     * 先登录，然后绑定信息
     *
     * @param user
     * @return
     */
    @PostMapping("/loginForBind")
    public JSONObject loginForBind(@RequestBody User user) {
        User loginUser = userService.loginForBind(user);
        JSONObject result = new JSONObject();
        if (loginUser == null) {
            result.put("code", 0);
            result.put("msg", "用户名或密码错误");
        } else {
            //添加绑定记录，下次免登录验证
            userService.addBind(user);
            result.put("code", 1);
            result.put("msg", "登录成功");
            user.setPassword(null);
            result.put("data", user);
        }
        return result;
    }
}
