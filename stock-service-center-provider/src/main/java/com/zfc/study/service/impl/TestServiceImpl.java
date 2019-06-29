package com.zfc.study.service.impl;

import com.google.common.collect.Lists;
import com.zfc.study.domain.entity.Test;
import com.zfc.study.service.TestService;
import com.zfc.study.mapper.TestMapper;
import com.zfc.study.stock.dto.TestDto;
import com.zfc.study.stock.vo.TestVo;
import com.zfc.study.util.RocketMQProducerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-27 16:11
 **/
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestVo> queryTestList() {
        List<TestVo> testVoList = Lists.newArrayList();
        List<Test> list = testMapper.queryTestList();
        if(CollectionUtils.isNotEmpty(list)){

            list.forEach(test->{
                TestVo testVo = new TestVo();
                BeanUtils.copyProperties(test,testVo);
                testVoList.add(testVo);

            });
        }




        return testVoList;
    }

    @Override
    public int saveTest(TestDto testDto) {

        Test test = new Test();
        BeanUtils.copyProperties(testDto,test);
        //int result = testMapper.insert(test);
        RocketMQProducerUtil.sendMessage("testTopic","testTag",test);

        return 1;
    }
}
