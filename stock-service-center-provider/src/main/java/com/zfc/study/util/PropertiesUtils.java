package com.zfc.study.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zufeichao
 * @ProjectName stock-service-center
 * @Description TODO
 * @Date 2019-06-28 16:29
 **/
@Slf4j
public class PropertiesUtils {

    public static Map<String,String> propertiesMap = new HashMap<>();

    public static ConcurrentHashMap<String,String> concurrentHashMapMap = new ConcurrentHashMap<>();


    static {
        Properties properties = new Properties();
        InputStream in = null;

        try {
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream("excel.properties");
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

    public static Map<String,String> getResourceProperties(String propertiesName){
        final ResourceBundle rb = ResourceBundle.getBundle(propertiesName);
        Enumeration<String> es = rb.getKeys();
        while (es.hasMoreElements()){
            String key = es.nextElement();
            log.info("key:{},value={}",key,rb.getString(key));
            concurrentHashMapMap.put(key,rb.getString(key));
        }
        return concurrentHashMapMap;
    }

    public static String getResourceProperties(String propertiesName,String fieldKey){
        final ResourceBundle rb = ResourceBundle.getBundle(propertiesName);
        return rb.getString(fieldKey);
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
        Map<String,String> map = getResourceProperties("mq");
        System.out.println(map);

        String value =getResourceProperties("mq","testTags");

        System.out.println(value);
    }

}
