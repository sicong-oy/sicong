package com.qf.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.ResultEntity;
import com.qf.entity.User;
import com.qf.feign.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUserPage")

    public String getUserPage(Page<User> page, Model model) {
        ResultEntity resultEntity = userService.getUserPage(page);
        model.addAttribute("page",resultEntity.getData());
        model.addAttribute("url","http://localhost/shop-back/user/getUserPage");
        return "user/userList";
    }

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public ResultEntity addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/deleteUser/{id}")
    @ResponseBody
    public ResultEntity deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}
