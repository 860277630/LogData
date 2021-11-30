package com.example.demo.service;

import com.example.demo.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {
    public static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
        log.info("===service层日志===");
        return userMapper.Sel(id);
    }
}