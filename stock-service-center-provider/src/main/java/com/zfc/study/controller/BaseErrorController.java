package com.zfc.study.controller;

import com.zfc.study.exception.BusinessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-14 16:34
 **/
@RestController
public class BaseErrorController {
    @RequestMapping("/json")
    public void json(ModelMap modelMap) throws Exception{
        System.out.println(modelMap.get("author"));
        String s = null;
        if (s == null){
            throw  new BusinessException("500","字段为空");
        }
    }
}
