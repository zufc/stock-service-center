package com.zfc.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zfc.study.domain.entity.User;
import com.zfc.study.service.UserService;
import com.zfc.study.util.DateUtils;
import com.zfc.study.util.ExcelPoiUtils;
import com.zfc.study.util.ExcelPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 19:45
 **/
@RestController
@RequestMapping("/api/v1/excel/")
@Slf4j
public class ExcelController {

    @Autowired
    private UserService userService;

    @PostMapping("importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file){
        String message = null;
        User user = null;
        List<User> userList  = Lists.newArrayList();
        try {
            List<String[]> list = ExcelPoiUtils.readExcel(file.getInputStream());
            for(int i = 1; i < list.size(); i++){
                String[] cellList = list.get(i);
                user = new User();
                user.setId(cellList[0]);
                user.setUserId(cellList[1]);
                user.setUserName(cellList[2]);
                user.setPassword(cellList[3]);
                user.setAge(Integer.valueOf(cellList[4]));

                log.info("从Excel导入数据到数据库的详细为：{}", JSONObject.toJSONString(user));
                userList.add(user);
            }

            int result = userService.insertList(userList);
            log.info("共{}条数据,导入成功{}条数据",userList.size(),result);
            message =  "共"+userList.size()+"条数据,导入成功"+result+"条数据";
        }/*catch (IOException ioe){
            logger.warn(ioe.getMessage());
        }*/catch (Exception e){
            log.warn(e.getMessage());
        }


        return message;
    }

    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception{

        List<User> userList = userService.queryList();
        List<Map<String,Object>> mapList = new ArrayList<>();
        userList.forEach(user->{
            Map<String,Object> map = object2Map(user);
            mapList.add(map);
        });
        List<String> fieldList = ExcelPropertiesUtil.getProperties("user.export");
        String fileName = DateUtils.formatDateTimeToString2(LocalDateTime.now()) + ".xls";
        ExcelPoiUtils.writeExcel2(fileName,mapList,fieldList,response);


    }


    /**
          * 实体对象转成Map
          * @param obj 实体对象
          * @return
          */
     public static Map<String, Object> object2Map(Object obj) {
            Map<String, Object> map = new HashMap<>();
            if (obj == null) {
                return map;
            }
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            try {
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(obj));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
    }



}
