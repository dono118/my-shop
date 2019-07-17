package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author Unicorn
 * @create 2019-07-13 15:47
 */
public interface TbUserService {

    /**
     * 查询所有用户信息
     *
     * @return 用户列表
     */
    List<TbUser> selectAll();

    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    TbUser getUserById(Long id);

    /**
     * 登录
     *
     * @param email 用户
     * @param password 密码
     * @return 用户
     */
    TbUser login(String email, String password);

    /**
     * 插入一条用户信息
     *
     * @param tbUser 用户
     */
    void insert(TbUser tbUser);

    /**
     * 根据用户ID删除用户信息
     *
     * @param id 用户ID
     */
    void delete(Long id);

    /**
     * 删除多个用户
     *
     * @param ids 用户ID数组
     */
    void deleteMulti(Long[] ids);

    /**
     * 更新用户信息
     *
     * @param tbUser 用户
     */
    void update(TbUser tbUser);

    /**
     * 根据用户名模糊查询
     *
     * @param username 用户名
     * @return 用户列表
     */
    List<TbUser> selectByUsername(String username);
}
