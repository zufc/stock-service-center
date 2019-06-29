package com.zfc.study.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description 自定义业务异常
 * @Date 2019-06-14 11:01
 **/
@Data
public class BusinessException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -6828574125960212440L;

    private String msg;
    private String code = "500";

    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String code,String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BusinessException(String msg,String code,Throwable e){
        super(msg,e);
        this.msg = msg;
        this.code = code;
    }


}
