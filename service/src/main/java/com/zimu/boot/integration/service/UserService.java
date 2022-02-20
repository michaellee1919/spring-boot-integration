package com.zimu.boot.integration.service;

import com.zimu.boot.integration.common.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);
}
