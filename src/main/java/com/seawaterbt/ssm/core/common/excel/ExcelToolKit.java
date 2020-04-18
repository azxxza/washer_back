package com.seawaterbt.ssm.core.common.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelToolKit {

    /**
     * 导入工具类
     * file Excel :上传过来的文件
     * columnNames :为Excel 实体类属性集合一一对应
     * 比如我的实体类有 id,name,age,address
     * 那么这个集合就是List<String> list =new ArrayList<>(){id,name,age,address}
     */
    public static Map<String, Object> uploadExcel(MultipartFile file, List<String> columnNames) {
        List<Line> lines = new LinkedList<>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int state = 1;
        String msg = "读取成功！";
        if (file.isEmpty()) {
            state = 0;
            msg = "文件为空！";
        }
        try {
            //根据路径获取这个操作excel的实例
            Workbook wb = WorkbookFactory.create(file.getInputStream());
            //根据页面index 获取sheet页
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            //循环sheet页中数据从第二行开始，第一行是标题
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Line line = new Line();
                row = sheet.getRow(i);
                Map<String, String> clbo = new HashMap<>();
                for (int j = 0; j < columnNames.size(); j++) {
                    String key = columnNames.get(j);
                    if (row.getCell(j) != null) {
//                        CellType cellType = row.getCell(j).getCellType();
//                        row.getCell(j).setCellType(CellType.STRING);
//                        String value = null;
//                        if (CellType.NUMERIC.equals(cellType)) {
//                            if ("General".equals(row.getCell(j).getCellStyle().getDataFormatString())) {
//                                value = String.valueOf(row.getCell(j).getNumericCellValue());
//                            } else if ("m/d/yy".equals(row.getCell(j).getCellStyle().getDataFormatString())) {
//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                value = sdf.format(row.getCell(j).getDateCellValue());
//                            } else {
//                                value = String.valueOf(row.getCell(j).getNumericCellValue());
//                            }
//                            clbo.put(key, value);
//                        } else {
                            row.getCell(j).setCellType(CellType.STRING);//设置成String
                            clbo.put(key, row.getCell(j).getStringCellValue());
//                        }
                    } else {
                        clbo.put(key, "");
                    }
                }
                line.setColumns(clbo);
                lines.add(line);
            }
        } catch (Exception e) {
            //log.error(e.getMessage());
            e.printStackTrace();
            state = 0;
            msg = "读取失败，请稍后再试！";
        }
        resultMap.put("state", state);
        resultMap.put("msg", msg);
        resultMap.put("lines", lines);
        return resultMap;
    }

    /**
     * 导出工具类
     *
     * @param response
     * @param lines    跟导入进来,返回出来的对象一致,行集合
     * @param titles   为Excel 实体类属性集合一一对应的意思
     *                 比如我的实体类有 id,name,age,address
     *                 那么这个集合就是List<String> list =new ArrayList<>(){"id","名称","年龄","地址"}
     * @param title    为标题
     * @throws Exception
     */
    public static void excelExport(HttpServletResponse response,
                                   List<Line> lines, List<String> titles, String title) throws Exception {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title, "utf-8"));

        exportExcel(titles, lines, response, title);
    }

    public static void exportExcel(List<String> titles, List<Line> lines,
                                   HttpServletResponse response, String title) {
        // 创建Excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表sheet 默认是表名是sheet0
        HSSFSheet sheet = workbook.createSheet();
        // 创建表的第一行
        HSSFRow row = sheet.createRow(0);
        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeightInPoints(20);
        // 创建第0行 也就是标题
        row.setHeightInPoints(30);// 设备标题的高度
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titles.size()));

        // 创建单元格
        HSSFCell cell = null;
        cell = row.createCell(0);
        cell.setCellStyle(titleStyle(workbook));
        cell.setCellValue(title);

        HSSFRow row1 = sheet.createRow(1);
        // 循环为第一行插入标题
        for (int j = 0; j < titles.size(); j++) {
            cell = row1.createCell(j);
            cell.setCellStyle(style(workbook));
            cell.setCellValue(titles.get(j));
        }
        // 追加数据 1是第二行
        for (int i = 2; i <= lines.size(); i++) {
            Line line = lines.get(i - 1);
            Map<String, String> columns = line.getColumns();
            HSSFRow nextrow = sheet.createRow(i);
            int j = 0;
            for (Map.Entry<String, String> entry : columns.entrySet()) {
                HSSFCell cell2 = nextrow.createCell(j);
                cell2.setCellStyle(style1(workbook));
                cell2.setCellValue(entry.getValue());
                j++;
            }
        }
        ServletOutputStream out;
        try {
            //.xls 是2003版本，excel2003、2007、2010都可以打开，兼容性最好
            response.setHeader("Content-Disposition", "attachment;fileName=" + title + ".xls");
            response.setContentType("APPLICATION/OCTET-STREAM");
            out = response.getOutputStream();
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 标题第一行样式
    public static HSSFCellStyle titleStyle(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBottomBorderColor((short) 8);
        style.setBorderBottom(BorderStyle.THIN); // HSSFCellStyle.BORDER_THIN
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 15); // 设置字体大小
        style.setFont(headerFont); // 为标题样式设置字体样式
        return style;
    }

    // 第二行样式
    public static HSSFCellStyle style(HSSFWorkbook wb) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBottomBorderColor((short) 8);
        style.setBorderBottom(BorderStyle.THIN); // HSSFCellStyle.BORDER_THIN
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 12); // 设置字体大小
        style.setFont(headerFont); // 为标题样式设置字体样式
        return style;
    }

    // 数据行样式
    public static HSSFCellStyle style1(HSSFWorkbook wb) {
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setWrapText(true);// 设置自动换行
        style1.setAlignment(HorizontalAlignment.CENTER);    //HSSFCellStyle.ALIGN_CENTER
        style1.setVerticalAlignment(VerticalAlignment.CENTER); // HSSFCellStyle.VERTICAL_CENTER // 创建一个居中格式
        style1.setBottomBorderColor((short) 8);
        style1.setBorderBottom(BorderStyle.THIN); // HSSFCellStyle.BORDER_THIN
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setBorderTop(BorderStyle.THIN);
        HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont1.setFontName("黑体"); // 设置字体类型
        headerFont1.setFontHeightInPoints((short) 10); // 设置字体大小
        style1.setFont(headerFont1); // 为标题样式设置字体样式
        return style1;
    }
}
