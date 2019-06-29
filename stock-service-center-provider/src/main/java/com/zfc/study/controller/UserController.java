package com.zfc.study.controller;

import com.zfc.study.domain.entity.User;
import com.zfc.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 16:27
 **/
@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("list")
    public List<User> queryList(){
        List<User> list = userService.queryList();
        System.out.println(list);
        return list;
    }
}
