package com.zhou.bot.zhoubottest.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @ClassName ExcelTest1
 * @Description 描述这个类作用
 * @Author ZhouYouMing
 * @Date 2019/7/16 12:44
 */
public class ExcelTest1 {

    public void t1() throws Exception {
        File excel = new File("d:\\我的文档\\桌面\\财务开票\\开票资料更新5月29.xlsx");
        if (excel.isFile() && excel.exists()) {
            StringBuffer sb = new StringBuffer();

            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
            Workbook wb = null;
            //根据文件后缀（xls/xlsx）进行判断
            if ("xls".equals(split[1])) {
                FileInputStream fis = new FileInputStream(excel);   //文件流对象
                wb = new HSSFWorkbook(fis);
            } else if ("xlsx".equals(split[1])) {
                wb = new XSSFWorkbook(excel);
            } else {
                System.out.println("文件类型错误!");
                return;
            }
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();
            System.out.println("firstRowIndex: " + firstRowIndex);
            System.out.println("lastRowIndex: " + lastRowIndex);
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                System.out.println("rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    sb.append("INSERT INTO FINANCE.AP_PLATFORM_FEE_INVOICE_APPLY(id,merchant_id,merchant_name,business_type,invoice_amount,invoice_date,pay_annual,status,operator_id,opt_time,operator,remark,is_delete)");
                    sb.append(" VALUES ");
                    sb.append("(finance.SEQ_AP_PLATFORM_F_I_APPLY.nextval,");
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                        Cell cell = row.getCell(cIndex);
                        if (cell != null) {
                            //System.out.println(cell.toString());
                            if(cIndex==1){
                                Integer i1 = Double.valueOf(cell.toString()).intValue();
                                sb.append(i1+",");
                            }
                            if(cIndex==2){
                                sb.append("'");
                                sb.append(cell.toString());
                                sb.append("'");
                                sb.append(",");
                            }
                            if(cIndex==3){
                                sb.append("1,");
                                Integer i2 = Double.valueOf(cell.toString()).intValue();
                                sb.append(i2+",");
                            }
                        }
                    }
                    sb.append("sysdate,2017,1,0,sysdate,'system','批量导入',0);");
                    sb.append("\n");
                }
            }
            //System.out.println(sb.toString());

            FileOutputStream fos = new FileOutputStream("d:\\我的文档\\桌面\\财务开票\\开票资料更新5月29.txt");//保存文件
            byte[] bs = sb.toString().getBytes();
            fos.write(bs);
            fos.close();

        } else {
            System.out.println("找不到指定的文件");
        }
    }

    public static void main(String[] args){
        try {
            new ExcelTest1().t1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
