package com.zfc.study.controller;

import com.zfc.study.service.TestService;
import com.zfc.study.stock.vo.TestVo;
import com.zfc.study.util.ApiResult;
import com.zfc.study.util.JsonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-27 16:06
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public JsonBean questTestList(){
         List<TestVo> list =  testService.queryTestList();
         return new JsonBean(list);
    }

    @GetMapping("/list")
    public List<TestVo> questTestList2(){
        List<TestVo> list =  testService.queryTestList();
        return list;
    }


}
