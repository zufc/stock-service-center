package com.zfc.study.exportExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 20:45
 **/
public class ListSource {

    public List<List<String>> listSource() {
        List<List<String>> totalList = new ArrayList<List<String>>();
        for (int i = 0; i < 10; i++) {
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < 5; j++) {
                String str = "";
                String source = getStr(j, str);
                list.add(source);
            }
            totalList.add(list);
        }
        return totalList;
    }
    private String getStr(int j, String str) {
        switch (j) {
            case 0:
                str = "ID";
                break;
            case 1:
                str = "用户账号";
                break;
            case 2:
                str = "用户姓名";
                break;
            case 3:
                str = "密码";
                break;
            case 4:
                str = "年龄";
                break;
        }
        return str;
    }



}
