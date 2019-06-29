package com.zfc.study.service.impl;

import com.zfc.study.service.SysLogService;
import com.zfc.study.stock.dto.SysLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 14:08
 **/
@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {
    @Override
    public boolean saveLog(SysLogDto sysLogDto) {
        log.info("{}",sysLogDto);
        return true;
    }
}
