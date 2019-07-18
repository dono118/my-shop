package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.misc.Regexp;

import java.util.Date;
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
                tbUserDao.insert(tbUser);
                baseResult.setMessage("新增用户成功");
            }

            // 更新用户
            else {
                tbUserDao.update(tbUser);
                baseResult.setMessage("更新用户成功");
            }
        }

        return baseResult;
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
