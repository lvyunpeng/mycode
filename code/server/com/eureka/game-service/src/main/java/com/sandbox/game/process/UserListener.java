package com.sandbox.game.process;

import com.google.gson.reflect.TypeToken;
import com.sandbox.game.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lv.yp
 * @Date 2018-05-23
 */
@Component
public class UserListener {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserListener.class);

    @RabbitListener(queues = "TEST_QUEUE")
    public void processHeadPicModify(String msg) {
        logger.info("queues head.pic.modify achieve msg, msg:{}", msg);
        try{
            Map<String, String> infoMap = (Map<String, String>) JsonUtils.fromJson(msg, new TypeToken<Map<String, String>>(){}.getType());

        }catch(Exception e){
            logger.error("update friend headPic error,msg:{}", msg, e);
        }
    }
}
