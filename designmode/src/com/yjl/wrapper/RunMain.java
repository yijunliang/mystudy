package com.yjl.wrapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RunMain {

	public static void main(String[] args) {
		
		Date date = new Date();
		System.out.println(date.getYear());
		date.getDate();
		long time = date.getTime();
		System.out.println(time);
		WrapperDate date2 = new WrapperDate();
		String stringDate = date2.getStringDate();
		System.out.println(stringDate);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String format2 = format.format(date);
		String format3 = format.format(date2);
		System.out.println(format2);
		System.out.println(format3);

	}

}
