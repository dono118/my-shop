package com.funtl.my.shop.dao.impl;

import com.funtl.my.shop.dao.UserDao;
import com.funtl.my.shop.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Unicorn
 * @create 2019-07-13 16:12
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        if ("admin@funtl.com".equals(email)) {
            if ("admin".equals(password)) {
                user = new User();
                user.setEmail("admin@funtl.com");
                user.setUsername("Unicorn");
            }
        }
        return user;
    }
}
