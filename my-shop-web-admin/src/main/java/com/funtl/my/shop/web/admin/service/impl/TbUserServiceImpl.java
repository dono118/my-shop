package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author Unicorn
 * @create 2019-07-13 16:20
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Resource
    private TbUserDao tbUserDao;

    @Override
    public void insert(TbUser tbUser) {
        tbUserDao.insert(tbUser);
    }

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
}
