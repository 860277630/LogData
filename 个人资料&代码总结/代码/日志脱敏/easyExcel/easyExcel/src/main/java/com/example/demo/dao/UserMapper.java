package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User Sel(int id);

    void insert(User u);

    List<User> SelAll();

    int getCount();

    List<User> getUserByPage(int start,int pageNum);

}