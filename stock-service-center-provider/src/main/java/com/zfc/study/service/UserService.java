package com.zfc.study.service;

import com.zfc.study.domain.entity.User;

import java.util.List;

public interface UserService {

    List<User> queryList();

    int insertList(List<User> list);

    int insert(User user);
}
