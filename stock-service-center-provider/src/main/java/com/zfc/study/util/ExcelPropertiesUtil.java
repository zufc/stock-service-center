package com.zfc.study.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-13 10:35
 **/
@Slf4j
public class ExcelPropertiesUtil {

    public static Map<String,String> propertiesMap = new HashMap<String,String>();

    static {
        Properties properties = new Properties();
        InputStream in = null;

        try {
            in = ExcelPropertiesUtil.class.getClassLoader().getResourceAsStream("excel.properties");
            properties.load(in);
            for(Object key : properties.keySet()){
                String keyStr  = key.toString();
                try {
                    propertiesMap.put(keyStr,new String(properties.getProperty(keyStr).getBytes("utf-8")));
                }catch (UnsupportedEncodingException e){
                    log.error("UnsupportedEncodingException",e);
                }catch (Exception e){
                    log.error("Exception",e.getMessage());
                }
            }

            in.close();
        }catch (Exception e){
            log.error("excel.properties读取处理失败",e);
        }

    }

    /**
     * @Author zufeichao
     * @Description 获取指定value
     * @Date 13:33 2019/6/13
     * @Param [fieldName]
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> getProperties(String fieldName){
        String fieldValue =  MapUtils.getString(propertiesMap,fieldName);
        if(StringUtils.isBlank(fieldValue)){
            return null;
        }
        String[] fieldArr = fieldValue.split(",");
        return Arrays.asList(fieldArr);
    }

    public static void main(String[] args) {
        List<String> l = getProperties("user.export");

        System.out.println(l.get(0));
    }

}
