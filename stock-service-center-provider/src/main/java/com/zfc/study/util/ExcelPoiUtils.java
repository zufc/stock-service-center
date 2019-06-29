package com.zfc.study.util;

import com.zfc.study.exportExcel.ListSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-06-11 19:58
 **/
@Slf4j
public class ExcelPoiUtils {


    /**
     * @Author zufeichao
     * @Description //TODO
     * @Date 21:00 2019/6/11
     * @Param [filePath]
     * @return java.util.List<java.lang.String[]>
     **/
    public static List<String[]> readExcel(InputStream ips){
        List<String[]> dataList = new ArrayList<String[]>();
        boolean isExcel2003 = true;
    /*    if(isExcel2007(file.getName())){
            isExcel2003 = false;
        }*/
 /*       InputStream ips =null;
        try {
            ips = new FileInputStream(file);
        }catch (FileNotFoundException ex){
            log.warn("读文件失败：{}",ex.getMessage());
        }*/
        Workbook wb = null;
        try {
            wb = isExcel2003 ? new HSSFWorkbook(ips) : new XSSFWorkbook(ips);
        }catch (IOException ex){
            log.warn("创建workbook失败：{}",ex.getMessage());
        }
        Sheet sheet = wb.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalCells = 0;
        if(totalRows >= 1 && sheet.getRow(0) != null){
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for (int r = 0 ; r < totalRows ; r++){
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            String[] rowList = new String[totalCells];
            for (int c = 0; c < totalCells; c++){
                Cell cell = row.getCell(c);
                String cellValue = "";
                if(cell == null){
                    rowList[c] = cellValue;
                    continue;
                }
                cellValue = ConvertCellStr(cell,cellValue);
                rowList[c] = cellValue;
            }
            dataList.add(rowList);
        }

        return dataList;
    }

    /**
     * @Author zufeichao
     * @Description //TODO
     * @Date 21:00 2019/6/11
     * @Param [filePath]
     * @return java.util.List<java.lang.String[]>
     **/
    public static List<String[]> readExcel(String filePath){
        List<String[]> dataList = new ArrayList<String[]>();
        boolean isExcel2003 = true;
        if(isExcel2007(filePath)){
            isExcel2003 = false;
        }
        File file = new File(filePath);
        InputStream ips =null;
        try {
            ips = new FileInputStream(file);
        }catch (FileNotFoundException ex){
            log.warn("读文件失败：{}",ex.getMessage());
        }
        Workbook wb = null;
        try {
            wb = isExcel2003 ? new HSSFWorkbook(ips) : new XSSFWorkbook(ips);
        }catch (IOException ex){
            log.warn("创建workbook失败：{}",ex.getMessage());
        }
        Sheet sheet = wb.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalCells = 0;
        if(totalRows >= 1 && sheet.getRow(0) != null){
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for (int r = 0 ; r < totalRows ; r++){
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            String[] rowList = new String[totalCells];
            for (int c = 0; c < totalCells; c++){
                Cell cell = row.getCell(c);
                String cellValue = "";
                if(cell == null){
                    rowList[c] = cellValue;
                    continue;
                }
                cellValue = ConvertCellStr(cell,cellValue);
                rowList[c] = cellValue;
            }
            dataList.add(rowList);
        }

        return dataList;
    }

    /**
     * @Author zufeichao
     * @Description xls
     * @Date 21:00 2019/6/11
     * @Param [filePath]
     * @return void
     **/
    public static void writeExcel(String filePath) throws Exception{

        List<List<String>> dateList = new ListSource().listSource();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");//添加sheet
        //表格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//指定单元格居中对齐


        // 在索引0的位置创建第一行

        for (int i = 0; i < dateList.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            List<String> list = dateList.get(i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(list.get(j));
                cell.setCellStyle(style);
            }
        }
        // 导出文件
        FileOutputStream fout = new FileOutputStream(filePath);
        wb.write(fout);



    }


    public static void  writeExcel2(String fileName, List<Map<String,Object>> dataMapList, List<String> fieldList, HttpServletResponse response)throws Exception{

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");//添加sheet
        //表格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//指定单元格居中对齐

        HSSFRow row = null;
        //表头
        if(CollectionUtils.isNotEmpty(dataMapList)){
            row = sheet.createRow(0);
            for (int j = 0; j < fieldList.size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(fieldList.get(j));
                cell.setCellStyle(style);
            }

        }

        for (int i = 1; i < dataMapList.size(); i++) {
            row = sheet.createRow(i);

            Map<String,Object> dataMap = dataMapList.get(i);
            for (int j = 0; j < fieldList.size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(MapUtils.getString(dataMap,fieldList.get(j)));
                cell.setCellStyle(style);
            }
        }
        FileSystemView fsv = FileSystemView.getFileSystemView();//获取本的桌面路径
        String filePath = fsv.getHomeDirectory().toString()+"\\"+fileName;
        log.info(filePath);

        FileOutputStream fout = new FileOutputStream(filePath);
        wb.write(fout);

        //doResponseExcel(fileName,response,wb);



    }

    private static void doResponseExcel(String fileName, HttpServletResponse response, HSSFWorkbook workbook) {
        try {

            response.setHeader("Content-disposition","attachment;filename="
                    +new String(fileName.getBytes("gb2312"),"ISO8859-1"));    //设置文件头编码格式
            response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
            response.setHeader("Cache-Control","no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            log.error("输出客户端失败",e.getMessage());
        }
    }

    /**
     * xlsx
     * @param excelName
     * @throws Exception
     */
    public static void createExcel(String excelName) throws Exception {
        //创建工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建一个sheet
        XSSFSheet sheet = wb.createSheet();

        // 创建单元格样式
        XSSFCellStyle style =  wb.createCellStyle();
        style.setFillForegroundColor((short)4); //设置要添加表格北京颜色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //solid 填充
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
        style.setBorderBottom(BorderStyle.THIN); //底边框加黑
        style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
        style.setBorderRight(BorderStyle.THIN); // 有边框加黑
        style.setBorderTop(BorderStyle.THIN); //上边框加黑
        //为单元格添加背景样式
        for (int i = 0; i < 6; i++) { //需要6行表格
            Row row =	sheet.createRow(i); //创建行
            for (int j = 0; j < 6; j++) {//需要6列
                row.createCell(j).setCellStyle(style);
            }
        }

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格，cellRangAddress四个参数，第一个起始行，第二终止行，第三个起始列，第四个终止列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 5));

        //tian入数据
        XSSFRow row = sheet.getRow(0); //获取第一行
        row.getCell(1).setCellValue("2018期末考试"); //在第一行中创建一个单元格并赋值
        XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
        row1.getCell(1).setCellValue("语文");
        row1.getCell(2).setCellValue("数学");
        row1.getCell(3).setCellValue("英语");
        row1.getCell(4).setCellValue("物理");
        row1.getCell(5).setCellValue("化学");
        XSSFRow row2 = sheet.getRow(2); //获取第三行
        row2.getCell(0).setCellValue("张三");
        XSSFRow row3 = sheet.getRow(3); //获取第四行
        row3.getCell(0).setCellValue("张三");
        XSSFRow row4 = sheet.getRow(4); //获取第五行
        row4.getCell(0).setCellValue("张三");
        XSSFRow row5 = sheet.getRow(5); //获取第五行
        row5.getCell(0).setCellValue("张三");
        //将数据写入文件
        FileOutputStream out = new FileOutputStream(excelName);
        wb.write(out);
    }



    private static String ConvertCellStr(Cell cell,String cellStr){
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING :
                //String
                cellStr = cell.getStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_BOOLEAN :
                //boolean
                cellStr = String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                // 先看是否是日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 读取日期格式
                    cellStr = formatTime(cell.getDateCellValue().toString());
                } else {
                    // 读取数字
                    cellStr = formatNumberToString(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                // 读取公式
                cellStr = cell.getCellFormula().toString();
                break;
            default:
                break;
        }
        return cellStr;
    }

    private static String formatTime(String s) {
        SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = sf.parse(s);
        } catch (ParseException ex) {
            log.warn("日期转化失败：{}",ex.getMessage());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = sdf.format(date);
        return result;
    }

    private static boolean isExcel2007(String fileName){
        return fileName.matches("^.+\\.(?i)(xlsx)$");
    }

    private static String formatNumberToString(double value){
        DecimalFormat df = new DecimalFormat("0");
        return df.format(value);
    }

    public static void main(String[] args) throws IOException {

        List<String[]> list = ExcelPoiUtils.readExcel("C:\\Users\\11190\\Desktop\\测试user (2).xls");
       // List<String[]> list = re.readExcel("c:/群组.xlsx");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("第" + (i + 1) + "行");
                String[] cellList = list.get(i);
                for (int j = 0; j < cellList.length; j++) {
                    System.out.print("\t第" + (j + 1) + "列值：");
                    System.out.println(cellList[j]);
                }
            }
        }

        System.out.println("===============================================");
        try {

            ExcelPoiUtils.writeExcel("D:\\test.xls");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
