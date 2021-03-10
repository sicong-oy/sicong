package com.qf.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.ResultEntity;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    public ResultEntity login(String username, String password){

        // 1.设置条件
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("username",username);
        entityWrapper.eq("password",password);

        // 2.查询
        User user = userService.selectOne(entityWrapper);

        if(user != null){
            return ResultEntity.response(user);
        }else{
            return  ResultEntity.error("用户名获密码错误。。。。");
        }
    }

    @RequestMapping(value = "/getUserPage")
    public ResultEntity getUserPage(@RequestBody Page<User> page){
        return ResultEntity.response(userService.selectPage(page));
    }

    @RequestMapping(value = "/addUser")
    public ResultEntity addUser(@RequestBody User user){
        return  ResultEntity.response(userService.insert(user));
    }


    @RequestMapping(value = "/deleteUser/{id}")
    public ResultEntity deleteUser(@PathVariable Integer id){
        return ResultEntity.response(userService.deleteById(id));
    }



}
