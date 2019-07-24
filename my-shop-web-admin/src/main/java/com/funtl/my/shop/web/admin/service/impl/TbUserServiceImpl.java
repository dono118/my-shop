package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author Unicorn
 * @create 2019-07-13 16:20
 */
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser, TbUserDao> implements TbUserService {

    /**
     * 登录
     *
     * @param email 用户
     * @param password 密码
     * @return 用户
     */
    @Override
    public TbUser login(String email, String password) {

        TbUser tbUser = dao.getUserByEmail(email);
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
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);

        // 通过验证
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            // 设置更新时间
            tbUser.setUpdated(new Date());

            // 密码加密
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));

            // 新增用户
            if (tbUser.getId() == null) {
                tbUser.setCreated(new Date());
                dao.insert(tbUser);
                baseResult.setMessage("新增用户成功");
            }

            // 更新用户
            else {
                dao.update(tbUser);
                baseResult.setMessage("更新用户成功");
            }
        }

        return baseResult;
    }


    /**
     * 用户信息的有效性验证
     *
     * @param tbUser 用户
     */
    private BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();
        // 非空验证
        // 判断邮箱是否为空
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空, 请重新输入");
        }

        // 判断邮箱格式是否正确
        else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱格式不正确, 请重新输入");
        }

        // 判断密码是否为空
        else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空, 请重新输入");
        }

        // 判断用户名是否为空
        else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("姓名不能为空, 请重新输入");
        }

        // 判断手机号是否为空
        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机号不能为空, 请重新输入");
        }

        // 判断手机号格式是否正确
        else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机号格式不正确, 请重新输入");
        }

        return baseResult;
    }
}
