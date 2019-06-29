package com.zfc.study.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.zfc.study.domain.entity.User;
import com.zfc.study.service.UserService;
import com.zfc.study.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 16:40
 **/
@RestController
@RequestMapping("/api/v1/easypoiexcel/")
@Slf4j
public class EasypoiExcelController {

    @Autowired
    private UserService userService;

    @GetMapping("exportExcel")
    public void export(HttpServletResponse response) {
        // 模拟从数据库获取需要导出的数据
        List<User> userList = userService.queryList();

        // 导出操作
        ExcelUtils.exportExcel(userList, "easypoi导出功能", "导出sheet1", User.class, "测试user.xls", response);

    }

    @PostMapping("importExcel")
    public String importExcel(@RequestParam("file") MultipartFile file){
        String message = null;
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);
        importParams.setTitleRows(1);
        importParams.setNeedVerfiy(false);
        try {
            //ExcelImportResult<User> result = ExcelImportUtil.importExcelMore(file.getInputStream(),User.class,importParams);
            // List<User> userList = result.getList();
            List<User> userList = ExcelUtils.importExcel(file,1,1, User.class);
            userList.forEach(user->{
                log.info("从Excel导入数据到数据库的详细为：{}", JSONObject.toJSONString(user));
            });
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

}
