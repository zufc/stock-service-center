package com.zfc.study.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-13 10:43
 **/
@Slf4j
public class PropertiesConfig {

    public static Map<String,String> propertiesMap = new HashMap<String,String>();
    static {
        Properties properties = new Properties();
        InputStream in = null;

        try {
            in = PropertiesConfig.class.getClassLoader().getResourceAsStream("excel.properties");
            properties.load(in);
            for(Object key : properties.keySet()){
                String keyStr  = key.toString();
                try {
                    propertiesMap.put(keyStr,new String(properties.getProperty(keyStr).getBytes("utf-8")));
                }catch (UnsupportedEncodingException e){
                    log.error("编码报错",e);
                }catch (Exception e){
                    log.error("报错",e);
                }
            }

            in.close();
        }catch (Exception e){
            log.error("报错",e);
        }

    }

    public static void main(String[] args) {
        System.out.println(propertiesMap);
        String s = MapUtils.getString(propertiesMap,"user.import");
        List<String> fieldList = Arrays.asList(s);
        System.out.println(fieldList);
    }

}
