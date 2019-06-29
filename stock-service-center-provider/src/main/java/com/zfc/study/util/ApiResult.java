package com.zfc.study.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-14 10:35
 **/
@Slf4j
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -9095237167028356775L;
    private T data;
    private String code = "0";
    private String msg = "ok";

    public ApiResult(){

    }

    public ApiResult(T data){
        this.data = data;
    }

    public ApiResult(T data,String code,String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

    @Override
    public String toString(){
        String toString = Strings.EMPTY;
        try {
            toString =  JSONObject.toJSONString(this);
        }catch (Exception e){
           log.error("ApiResult JSON transfer error",e.getMessage());
        }
        return toString;
    }


}
