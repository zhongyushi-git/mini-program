package com.zys.miniprogramservive.service;

import com.zys.miniprogramservive.dao.UserDao;
import com.zys.miniprogramservive.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhongyushi
 * @date 2020/12/11 0011
 * @dec 描述
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<Map> checkBind(String openid) {
        return userDao.checkBind(openid);
    }

    @Override
    public User loginForBind(User user) {
        return userDao.loginForBind(user);
    }

    @Override
    public void addBind(User user) {
        userDao.addBind(user);
    }
}
