package com.funtl.my.shop.web.admin.dao;


import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author Unicorn
 * @create 2019-07-13 15:57
 */
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 根据邮箱和密码获取用户信息
     *
     * @param email 邮箱
     * @return 用户
     */
    TbUser getUserByEmail(String email);

    /**
     * 根据用户名模糊查询
     *
     * @param username 用户名
     * @return 用户列表
     */
    List<TbUser> selectByUsername(String username);
}
