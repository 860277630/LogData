package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Sel(int id){
        return userMapper.Sel(id);
    }

    public void insert(User u){
        userMapper.insert(u);
    }

    public List<User> SelAll(){return userMapper.SelAll();}

    public int getCount(){return userMapper.getCount();}

    public List<User> getUserByPage(int start,int pageNum){return userMapper.getUserByPage(start,pageNum);}
}