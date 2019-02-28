package com.yjl.wrapper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
/**
 * 日期包装类，扩展日期类的功能
 * @author Administrator
 *
 */
public class WrapperDate extends Date {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Object clone() {
		return super.clone();
	}

	@Override
	public int getYear() {
		return super.getYear();
	}

	@Override
	public void setYear(int year) {
		super.setYear(year);
	}

	@Override
	public int getMonth() {
		return super.getMonth();
	}

	@Override
	public void setMonth(int month) {
		super.setMonth(month);
	}

	@Override
	public int getDate() {
		// TODO Auto-generated method stub
		return super.getDate();
	}

	@Override
	public void setDate(int date) {
		// TODO Auto-generated method stub
		super.setDate(date);
	}

	@Override
	public int getDay() {
		return super.getDay();
	}

	@Override
	public int getHours() {
		return super.getHours();
	}

	@Override
	public void setHours(int hours) {
		super.setHours(hours);
	}

	@Override
	public int getMinutes() {
		return super.getMinutes();
	}

	@Override
	public void setMinutes(int minutes) {
		super.setMinutes(minutes);
	}

	@Override
	public int getSeconds() {
		return super.getSeconds();
	}

	@Override
	public void setSeconds(int seconds) {
		// TODO Auto-generated method stub
		super.setSeconds(seconds);
	}

	@Override
	public long getTime() {
		return super.getTime();
	}

	@Override
	public void setTime(long time) {
		// TODO Auto-generated method stub
		super.setTime(time);
	}

	@Override
	public boolean before(Date when) {
		return super.before(when);
	}

	@Override
	public boolean after(Date when) {
		return super.after(when);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int compareTo(Date anotherDate) {
		return super.compareTo(anotherDate);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public String toLocaleString() {
		return super.toLocaleString();
	}

	@Override
	public String toGMTString() {
		return super.toGMTString();
	}

	@Override
	public int getTimezoneOffset() {
		return super.getTimezoneOffset();
	}

	@Override
	public Instant toInstant() {
		return super.toInstant();
	}
	
	public String getStringDate() {
		String format = dateFormat.format(this);
		return format;
	}
	
	

}
