package com.yj.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yjl.excel.ReadFile;
import com.yjl.excel.ReadFileOfExcel;

public class FileUtil {
	public static final String EXCEL_TEMPLATE_NAME = "t_excel_template_";
	
	public static final String EXCEL_HEAD_NAME = "head_";
	/**
	 * 文件上传方法
	 * @param file 要上传的文件
	 * @param filePath 上传到的路径
	 * @param fileName 文件名
	 * @throws Exception
	 */
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	/**
	 * 读取excel文件后的返回值，进一步封装是因为当需要改变实现类时，只要修改这个类的这个方法，保证能正确返回结果就可以了
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readFile(String fileName, String filePath) throws Exception {
		ReadFile result = new ReadFileOfExcel();
		return result.readFile(fileName, filePath);
	}
	
	/**
	 * 生成表的字段名
	 * @param heads
	 * @return
	 */
	public static Map<String,Object> getExcelHead(String[] heads) {
    	Map<String,Object> headMap = new LinkedHashMap<String, Object>();
    	for(int i = 0; i < heads.length; i++) {
    		headMap.put(EXCEL_HEAD_NAME+i, heads[i]);
    	}
    	return headMap;
    }
	public static List<String[]> readFile(String fileName, InputStream inputStream) throws Exception {
		ReadFile result = new ReadFileOfExcel();
		return result.readFile(fileName, inputStream);
	}
}
