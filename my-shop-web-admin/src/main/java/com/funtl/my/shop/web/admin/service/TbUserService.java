package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbUser;

/**
 * @author Unicorn
 * @create 2019-07-13 15:47
 */
public interface TbUserService {

    void insert(TbUser tbUser);

    /**
     * 登录
     *
     * @param email 用户
     * @param password 密码
     * @return 用户
     */
    TbUser login(String email, String password);
}
