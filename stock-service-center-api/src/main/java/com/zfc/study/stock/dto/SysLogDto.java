package com.zfc.study.stock.dto;

import lombok.Data;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 14:06
 **/
@Data
public class SysLogDto {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;


}
