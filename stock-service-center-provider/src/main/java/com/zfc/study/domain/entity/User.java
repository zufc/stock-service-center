package com.zfc.study.domain.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 10:04
 **/
@Data
@ExcelTarget("20")
public class User implements Serializable {
    private static final long serialVersionUID = -7784095314173133484L;

    @Excel(name = "ID")
    private String id;
    @Excel(name = "用户账号",orderNum = "0")
    private String  userId;
    @Excel(name = "用户姓名",orderNum = "1")
    private String userName;
    @Excel(name = "密码",orderNum = "2")
    private String password;
    @Excel(name = "年龄",orderNum = "3")
    private Integer age;


}
