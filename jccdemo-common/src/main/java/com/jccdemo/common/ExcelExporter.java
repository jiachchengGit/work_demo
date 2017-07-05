package com.jccdemo.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guofeng on 2016/12/15.
 */
public class ExcelExporter {

    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 根据ExcelEntity等参数生成Workbook
     *
     * @param entity
     * @return
     * @throws Exception
     */
    public static <T> Workbook export2Excel(ExcelEntity<T> entity) throws Exception {
        Workbook workbook = export2Excel(entity.getHeader(), entity.getFooter(), entity.getSheetName(), entity.getColumnNames(),
                entity.getMethodNames(),entity.getEntities());
        return workbook;
    }

    /**
     * 根据给定参数导出Excel文档
     *
     * @param headerTitle 题头
     * @param sheetName
     * @param columnNames 表头名称
     * @param methodNames
     * @param entities
     * @return
     * @throws Exception
     */
    public static <T> Workbook export2Excel(String headerTitle, String footerTitle, String sheetName, String[] columnNames,
                                            String[] methodNames, List<T> entities) throws Exception {
        if (methodNames.length != columnNames.length)
            throw new IllegalArgumentException("methodNames.length should be equal to columnNames.length:"
                    + columnNames.length + " " + methodNames.length);
        Workbook newWorkBook2007 = new XSSFWorkbook();
        Sheet sheet = newWorkBook2007.createSheet(sheetName);

        //设置题头
        Header header = sheet.getHeader();
        header.setCenter(headerTitle);
        //设置脚注
        Footer footer = sheet.getFooter();
        footer.setCenter(footerTitle);

        // 创建表头
        createTableHeader(sheet, 0, columnNames);
        // 填充表内容
        createTableContent(sheet, 1, methodNames, entities);

        return newWorkBook2007;

    }

    /**
     * 创建表头
     *
     * @param sheet
     * @param index        表头开始的行数
     * @param columnNames
     */
    private static void createTableHeader(Sheet sheet, int index, String[] columnNames) {

        Row headerRow = sheet.createRow(index);
        /* 格式设置 */
        // 设置字体
        Font font = sheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
        // 设置背景色
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(font);

        for (int i = 0; i < columnNames.length; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(columnNames[i]);
        }
    }

    /**
     * 创建表格内容
     *
     * @param sheet
     * @param rowIndexBegin 表内容开始的行数
     * @param methodNames   T对象的方法名
     * @param entities
     * @throws Exception
     */
    private static <T> void createTableContent(Sheet sheet, int rowIndexBegin, String[] methodNames, List<T> entities) throws Exception {
        Class<? extends Object> clazz = null;
        if(entities == null || entities.size() <1){
            return;
        }
        clazz = entities.get(0).getClass();
        String content = null;
        for (T t : entities) {

            Row row = sheet.createRow(rowIndexBegin++);
            for (int i = 0; i < methodNames.length; i++) {
                Cell cell = row.createCell(i);
                Method method = clazz.getMethod(methodNames[i], null);
                Object object = method.invoke(t, null);
                object = object == null ? "" : object;
                if (object.getClass().equals(Date.class)) {// 对日期格式进行特殊处理
                    SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
                    content = sdf.format((Date) object);
                    cell.setCellValue(content);
                } else {
                    content = object.toString();
                    cell.setCellValue(content);
                }
//                int columnWidth = (content.getBytes().length + 2) * 256;
//                sheet.setColumnWidth(i, columnWidth);
                sheet.autoSizeColumn(i,true);
            }
        }
    }

    /**
     * 将workbook2007村委文件
     *
     * @param workbook2007
     * @param dstFile
     */
    public static void saveWorkBook2007(Workbook workbook2007, String dstFile) {
        File file = new File(dstFile);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            workbook2007.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    /**
     *  读取
     * @param fileName
     */
    public static void readExcel(String fileName) {
        Workbook workbook = getWorkbook2Read(fileName);
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 1; i < numberOfSheets; i++) {
            Sheet sheetAt = workbook.getSheetAt(i);
            int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();

            List<String> lists = new ArrayList<String>();
            for (int j = 1; j < physicalNumberOfRows; j++) {

                Row row = sheetAt.getRow(j);
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                StringBuilder sbStr = new StringBuilder();
                for (int c = 0; c < physicalNumberOfCells; c++) {
                    Cell cell = row.getCell(c);
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            String stringCellValue = cell.getStringCellValue();
                            sbStr.append("'").append(stringCellValue).append("'").append(",");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                                Date dateCellValue = cell.getDateCellValue();
                                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateCellValue);
                                sbStr.append("'").append(format).append("'").append(",");
                            } else {
                                Object inputValue = null;
                                double numericCellValue = cell.getNumericCellValue();
                                long longVal = Math.round(numericCellValue);
                                if (Double.parseDouble(longVal + ".0") == numericCellValue) {
                                    inputValue = longVal;
                                } else {
                                    inputValue = numericCellValue;
                                }
                                sbStr.append("'").append(inputValue).append("'").append(",");
                            }
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //表达式
//                            cell.getCellFormula();
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            //空值
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            //boolean值
//                            cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            //错值
//                            cell.getErrorCellValue();
                            break;
                        default:
                            break;
                    }
                }
                lists.add(sbStr.toString().substring(0, sbStr.length()-1));
            }
            printExcelRowValue(lists);
        }
    }

    private static void printExcelRowValue(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    private static Workbook getWorkbook2Read(String fileName) {
        Workbook wb = null;
        InputStream inputStream = getFileInputStream(fileName);
        try {
            if (fileName.endsWith("xlsx")) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                wb = new HSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    private static FileInputStream getFileInputStream(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 测试方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /**
        // 准备数据
            List<Test> winds = new ArrayList<Test>();// Wind有三个方法:getLocation、getSpeed、getTimestamp
            for (int i = 0; i < 10; i++) {
                Test wind = new Test();
                wind.setAreaCode("028");
                wind.setActivateTime(new Date());
                wind.setDeadlineTime(new Date());
                wind.setAmount(""+10000);
                wind.setRechargeId(""+123+i);
                wind.setRechargeStatus(1);
                winds.add(wind);
            }
        String[] columnNames = {"城市编码", "开卡时间", "截止时间","金额","卡号","状态"};
        String[] methodNames = {"getAreaCode", "getActivateTime", "getDeadlineTime","getAmount","getRechargeId","getRechargeStatus"};
        String fileName = "/Users/lgfcxx/Desktop/excel1.xlsx";
        // 生成ExcelEntity实体，包含4个必备参数
        ExcelEntity<Test> excelEntity = new ExcelEntity<Test>(fileName, columnNames, methodNames, winds);
        //excelEntity.setHeader("题头");
        //excelEntity.setFooter("脚注");
        Workbook excel = ExcelExporter.export2Excel(excelEntity);
        //ExcelExporter.export2Excel("题头","脚注", "sheet1", columnNames, methodNames, winds);//也可以这样调用,无需新建ExcelEntity对象
        //将Workbook存为文件
        ExcelExporter.saveWorkBook2007(excel, excelEntity.getFileName());

        System.out.println("导出完成！");

         */

    }

}
