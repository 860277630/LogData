package com.example.demo.dao;

import com.example.demo.model.Park;

public interface ParkDao {
    int deleteByPrimaryKey(String id);

    int insert(Park record);

    int insertSelective(Park record);

    Park selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Park record);

    int updateByPrimaryKey(Park record);
}