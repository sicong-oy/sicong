package com.qf.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qf.entity.User;
import com.qf.mapper.IUserMapper;
import com.qf.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper,User> implements IUserService {
}
