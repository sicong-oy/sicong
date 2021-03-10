package com.qf.feign.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.qf.entity.ResultEntity;
import com.qf.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SHOP-USER")
public interface IUserService {

    @RequestMapping(value = "/user/login")
    public ResultEntity login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/user/getUserPage")
    public ResultEntity getUserPage(@RequestBody Page<User> page);

    @RequestMapping(value = "/user/addUser")
    public ResultEntity addUser(@RequestBody User user);

    @RequestMapping(value = "/deleteUser/{id}")
    public ResultEntity deleteUser(@PathVariable("id") Integer id);
}
