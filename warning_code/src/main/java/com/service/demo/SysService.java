package com.service.demo;

import com.dao.demo.UserEntityMapper;
import com.dao.demo.WorkOrderEntityMapper;
import com.ds.DS;
import com.entity.UserEntity;
import com.entity.WorkOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysService {
    public final static Logger _logger = LoggerFactory.getLogger(SysService.class);


    @Autowired
    private WorkOrderEntityMapper workOrderEntityMapper;

    @Autowired
    private UserEntityMapper userEntityMapper;




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void testWorkouder(){

        WorkOrderEntity wEntity = workOrderEntityMapper.selectByPrimaryKey(1178);
        wEntity.setBusinessNo("2323");
        workOrderEntityMapper.updateByPrimaryKey(wEntity);



        UserEntity record = userEntityMapper.selectByPrimaryKey("297e0b8f67ef03e30167ef0795a00000");
        if (null != record){
            record.setPhoneNo("111328023840238423842847239847923842394802384029840498493r9348938491232");
            userEntityMapper.updateByPrimaryKeySelective(record);
        }

        int a=10;
        int b = 0;
        //System.out.print(a/b);
    }







    @DS("wuqi_base")
    public void testWork(){
        WorkOrderEntity wEntity = workOrderEntityMapper.selectByPrimaryKey(1178);
        wEntity.setBusinessNo("123");
        workOrderEntityMapper.updateByPrimaryKey(wEntity);

    }

    @DS("xyx_carsharing2")
    @Transactional(rollbackFor = Throwable.class)
    public void testUser(){

        UserEntity record = userEntityMapper.selectByPrimaryKey("297e0b8f67ef03e30167ef0795a00000");
        if (null != record){
            record.setPhoneNo("111473847");
            userEntityMapper.updateByPrimaryKeySelective(record);
        }

        int a=10;
        int b = 0;
        System.out.print(a/b);
    }





}
