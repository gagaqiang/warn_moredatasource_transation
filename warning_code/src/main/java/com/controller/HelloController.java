package com.controller;

import com.service.demo.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api("swaggerTestController相关api")
public class HelloController {

    private final static Logger _logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private SysService sysService;

    @ApiOperation(value = "测试swagger", notes = "hello-测试")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        _logger.error("----111111111111-2222---");
        return "Hello Spring Boot!";
    }



    @ApiOperation(value = "测试多个数据库", notes = "多个数据库")
    @RequestMapping(value="/getMoreResourceTest", method = RequestMethod.GET)
    public String getTest(){
        sysService.testWorkouder();
        return "SUCCESS";
    }



    @ApiOperation(value = "测试五期", notes = "测试五期")
    @RequestMapping(value="/testWuqi", method = RequestMethod.GET)
    public String testWuqi(){
        sysService.testUser();
        return "SUCCESS";
    }


}
