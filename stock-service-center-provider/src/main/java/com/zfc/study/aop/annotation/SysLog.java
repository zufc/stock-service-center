package com.zfc.study.aop.annotation;

import java.lang.annotation.*;

/**
 * @Author zufeichao
 * @Description 定义系统日志注解
 * @Date 13:50 2019/6/11
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default  "";
}
