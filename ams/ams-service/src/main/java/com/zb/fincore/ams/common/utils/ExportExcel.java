package com.zb.fincore.ams.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！ 
 *  
 * @author leno 
 * @version v1.0 
 * @param <T> 
 *            应用泛型，代表任意一个符合javabean风格的类 
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx() 
 *            byte[]表jpg格式的图片数据 
 */  
public class ExportExcel<T>  {
	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");
    public void exportExcel(Collection<T> dataset, OutputStream out)  
    {  
        exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd HH:mm:ss");  
    }  
  
    public void exportExcel(String sheetname,String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
        exportExcel(sheetname, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");  
    }  
    
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out)  
    {  
    	String sheetname="zjp2p";
    	exportExcel(sheetname, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");   
    }  
  
    public void exportExcel(String[] headers, Collection<T> dataset,  
            OutputStream out, String pattern)  
    {  
        exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);  
    }  
  
    /** 
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上 
     *  
     * @param title 
     *            表格标题名 
     * @param headers 
     *            表格属性列名数组 
     * @param dataset 
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据) 
     * @param out 
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
     * @param pattern 
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd" 
     */  
    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })  
    public void exportExcel(String title, String[] headers,  
            Collection<T> dataset, OutputStream out, String pattern)  
    {  
    	// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节  
        sheet.setDefaultColumnWidth(18);
        
        
        // 1设置标题样式
//		HSSFCellStyle titleStyle = workbook.createCellStyle(); // 创建样式对象
//		titleStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  //下边框
//		titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 底部边框颜色
//		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  //左边框
//		titleStyle.setLeftBorderColor(IndexedColors.RED.getIndex()); // 左边边框颜色
//		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  //右边框
//		titleStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());  // 右边边框颜色
//		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  //上边框
//		titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());  // 上边边框颜色
//		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION); // 水平居中
//		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
//		// 设置字体
//		HSSFFont titleFont = workbook.createFont(); // 创建字体对象
//		titleFont.setFontHeightInPoints((short) 15); // 设置字体大小
//		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 设置粗体
//		titleFont.setFontName("黑体"); // 设置为黑体字
//		titleStyle.setFont(titleFont);
//		// 合并单元格操作
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 5));
//		HSSFRow row = null;
//		HSSFCell cell = null;
//		row = sheet.createRow(0);
//		cell = row.createCell(0);
//		cell.setCellStyle(titleStyle);
//		cell.setCellValue(new HSSFRichTextString(title));
        
        
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        //style.setFillForegroundColor(HSSFColor.BLACK.SKY_BLUE.index);  
        style.setFillForegroundColor(HSSFColor.WHITE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        //style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillForegroundColor(HSSFColor.WHITE.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        //非数字字体样式
        HSSFFont font3 = workbook.createFont();  
        font3.setColor(HSSFColor.BLUE.index);
        
        // 声明一个画图的顶级管理器  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
//        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
//                0, 0, 0, (short) 4, 2, (short) 6, 5));
//        // 设置注释内容
//        comment.setString(new HSSFRichTextString("企业理财"));
//        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//        comment.setAuthor("kaiyun");
  
        // 产生表格标题行  
//        HSSFRow row = sheet.createRow(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++)  
        {  
//            HSSFCell cell = row.createCell(i);  
        	cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
        }  
  
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();  
        int index = 0;  
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);
            T t = (T) it.next();  
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
            Field[] fields = t.getClass().getDeclaredFields();  
            for (int i = 0; i < headers.length; i++)  
            {  
//                HSSFCell cell = row.createCell(i);  
            	cell = row.createCell(i);
                cell.setCellStyle(style2);  
                Field field = fields[i];  
                String fieldName = field.getName();  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                try  
                {  
                    Class tCls = t.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[]  
                            {});  
                    Object value = getMethod.invoke(t, new Object[]  
                    {});  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                    if(value == null){
                    	continue;
                    }
                     if (value instanceof Integer) {  
                     int intValue = (Integer) value;  
                     cell.setCellValue(intValue);  
                     } else if (value instanceof Float) {  
                     float fValue = (Float) value;   
                     cell.setCellValue(fValue);  
                     } else if (value instanceof Double) {  
                     double dValue = (Double) value;  
                     cell.setCellValue(dValue);  
                     } else if (value instanceof Long) {  
                     long longValue = (Long) value;  
                     cell.setCellValue(longValue);  
                     }  
                    if (value instanceof Boolean)  
                    {  
                        boolean bValue = (Boolean) value;  
                        textValue = "男";  
                        if (!bValue)  
                        {  
                            textValue = "女";  
                        }  
                    }  
                    else if (value instanceof Date)  
                    {  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
                        textValue = sdf.format(date);  
                    }  
                    else if (value instanceof byte[])  
                    {  
                        // 有图片时，设置行高为60px;  
                        row.setHeightInPoints(60);  
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算  
                        sheet.setColumnWidth(i, (short) (35.7 * 80));  
                        // sheet.autoSizeColumn(i);  
                        byte[] bsValue = (byte[]) value;  
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,  
                                1023, 255, (short) 6, index, (short) 6, index);  
                        anchor.setAnchorType(2);  
                        patriarch.createPicture(anchor, workbook.addPicture(  
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));  
                    }  
                    else  
                    {  
                        // 其它数据类型都当作字符串简单处理  
                        textValue = value.toString();  
                    }  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)  
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }  
                        else  
                        {  
                            HSSFRichTextString richString = new HSSFRichTextString(  
                                    textValue);    
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                        }  
                    }  
                }  
                catch (SecurityException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (NoSuchMethodException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalArgumentException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalAccessException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (InvocationTargetException e)  
                {  
                    e.printStackTrace();  
                }  
                finally  
                {  
                    // 清理资源  
                }  
            }  
        }  
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public void appendExcel(
            Collection<T> dataset, OutputStream out, String pattern,Integer index)  
    {  
    	String title="zb report ";
    	// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节  
        //sheet.setDefaultColumnWidth(15);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.VIOLET.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
        // 生成并设置另一个样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样式  
        style2.setFont(font2);  
  
        //非数字字体样式
        HSSFFont font3 = workbook.createFont();  
        font3.setColor(HSSFColor.BLUE.index);
        
        // 声明一个画图的顶级管理器  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        // 定义注释的大小和位置,详见文档  
//        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,  
//                0, 0, 0, (short) 4, 2, (short) 6, 5));  
//        // 设置注释内容  
//        comment.setString(new HSSFRichTextString(" zjp2p 金融"));  
//        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.  
//        comment.setAuthor("leno");  
  
        // 产生表格标题行  
        /*HSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < headers.length; i++)  
        {  
            HSSFCell cell = row.createCell(i);  
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);  
            cell.setCellValue(text);  
        }  */
  
        // 遍历集合数据，产生数据行  
        Iterator<T> it = dataset.iterator();   
        HSSFRow row = null;  
        while (it.hasNext())  
        {  
            index++;  
            row = sheet.createRow(index);  
            T t = (T) it.next();  
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  
            Field[] fields = t.getClass().getDeclaredFields();  
            for (int i = 0; i < fields.length; i++)  
            {  
                HSSFCell cell = row.createCell(i);  
                cell.setCellStyle(style2);  
                Field field = fields[i];  
                String fieldName = field.getName();  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
                try  
                {  
                    Class tCls = t.getClass();  
                    Method getMethod = tCls.getMethod(getMethodName,  
                            new Class[]  
                            {});  
                    Object value = getMethod.invoke(t, new Object[]  
                    {});  
                    // 判断值的类型后进行强制类型转换  
                    String textValue = null;  
                    if(value == null){
                    	continue;
                    }
                     if (value instanceof Integer) {  
                     int intValue = (Integer) value;  
                     cell.setCellValue(intValue);  
                     } else if (value instanceof Float) {  
                     float fValue = (Float) value;   
                     cell.setCellValue(fValue);  
                     } else if (value instanceof Double) {  
                     double dValue = (Double) value;  
                     cell.setCellValue(dValue);  
                     } else if (value instanceof Long) {  
                     long longValue = (Long) value;  
                     cell.setCellValue(longValue);  
                     }  
                    if (value instanceof Boolean)  
                    {  
                        boolean bValue = (Boolean) value;  
                        textValue = "男";  
                        if (!bValue)  
                        {  
                            textValue = "女";  
                        }  
                    }  
                    else if (value instanceof Date)  
                    {  
                        Date date = (Date) value;  
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
                        textValue = sdf.format(date);  
                    }  
                    else if (value instanceof byte[])  
                    {  
                        // 有图片时，设置行高为60px;  
                        row.setHeightInPoints(60);  
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算  
                        sheet.setColumnWidth(i, (short) (35.7 * 80));  
                        // sheet.autoSizeColumn(i);  
                        byte[] bsValue = (byte[]) value;  
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,  
                                1023, 255, (short) 6, index, (short) 6, index);  
                        anchor.setAnchorType(2);  
                        patriarch.createPicture(anchor, workbook.addPicture(  
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));  
                    }  
                    else  
                    {  
                        // 其它数据类型都当作字符串简单处理  
                        textValue = value.toString();  
                    }  
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  
                    if (textValue != null)  
                    {  
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                        Matcher matcher = p.matcher(textValue);  
                        if (matcher.matches())  
                        {  
                            // 是数字当作double处理  
                            cell.setCellValue(Double.parseDouble(textValue));  
                        }  
                        else  
                        {  
                            HSSFRichTextString richString = new HSSFRichTextString(  
                                    textValue);    
                            richString.applyFont(font3);  
                            cell.setCellValue(richString);  
                        }  
                    }  
                }  
                catch (SecurityException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (NoSuchMethodException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalArgumentException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (IllegalAccessException e)  
                {  
                    e.printStackTrace();  
                }  
                catch (InvocationTargetException e)  
                {  
                    e.printStackTrace();  
                }  
                finally  
                {  
                    // 清理资源  
                }  
            }  
        }  
        try  
        {  
            workbook.write(out);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
    
 
	public void exportExcel(Collection<T> dataset,String path,String headers[]){ 
        try  
        {    
  
            OutputStream out = new FileOutputStream(path);    
            exportExcel("提现",headers, dataset, out); 
            out.close();
			System.out.println("excel导出成功！");
        }  
        catch (FileNotFoundException e){  
            e.printStackTrace();  
        }  
        catch (IOException e)  {  
            e.printStackTrace();  
        }  
	}
	
	public void appendExcel(Collection<T> dataset,String path,Integer index){ 
        try  
        {    
  
            OutputStream out = new FileOutputStream(path);    
            appendExcel( dataset, out,  "yyyy-MM-dd HH:mm:ss",index); 
            out.close();
			System.out.println("excel导出成功！");
        }  
        catch (FileNotFoundException e){  
            e.printStackTrace();  
        }  
        catch (IOException e)  {  
            e.printStackTrace();  
        }  
	}
    
//	public static void download(String path, HttpServletResponse response) {
//		try {
//			// path是指欲下载的文件的路径。
//			File file = new File(path);
//			// 取得文件名。
//			String filename = file.getName();
//			// 以流的形式下载文件。
//			InputStream fis = new BufferedInputStream(new FileInputStream(path));
//			byte[] buffer = new byte[fis.available()];
//			fis.read(buffer);
//			fis.close();
//			// 清空response
//			response.reset();
//			// 设置response的Header
//			response.addHeader("Content-Disposition", "attachment;filename="
//					+ new String(filename.getBytes()));
//			response.addHeader("Content-Length", "" + file.length());
//			OutputStream toClient = new BufferedOutputStream(
//					response.getOutputStream());
//			response.setContentType("application/vnd.ms-excel;charset=gb2312");
//			toClient.write(buffer);
//			toClient.flush();
//			toClient.close();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}

	
}  