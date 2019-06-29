package com.zfc.study.rpc;

import com.zfc.study.service.TestService;
import com.zfc.study.stock.dto.TestDto;
import com.zfc.study.stock.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-27 17:38
 **/
@RestController
@RequestMapping("/api/v1/test")
public class TestRpcServiceImpl {
    @Autowired
    private TestService testService;

    @GetMapping
    public List<TestVo> questTestList(){
        List<TestVo> list =  testService.queryTestList();
        return list;
    }

    @PostMapping
    public int saveTest(@RequestBody TestDto testDto){
        return testService.saveTest(testDto);
    }
}
