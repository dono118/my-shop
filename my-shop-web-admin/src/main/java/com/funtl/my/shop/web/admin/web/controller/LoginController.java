package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.commons.utils.CookieUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param request 客户端的请求
     * @return 跳转页面
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String userInfo = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_USER_INFO);
        if (StringUtils.isNotBlank(userInfo)) {
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("isRemember", true);
        }
        // 判断 Session 中是否有该用户的登录信息
        TbUser tbUser = (TbUser) request.getSession().getAttribute(ConstantUtils.SESSION_USER);
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
     * @param request 客户端的请求
     * @param response 服务器的响应
     * @return 跳转页面
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password, HttpServletRequest request,
                        HttpServletResponse response) {
        boolean isRemember = request.getParameter("isRemember") != null;
        if (!isRemember) {
            // 设置Cookie
            CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_USER_INFO);
        }

        TbUser tbUser = tbUserService.login(email, password);

        // 登录失败
        if (tbUser == null) {
            return login(request);
        }

        // 登录成功
        else {
            if (isRemember) {
                // 设置Cookie
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_USER_INFO,
                        String.format("%s:%s", email, password), 24 * 3600);
            }
            // 将登录信息放入 Session
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
    }

    /**
     * 注销用户
     *
     * @param request 客户端的请求
     * @return 跳转页面
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // 销毁当前 session
        request.getSession().invalidate();
        return login(request);
    }
}
