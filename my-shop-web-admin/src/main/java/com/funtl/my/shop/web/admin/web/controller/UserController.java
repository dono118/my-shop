package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户管理
 *
 * @author Unicorn
 * @create 2019-07-17 21:51
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    private final TbUserService tbUserService;

    @Autowired
    public UserController(TbUserService tbUserService) {
        this.tbUserService = tbUserService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }
}
