package com.yj.utils;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yjl.excel.Export;
import com.yjl.excel.ExportExcelOfListBean;
import com.yjl.excel.ExportExcelOfListMap;

public class ExportUtil {
	/**
	 * 导出excel
	 * @param fileName 文件名
	 * @param heads 表头
	 * @param cols  表头对应的key
	 * @param list  要导出的记录
	 * @param numerics 第几列是数字
	 * @param out   导出到的文件流(response可以获取到响应流,即下载)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void exportExcel(String fileName,String[] heads,String[] cols,List list,int[] numerics,OutputStream out) {
		Export pee = new ExportExcelOfListBean(fileName, heads, cols, list,numerics, out);
		if(list.size() > 0 && list.get(0) instanceof Map) {
			pee = new ExportExcelOfListMap(fileName, heads, cols, list,numerics, out);
		}
		pee.exportFile();
	}
	/**
	 * 导出excel时设置响应头消息
	 * @param fileName 文件
	 * @param req 要设置的请求
	 * @param resp 要设置的响应
	 */
	public static void setExportExcelResponseHeader(String fileName,HttpServletRequest req,HttpServletResponse resp) {
		 resp.setContentType("application/vnd.ms-excel");
	        String contentDisposition = "";
	        try {
	            if (req.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
	                contentDisposition = "attachment; filename=\"" + new String(fileName.getBytes("UTF-8") , "ISO8859-1") + ".xls"
	                        + "\"";// firefox浏览器
	            } else {
	                //contentDisposition = "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"";// IE浏览器
	            	contentDisposition = "attachment; filename=\"" + new String(fileName.getBytes("UTF-8") , "ISO8859-1") + ".xls"
	                        + "\"";
	            }
	        } catch (UnsupportedEncodingException e1) {
	            e1.printStackTrace();
	        }
	        resp.setHeader("Content-Disposition", contentDisposition);
	        resp.setCharacterEncoding("UTF-8");
	}

}
