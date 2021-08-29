package com.example.demo.service;

import com.example.demo.controller.UserController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {

    static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
        log.info("===service层日志===");
        return userMapper.Sel(id);
    }
}
