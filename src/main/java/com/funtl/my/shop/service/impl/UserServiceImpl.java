package com.funtl.my.shop.service.impl;

import com.funtl.my.shop.dao.UserDao;
import com.funtl.my.shop.entity.User;
import com.funtl.my.shop.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Unicorn
 * @create 2019-07-13 16:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }
}
