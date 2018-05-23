package com.sandbox.user.controller;

import com.sandbox.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author lv.yp
 * @Date 2018-05-23
 */
@RestController
@Api(value="userController", description="用户相关服务")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "测试")
    @RequestMapping(
            value = "/api/v1/user/test",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )  public String getGameDetailInfo(
    ){
        return "success";
    }
}
