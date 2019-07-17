package com.funtl.my.shop.web.admin.service.test;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;


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
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserDao.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testGetUserById() {
        TbUser tbUser = tbUserDao.getUserById((long) 38);
        if (tbUser != null) {
            System.out.println(tbUser.getEmail());
        }
    }

    @Test
    public void testGetUserByEmail() {
        TbUser tbUser = tbUserDao.getUserByEmail("admin@funtl.com");
        if (tbUser != null) {
            System.out.println(tbUser.getPassword());
        }
    }

    @Test
    public void testInsert() {
        /*TbUser tbUser = new TbUser();
        tbUser.setEmail("mary@qq.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setPhone("13512345588");
        tbUser.setUsername("Mary");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserDao.insert(tbUser);*/
    }

    @Test
    public void testDelete() {
        TbUser tbUser = tbUserDao.getUserById(41L);
        if (tbUser != null) {
            tbUserDao.delete(41L);
        }
    }

    @Test
    public void testDeleteMulti() {
        /*Long[] ids = {43L, 44L};
        for (Long id: ids) {
            TbUser tbUser = tbUserDao.getUserById(id);
            if (tbUser != null) {
                tbUserDao.delete(id);
            }
        }*/
    }

    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserDao.getUserById(44L);
        tbUser.setEmail("jack@hotmail.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setUpdated(new Date());
        tbUserDao.update(tbUser);
    }

    @Test
    public void testSelectByUsername() {
        List<TbUser> tbUsers = tbUserDao.selectByUsername("ic");
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }
}
