package com.example.demo.service;

import com.example.demo.controller.UserController;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
        log.info("========000===============");
        return userMapper.Sel(id);
    }
}
