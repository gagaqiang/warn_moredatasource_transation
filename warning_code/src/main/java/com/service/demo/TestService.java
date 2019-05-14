package com.service.demo;

import com.alibaba.fastjson.JSONObject;
import com.dao.sys.UserEntityMapper;
import com.ds.DS;
import com.dto.weChat.WeChatResuDto;
import com.entity.UserEntity;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestService {



    @Autowired
    private UserEntityMapper userEntityMapper;




    public static void main(String[] args){

        String pa = "{\"msg\":\"已发送\",\"result\":\"1\\n\",\"retCode\":\"200\"}";
        Gson gson = new Gson();

        //WeChatResuDto we = gson.fromJson(pa, WeChatResuDto.class);
        //System.out.println(we.getRetCode());


        Integer num = 1;
        List<Integer> li = Lists.newArrayList(num);
        System.out.println(JSONObject.toJSONString(li));
    }



    @DS(value = "xyx_carsharing2")
    public void testUser(){

        UserEntity record = userEntityMapper.selectByPrimaryKey("297e0b8f67ef03e30167ef0795a00000");
        if (null != record){
            record.setPhoneNo("13788845435353489898989898989898899898878787872382732732838473847");
            userEntityMapper.updateByPrimaryKeySelective(record);
        }

    }



}
