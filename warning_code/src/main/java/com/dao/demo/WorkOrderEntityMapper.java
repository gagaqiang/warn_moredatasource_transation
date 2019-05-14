package com.dao.demo;

import com.ds.DS;
import com.entity.WorkOrderEntity;


@DS("wuqi_base")
public interface WorkOrderEntityMapper {


    WorkOrderEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(WorkOrderEntity record);

}