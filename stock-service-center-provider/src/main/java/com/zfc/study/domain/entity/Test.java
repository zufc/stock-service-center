package com.zfc.study.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-27 16:10
 **/
@Data
public class Test implements Serializable {
    private static final long serialVersionUID = 2543386096169337188L;

    private String userId;

    private String userName;
}
