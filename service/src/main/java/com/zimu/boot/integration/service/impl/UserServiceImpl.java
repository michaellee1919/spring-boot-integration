package com.zimu.boot.integration.service.impl;

import com.github.pagehelper.PageHelper;
import com.zimu.boot.integration.common.model.User;
import com.zimu.boot.integration.dao.mapper.UserMapper;
import com.zimu.boot.integration.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAll() {
        PageHelper.startPage(1, 10);
        return userMapper.getAll();
    }

    @Override
    public User getOne(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }
}
