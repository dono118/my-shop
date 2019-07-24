package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author Unicorn
 * @create 2019-07-13 15:47
 */
public interface TbUserService extends BaseService<TbUser> {

    /**
     * 登录
     *
     * @param email 用户
     * @param password 密码
     * @return 用户
     */
    TbUser login(String email, String password);
}
