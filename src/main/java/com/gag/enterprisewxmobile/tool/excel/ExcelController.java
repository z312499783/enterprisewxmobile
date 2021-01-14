package com.gag.enterprisewxmobile.tool.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


/**
 * 导入导出模板
 */
public class ExcelController {


    /**
     * 根据一个数据的结果集和实体类的绝对路径以及文件的名称,excel导出数据
     * @param response  根据发出请求的位置以附近的形式返回下载处理
     * @param list  需要导出的数据结果集
     * @param columns  需要导出的数据的表头信息
     * @param filename  需要导出excel文件的名称
     */
    public static void export(HttpServletResponse response, List list, List<ColumnInfo> columns, String filename){
        try {
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            //通过类的路径和名称反射获取.class
            //Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass(classname);
            Sheet sheet = new Sheet(1, 0);
            //设置表头
            Table table = new Table(1);
            table.setHead(getExcelhead(columns));
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            // 第一个 sheet 名称
            sheet.setSheetName(filename);
            writer.write(list, sheet,table);
            //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
            response.setHeader("Content-disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
            writer.finish();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void export(HttpServletResponse response, List list,Class<? extends BaseRowModel> clazz, String filename){
        try {
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            //通过类的路径和名称反射获取.class
            //Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass(classname);
            Sheet sheet = new Sheet(1, 0,clazz);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            // 第一个 sheet 名称
            sheet.setSheetName(filename);
            writer.write(list, sheet);
            //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
            response.setHeader("Content-disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
            writer.finish();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据一个数据的结果集和实体类的绝对路径以及文件的名称,excel导出数据
     * @param response  根据发出请求的位置以附近的形式返回下载处理
     * @param list  需要导出的数据结果集
     * @param head  自定义表头需要导出的数据的表头信息
     * @param filename  需要导出excel文件的名称
     */
    public static void CustomHeadexport(HttpServletResponse response, List list, List <List<String>> head, String filename){
        try {
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
            //通过类的路径和名称反射获取.class
            //Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass(classname);
            Sheet sheet = new Sheet(1, 0);
            //设置表头
            Table table = new Table(1);
            table.setHead(head);
            //设置自适应宽度
            sheet.setAutoWidth(Boolean.TRUE);
            // 第一个 sheet 名称
            sheet.setSheetName(filename);
            writer.write(list, sheet,table);
            //通知浏览器以附件的形式下载处理，设置返回头要注意文件名有中文
            response.setHeader("Content-disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ) + ".xlsx");
            writer.finish();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置导出excel表头
     * @param columns
     * @return
     */
    private static List<List<String>> getExcelhead(List<ColumnInfo> columns){
        List<List<String>> head = new LinkedList<List<String>>();
        for (int i=0;i<columns.size();i++){
            List<String> head0 = new LinkedList<>();
            head0.add(columns.get(i).getColumnComment());
            head.add(head0);
        }
        return head;
    }
}