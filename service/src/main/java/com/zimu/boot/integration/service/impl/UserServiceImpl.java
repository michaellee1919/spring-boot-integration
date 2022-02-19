package com.zimu.boot.integration.service.impl;

import com.zimu.boot.integration.common.model.User;
import com.zimu.boot.integration.dao.mapper.UserMapper;
import com.zimu.boot.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAll() {
        return userMapper.getAll();
    }
}
