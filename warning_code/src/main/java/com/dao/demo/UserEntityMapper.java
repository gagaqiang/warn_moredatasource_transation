package com.dao.demo;

import com.ds.DS;
import com.entity.UserEntity;

@DS("xyx_carsharing2")
public interface UserEntityMapper {

    UserEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserEntity record);


}