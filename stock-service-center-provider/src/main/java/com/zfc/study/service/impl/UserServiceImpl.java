package com.zfc.study.service.impl;

import com.zfc.study.domain.entity.User;
import com.zfc.study.mapper.UserMapper;
import com.zfc.study.service.UserService;
import com.zfc.study.util.RocketMQProducerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 10:36
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryList() {

        return userMapper.queryList();
    }

    @Override
    public int insertList(List<User> list) {
       int result =  userMapper.insertList(list);
       //mq下发
/*       list.forEach(user -> {
           RocketMQProducerUtil.sendMessage(user);
       });*/
       return result;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }


}
