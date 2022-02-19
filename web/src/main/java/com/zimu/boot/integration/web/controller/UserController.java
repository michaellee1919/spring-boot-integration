package com.zimu.boot.integration.web.controller;

import com.alibaba.fastjson.JSON;
import com.zimu.boot.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public String getAll() {
        return JSON.toJSONString(userService.getAll());
    }
}
