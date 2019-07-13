package com.funtl.my.shop.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.entity.User;
import com.funtl.my.shop.service.UserService;
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
    private UserService userService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest) {
        // 判断 Session 中是否有该用户的登录信息
        User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        if (user != null) {
            return "redirect:/main";
        }
        return "login";
    }

    /**
     * 处理登录请求
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest httpServletRequest) {
        User user = userService.login(email, password);

        // 登录失败
        if (user == null) {
            return login(httpServletRequest);
        }

        // 登录成功
        else {
            // 将登录信息放入 Session
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            return "redirect:/main";
        }
    }
}
