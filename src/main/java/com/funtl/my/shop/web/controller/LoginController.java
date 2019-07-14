package com.funtl.my.shop.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.entity.TbUser;
import com.funtl.my.shop.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Unicorn
 * @create 2019-07-13 15:31
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登录页面
     *
     * @return 跳转页面
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest) {
        // 判断 Session 中是否有该用户的登录信息
        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        if (tbUser != null) {
            return "redirect:/main";
        }
        return "login";
    }

    /**
     * 处理登录请求
     *
     * @param email 邮箱
     * @param password 密码
     * @return 跳转页面
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest httpServletRequest) {
        TbUser tbUser = tbUserService.login(email, password);

        // 登录失败
        if (tbUser == null) {
            return login(httpServletRequest);
        }

        // 登录成功
        else {
            // 将登录信息放入 Session
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
    }
}
