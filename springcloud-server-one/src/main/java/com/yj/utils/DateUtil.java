package com.yj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	public static final String DATE_TYPE_DAY = "DAY";
	public static final String DATE_TYPE_MONTH = "MONTH";
	public static final String DATE_TYPE_YEAR = "YEAR";
	public static final String DATE_FORMAT_Y_M = "yyyy-MM";
	public static final String DATE_FORMAT_YM = "yyyyMM";
	public static final String DATE_FORMAT_Y_M_D = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YMD = "yyyyMMdd";
	public static final String DATE_FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd hh:mm:ss";
	public static final String DATE_FORMAT_YMDHMS = "yyyyMMddHHmmss";

	/**
	 * 返回日期格式：yyyyMMddHHmmss
	 * 
	 * @return
	 */
	@Deprecated
	public static String getDateForm1(Date date) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			return df.format(date);
		}
		return null;
	}

	/**
	 * 返回日期格式：yyyy-MM-dd
	 */
	@Deprecated
	public static String getDateForm2(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = dateFormat.format(date);
			return sdate;
		}
		return null;
	}
	/**
	 * 返回格式为 yyyy-MM-dd 的日期字符串
	 * @param date
	 * @return
	 */
	public static String getStringDateY_M_D(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = dateFormat.format(date);
			return sdate;
		}
		return null;
	}
	/**
	 * 返回给定日期前N天或后几天的日期
	 * 
	 * @throws ParseException
	 */
	public static String getBeforeDateN(String date, int n)
			throws ParseException {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today;
			String bday = "";
			today = dateFormat.parse(date);
			Calendar r = Calendar.getInstance();
			r.setTime(today);
			r.add(Calendar.DATE, n);
			bday = dateFormat.format(r.getTime());

			return bday;
		}
		return null;
	}

	/**
	 * 返回给定日期所在周的周一的日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekFirstDay(String date) throws ParseException {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today;
			String bday = "";
			today = dateFormat.parse(date);
			Calendar r = Calendar.getInstance(Locale.CHINA);
			// 默认一周开始日期是周日，按照中国习惯，将一周开始时间设置为周一
			r.setFirstDayOfWeek(Calendar.MONDAY);
			r.setTime(today);
			// ---周一的时间
			r.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			bday = dateFormat.format(r.getTime());

			return bday;
		}
		return null;
	}
	
	public static Date formatDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (dateStr != null && !"".equals(dateStr)) {
			try {
				date = format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	public static Date formatDate(String dateStr, String formatType) {
		SimpleDateFormat format = new SimpleDateFormat(formatType);
		Date date = null;
		if (dateStr != null && !"".equals(dateStr)) {
			try {
				date = format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	/**
	 * 获取yyyy-MM-dd格式的日期字符串的年份
	 * @param date
	 * @return
	 */
	public static Integer getYearByStringDate(String date) {
		String queryDate = getStringDateByFormat(date,DATE_FORMAT_Y_M_D);
		return Integer.parseInt(queryDate.substring(0, 4));
	}
	/**
	 * 获取yyyy-MM-dd格式的日期字符串的月份
	 * @param date
	 * @return
	 */
	public static Integer getMonthByStringDate(String date) {
		String queryDate = getStringDateByFormat(date,DATE_FORMAT_Y_M_D);
		return Integer.parseInt(queryDate.substring(5, 7));
	}
	/**
	 * 获取yyyy-MM-dd格式的日期字符串的天
	 * @param date
	 * @return
	 */
	public static Integer getDayByStringDate(String date) {
		String queryDate = getStringDateByFormat(date,DATE_FORMAT_Y_M_D);
		return Integer.parseInt(queryDate.substring(8, 10));
	}
	
	/**
	 * 把字符串日期转化为指定格式的日期字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStringDateByFormat(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date parse = sdf.parse(date);
			return sdf.format(parse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 把字符串转化为指定格式日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDateByFormat(String date, String format) {
		return formatDate(date, format);
	}
	/**
	 * 通过指定年、月、日获取字符串格式的日期
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String getStringDateY_M_D(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date time = calendar.getTime();
		return getStringDateY_M_D(time);
	}
	/**
	 * 获取当前年的当前月份的第一天
	 * @return
	 */
	public static String getFirstDayOfMonth(String queryDate){
		int year = getYearByStringDate(getStringDateByFormat(queryDate,DATE_FORMAT_Y_M_D));
		int month = getMonthByStringDate(getStringDateByFormat(queryDate,DATE_FORMAT_Y_M_D));
		return getStringDateY_M_D(year, month, 1);
	}
	/**
	 * 获取当前年的当前月份的最后一天
	 * @return
	 */
	public static String getLastDayOfMonth(String queryDate){
		int year = getYearByStringDate(getStringDateByFormat(queryDate,DATE_FORMAT_Y_M_D));
		int month = getMonthByStringDate(getStringDateByFormat(queryDate,DATE_FORMAT_Y_M_D));
		//月份是从0开始的
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,1);
		//当前年月的月份最大天数
		int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return getStringDateY_M_D(year, month, actualMaximum);
	}
	/**
	 * 指定操作类型，对日期进行增加或减少
	 * @param date 要操作的日期
	 * @param type 要操作的日期类型(年月日)
	 * @param operatorValue 增加或减少的数值
	 * @return
	 */
	public static String getStringDateY_M_D(String date, String type, int operatorValue) {
		int year = getYearByStringDate(date);
		int month = getMonthByStringDate(date);
		int day = getDayByStringDate(date);
		if(DATE_TYPE_DAY.equals(type)) {
			return getStringDateY_M_D(year, month, day + operatorValue);
		}
		if(DATE_TYPE_MONTH.equals(type)) {
			return getStringDateY_M_D(year, month + operatorValue, day);
		}
		if(DATE_TYPE_YEAR.equals(type)) {
			return getStringDateY_M_D(year + operatorValue, month, day);
		}
		return null;
	}
	public static void main(String[] args){
		String queryDate = "2018-4-7";
		String stringDateY_M_D = getStringDateY_M_D(queryDate,DATE_TYPE_MONTH,-1);
		System.out.println(stringDateY_M_D);
		System.out.println(getFirstDayOfMonth(queryDate));
	}
}
