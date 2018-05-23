package com.sandbox.game.controller;

import com.sandbox.game.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lv.yp
 * @Date 2018-05-23
 */
@RestController
@Api(value="gameController", description="游戏相关服务")
public class GameController {

    @Autowired
    private GameService gameService;

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @ApiOperation(value = "测试")
    @RequestMapping(
            value = "/api/v1/game/test",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )  public String getGameDetailInfo(
    ){
        return "success";
    }
}
