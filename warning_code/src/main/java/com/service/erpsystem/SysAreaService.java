package com.service.erpsystem;

import com.dao.demo.SysAreaDao;
import com.dao.sys.UserEntityMapper;
import com.dao.sys.WorkOrderEntityMapper;
import com.ds.DS;
import com.entity.UserEntity;
import com.entity.erpsystem.SysAreaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * SysArea
 */
@Service
public class SysAreaService {

    public final static Logger _logger = LoggerFactory.getLogger(SysAreaService.class);

    @Autowired
    private SysAreaDao dao;




    @Autowired
    private UserEntityMapper userEntityMapper;




    @DS(value = "erp_system")
    public SysAreaInfo getEntity(Long id) {
        return this.dao.getEntity(id);
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
