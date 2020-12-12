package com.zys.miniprogramservive.dao;

import com.zys.miniprogramservive.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zhongyushi
 * @date 2020/12/11 0011
 * @dec 描述
 */
@Mapper
public interface UserDao {
    List<Map> checkBind(@Param("openid") String openid);

    User loginForBind(User user);

    void addBind(User user);
}
