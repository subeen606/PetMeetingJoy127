package com.petmeeting.joy.playboard.Util;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
	
	public static Date toDate(int year, int month, int day){
		String s = year + "-" + two(month+"") + "-" + two(day+"");
		Date d = Date.valueOf(s);
		return d;
	}
	public static String two(String msg){
		return msg.trim().length()<2?"0"+msg:msg.trim();
	}
	//연월일만으로 대소 비교
	public static boolean isEnd(java.util.Date mydate){
		Calendar c=Calendar.getInstance();
		c.setTime(mydate);
		Calendar t=Calendar.getInstance();
		//오늘이 마지막날 보다 큰가?
		return Integer.parseInt(StringCal(t))>Integer.parseInt(StringCal(c));
	}
	//칼렌더를 20120807형식으로 만들기
	public static String StringCal(Calendar dd){
		String s=dd.get(Calendar.YEAR)+""+
		two((dd.get(Calendar.MONTH)+1)+"")+""+
		two(dd.get(Calendar.DATE)+"");
		return s;
	}
	public static String DateString(Date myDate) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		
		String[] sWeek = {"(일)", "(월)", "(화)", "(수)", "(목)", "(금)", "(토)"};
		
		String str = year +"년 " + month + "월 " + day + "일 " + sWeek[week];
		return str;
	}
}
