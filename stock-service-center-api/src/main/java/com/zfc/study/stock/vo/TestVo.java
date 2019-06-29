package com.zfc.study.stock.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-27 16:09
 **/
@Data
public class TestVo implements Serializable {
    private static final long serialVersionUID = 3920158845786366456L;

    private String userId;

    private String userName;
}
