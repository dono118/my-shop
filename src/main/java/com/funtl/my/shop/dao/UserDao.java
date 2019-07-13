package com.funtl.my.shop.dao;

import com.funtl.my.shop.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Unicorn
 * @create 2019-07-13 15:57
 */
public interface UserDao {
    /**
     * 根据邮箱和密码获取用户信息
     *
     * @param email 邮箱
     * @param password 密码
     * @return 用户
     */
    User getUserByEmailAndPassword(String email, String password);
}
