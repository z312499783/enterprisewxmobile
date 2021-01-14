package com.gag.enterprisewxmobile.tool.common.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ExcelCustomCommon {

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbookType(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(".xls".equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(".xlsx".equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("导入格式错误");
        }
        return wb;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell 单元格
     * @return
     */
    public String getCellFormatValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            switch (cell.getCellType()) { // 判断当前Cell的Type
                case HSSFCell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                    cellvalue = String.valueOf(cell.getNumericCellValue()).trim();
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                    }
                    else { // 如果是纯数字
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case HSSFCell.CELL_TYPE_STRING:  // 如果当前Cell的Type为STRIN
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:  // 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * excel导入单元格填写数字默认是double类型,判断是否有.0,如果有则去掉
     * @param str java类型为string
     * @return
     */
    public String StrIfTransformInt(String str) {
        if (NumberUtils.isNumber(str)){
            if (str.endsWith(".0")){
                String sub = str.substring(0,str.indexOf(".0"));
                str = String.valueOf(Integer.valueOf(sub));
            }else{
                str = new BigDecimal(str.toString()).toPlainString();
            }
        }
        return str;
    }

    /**
     *  excel导入单元格填写数字默认是double类型,判断是否存在.,如果有则去掉
     * @param str java类型为int
     * @return
     */
    public int ifExistSuffix(String str){
        String substring = "0";
        if (str.contains(".")){
            substring = str.substring(0,str.indexOf("."));
        }else { substring = str; }
        return Integer.parseInt(substring);
    }

    /**
     * excel导入单元格填写日期默认是yyyy/MM/dd,poi拿到日期自动转换为dd-中文月份-yyyy,java拿到后自动转换4000多的值,4000多的值是距离1900-01-01离参数day天
     * @param str
     * @return
     */
    public Date TransformDate(String str) throws ParseException {
        if (NumberUtils.isNumber(str)){
            String s = StrIfTransformInt(str);
            Calendar c = new GregorianCalendar(1900,0,-1);
            Date date= DateUtils.addDays(c.getTime(), Integer.parseInt(s));
            return date;
        }else {
            Date date = com.gag.enterprisewxmobile.tool.common.utils.DateUtils.dateTime("yyyy-MM-dd", str);
            return date;
        }

    }
}
