package com.funtl.my.shop.web.admin.dao;


import com.funtl.my.shop.domain.TbUser;

/**
 * @author Unicorn
 * @create 2019-07-13 15:57
 */
public interface TbUserDao {

    /**
     * 插入一条用户信息
     *
     * @param tbUser
     */
    void insert(TbUser tbUser);

    /**
     * 根据邮箱和密码获取用户信息
     *
     * @param email 邮箱
     * @return 用户
     */
    TbUser getUserByEmail(String email);
}
