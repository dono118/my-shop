package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @author Unicorn
 * @create 2019-07-13 16:20
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    private final TbUserDao tbUserDao;

    @Autowired
    public TbUserServiceImpl(TbUserDao tbUserDao) {
        this.tbUserDao = tbUserDao;
    }

    /**
     * 查询所有用户信息
     *
     * @return 用户列表
     */
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    /**
     * 根据用户ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public TbUser getUserById(Long id) {
        return tbUserDao.getUserById(id);
    }

    /**
     * 登录
     *
     * @param email 用户
     * @param password 密码
     * @return 用户
     */
    @Override
    public TbUser login(String email, String password) {

        TbUser tbUser = tbUserDao.getUserByEmail(email);
        if (tbUser != null) {
            // 对明文密码进行加密
            String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());

            // 判断加密后的密码和数据库中存放的密码是否匹配，匹配则表示允许登录
            if (md5Pass.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }

        return null;
    }

    /**
     * 插入一条用户信息
     *
     * @param tbUser 用户
     */
    @Override
    public void insert(TbUser tbUser) {
        tbUserDao.insert(tbUser);
    }

    /**
     * 根据用户ID删除用户信息
     *
     * @param id 用户ID
     */
    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    /**
     * 删除多个用户
     *
     * @param ids 用户ID数组
     */
    @Override
    public void deleteMulti(Long[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    /**
     * 更新用户信息
     *
     * @param tbUser 用户
     */
    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    /**
     * 根据用户名模糊查询
     *
     * @param username 用户名
     * @return 用户列表
     */
    @Override
    public List<TbUser> selectByUsername(String username) {
        return tbUserDao.selectByUsername(username);
    }
}
