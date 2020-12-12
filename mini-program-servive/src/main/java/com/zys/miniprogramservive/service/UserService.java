package com.zys.miniprogramservive.service;

import com.zys.miniprogramservive.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author zhongyushi
 * @date 2020/12/11 0011
 * @dec 描述
 */
public interface UserService {

    List<Map> checkBind(String openid);

    User loginForBind(User user);

    void addBind(User user);
}
