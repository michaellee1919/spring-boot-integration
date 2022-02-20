package com.zimu.boot.integration.web.controller;

import com.zimu.boot.integration.common.model.User;
import com.zimu.boot.integration.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api("用户注册登录接口")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户", notes="获取所有用户")
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户", notes="获取所有用户")
    public User getOne(Long id) {
        return userService.getOne(id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.PUT)
    @ApiOperation(value = "获取所有用户", notes="获取所有用户")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有用户", notes="获取所有用户")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "获取所有用户", notes="获取所有用户")
    public void delete(Long id) {
        userService.delete(id);
    }
}
