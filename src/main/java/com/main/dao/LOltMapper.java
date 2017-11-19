package com.main.dao;

import com.main.model.LOlt;

public interface LOltMapper {
    int deleteByPrimaryKey(String id);

    int insert(LOlt record);

    int insertSelective(LOlt record);

    LOlt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LOlt record);

    int updateByPrimaryKey(LOlt record);
}