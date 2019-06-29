package com.zfc.study.mq.rocketMQ.message.impl;


import com.zfc.study.mq.rocketMQ.message.MessageProcessor;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-12 11:58
 **/
public class MessageProcessorImpl implements MessageProcessor {
    @Override
    public boolean handleMessage(Object message) {
        return false;
    }

    @Override
    public Class getClazz() {
        return null;
    }

    @Override
    public Object transferMessage(String message) {
        return null;
    }
}
