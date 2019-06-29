package com.zfc.study.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-14 10:27
 **/
@Data
public class JsonBean implements Serializable {
    private static final long serialVersionUID = -2573394662439928722L;
    private Object data;
    private String code = "0";
    private String msg = "ok";
    private boolean result = true;


    public JsonBean(){

    }

    public JsonBean(boolean result){
        this.result = true;

    }

    public JsonBean(Object data){
        this.data = data;
    }

    public JsonBean(Object data, String code, String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
