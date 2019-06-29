package com.zfc.study.stock.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-27 16:08
 **/
@Data
public class TestDto implements Serializable {

    private static final long serialVersionUID = -7705256454899850622L;

    private String userId;

    private String userName;
}
