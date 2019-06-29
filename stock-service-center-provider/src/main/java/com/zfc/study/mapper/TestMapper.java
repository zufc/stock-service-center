package com.zfc.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfc.study.domain.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<Test> {

    List<Test> queryTestList();

}
