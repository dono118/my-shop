package com.funtl.my.shop.service.test;

import com.funtl.my.shop.dao.TbUserDao;
import com.funtl.my.shop.entity.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author Unicorn
 * @create 2019-07-14 17:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserDao tbUserDao;

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@funtl.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
        tbUser.setPhone("15888888888");
        tbUser.setUsername("Unicorn");
        tbUser.setCreated(new Date());
        tbUser.setUpdate(new Date());

        tbUserDao.insert(tbUser);
    }

    @Test
    public void testGetUserByEmail() {
        TbUser user = tbUserDao.getUserByEmail("admin@funtl.com");
        System.out.println(user.getPassword());
    }
}
