package com.zfc.study.service;


import com.zfc.study.stock.dto.SysLogDto;

public interface SysLogService {

    boolean saveLog(SysLogDto sysLogDto);

}
