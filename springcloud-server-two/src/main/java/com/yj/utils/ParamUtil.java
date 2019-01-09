package com.yj.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 公用的参数处理方法
 * @author Administrator
 *
 */
public class ParamUtil {
	/**
	 * 把字符串转化为整型
	 * @param value
	 * @return
	 */
	public static Integer string2Integer(String value) {
		if(!isEmpty(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}
	/**
	 * 判断字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if(null == value || "".equals(value)) {
			return true;
		}
		return false;
	}
	/**
	 * 判断Integer是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Integer value) {
		if(null == value) {
			return true;
		}
		return false;
	}
	
	public static Integer getPageStart(int pageSize, int pageNumber) {
		return (pageNumber-1)*pageSize;
	}
	/**
	 * 把参数封装为分页参数（没有传分页参数，默认每页显示2147483647条记录）
	 *@param param
	 */
	public static Map<String, Object> wrapupParamForPaging(Map<String, Object> paramMap) {
        int pageSize = parseToInteger(paramMap.get("pageSize"),2147483647); //每页显示的记录数
        int pageNumber = parseToInteger(paramMap.get("pageNumber"),1);//当前第几页
        int start = getPageStart(pageSize, pageNumber);  //当前起始页
        Map<String, Object> wrapupMap = paramMap;
        wrapupMap.put("start",start);
        wrapupMap.put("limit",pageSize);
        return wrapupMap;//不要直接操作形参，然后返回操作后的形参(存在线程安全问题)
	}
	/**
	 * 把字符串按某个字符分割为字符串数组
	 * @param paramMap
	 * @param key
	 * @param splitValue
	 * @return
	 */
	public static String[]  stringToArray(Map<String, Object> paramMap, String key, String splitValue) {
		String str = paramMap.get(key).toString();
		String[] splitArray = str.split(splitValue);
		return splitArray;
	}
	
	public static String parseToString(Object obj){
		String rs = null;
		if(obj != null){
			rs = obj.toString();
		}
		if(!"".equals(rs)){
			return rs;
		}
		return null;
	}
	/**
	 * 把对象转换为字符串，如果对象为null则使用默认值
	 * @param obj 要转换为字符串的对象
	 * @param defaultValue 默认字符串
	 * @return
	 */
	public static String parseToString(Object obj, String defaultValue){
		String result = parseToString(obj);
		if(result == null) {
			return defaultValue;
		}
		return result;
	}
	/**
	 * 通过名称从参数map中获取值
	 * @param params 
	 * @param name  
	 * @return String
	 */
	public static String getStringParamByName(Map<String,Object> params, String name) {
		return parseToString(params.get(name));
	}
	/**
	 * 通过名称从参数map中获取值，值为null时使用默认值
	 * @param params 
	 * @param name 
	 * @param defaultValue 默认值
	 * @return String 
	 */
	public static String getStringParamByName(Map<String,Object> params, String name, String defaultValue) {
		return parseToString(params.get(name), defaultValue);
	}
	/**
	 * 从Map<String,Object>集合中取出List<Object>数据(封装向下转型)
	 * @param params Map<String,Object>
	 * @param name key
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getListParamByName(Map<String,Object> params, String name) {
		return (List<Object>) params.get(name);
	}
	
	public static Integer parseToInteger(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return Integer.valueOf(rs);
		}
		return null;
	}
	
	public static int parseToInteger(Object obj, int defaultValue){
		String rs = parseToString(obj);
		if(rs != null){
			return Integer.valueOf(rs);
		}
		return defaultValue;
	}
	
	/**
	 * 通过名称从参数map中获取值
	 * @param params 
	 * @param name  
	 * @return Integer
	 */
	public static Integer getIntegerParamByName(Map<String,Object> params, String name) {
		return parseToInteger(params.get(name));
	}
	/**
	 * 通过名称从参数map中获取值
	 * @param params 
	 * @param name 
	 * @param defaultValue 
	 * @return int
	 */
	public static int getIntegerParamByName(Map<String,Object> params, String name, int defaultValue) {
		return parseToInteger(params.get(name),defaultValue);
	}
	
	public static Short parseToShort(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return Short.valueOf(rs);
		}
		return null;
	}
	
	public static Long parseToLong(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return Long.valueOf(rs);
		}
		return null;
	}
	
	public static Float parseToFloat(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return Float.valueOf(rs);
		}
		return null;
	}
	
	public static Double parseToDouble(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return Double.valueOf(rs);
		}
		return null;
	}
	
	public static BigDecimal parseToBigDecimal(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return new BigDecimal(rs);
		}
		return null;
	}
	
	public static Boolean parseToBoolean(Object obj){
		String rs = parseToString(obj);
		if(rs != null){
			return Boolean.valueOf(rs);
		}
		return null;
	}
	
	public static Date parseToDate(Object obj) throws ParseException{
		String rs = parseToString(obj);
		if(rs != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
			return dateFormat.parse(rs);
		}
		return null;
	}
	
	public static Date parseToDateTime(Object obj) throws ParseException{
		String rs = parseToString(obj);
		if(rs != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			return dateFormat.parse(rs);
		}
		return null;
	}
	
	/**
	 * 验证参数是否为空
	 * @param args
	 * @return
	 */
	public static boolean containsNull(Object... args) {
		if (args != null) {
			for (Object obj : args) {
				if (null == obj) {
					return true;
				}
			}
		}
		return false;
	}

}
