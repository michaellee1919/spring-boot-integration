package com.zimu.boot.integration.service.impl;

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

    public List<User> getAll() {
        return userMapper.getAll();
    }

    public User getOne(Long id) {
        return userMapper.getOne(id);
    }

    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }
}
