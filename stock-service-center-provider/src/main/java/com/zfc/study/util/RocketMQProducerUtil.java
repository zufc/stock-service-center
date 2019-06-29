package com.zfc.study.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-12 20:41
 **/
@Slf4j
public class RocketMQProducerUtil {

    private static DefaultMQProducer producer = SpringBeanUtil.getBean(DefaultMQProducer.class);

    public static void  sendMessage(Object obj){
        Message message = new Message("topic2019", "test", JSON.toJSONString(obj).getBytes());
        try {
            SendResult sendResult = producer.send(message);
            log.info("发送了消息{}",sendResult);
        }catch (Exception e){
            log.error("MQ下发失败",e);
        }
    }

    public static void sendMessage(String topic,String tags,Object object){
        Message message = new Message(topic, tags, JSON.toJSONString(object).getBytes());
        try {
            SendResult sendResult = producer.send(message);
            log.info("发送了消息{}",sendResult);
        }catch (Exception e){
            log.error("MQ下发失败",e);
        }
    }


}
