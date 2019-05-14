package com.dao.demo;

import com.entity.erpsystem.SysAreaInfo;
import org.apache.ibatis.annotations.Mapper;


/**
 * SysArea
 */
public interface SysAreaDao {

	public SysAreaInfo getEntity(Long id);


}
