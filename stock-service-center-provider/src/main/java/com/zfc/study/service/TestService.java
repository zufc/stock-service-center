package com.zfc.study.service;

import com.zfc.study.stock.dto.TestDto;
import com.zfc.study.stock.vo.TestVo;

import java.util.List;

public interface TestService {

    List<TestVo> queryTestList();

    int saveTest(TestDto testDto);

}
