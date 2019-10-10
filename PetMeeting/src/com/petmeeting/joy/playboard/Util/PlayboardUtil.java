package com.petmeeting.joy.playboard.Util;

import java.util.Calendar;
import java.util.Date;


public class PlayboardUtil {
	
	private String title;
	
	private String category;
	
	private Date myDate;
	
	private String location;
	
	private String hash1;
	private String hash2;
	private String hash3;
	private String hash4;
	private String hash5;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getHash1() {
		return hash1;
	}

	public void setHash1(String hash1) {
		this.hash1 = hash1;
	}

	public String getHash2() {
		return hash2;
	}

	public void setHash2(String hash2) {
		this.hash2 = hash2;
	}

	public String getHash3() {
		return hash3;
	}

	public void setHash3(String hash3) {
		this.hash3 = hash3;
	}

	public String getHash4() {
		return hash4;
	}

	public void setHash4(String hash4) {
		this.hash4 = hash4;
	}

	public String getHash5() {
		return hash5;
	}

	public void setHash5(String hash5) {
		this.hash5 = hash5;
	}

	public String getReduceTitle() {	
		String str = "["+category+"] "+title;		
		if(str.length() > 18) {
			if(category.length() > 5 && title.length() > 7) {
				title = title.substring(0, 7) + "...";	
			}else if(title.length() > 12) {
				title = title.substring(0, 11) + "...";	
			}
		}else {
			if(title.length() > 12) {
				title = title.substring(0, 11) + "...";	
			}
		}
		return title;
	}
	
	public static String ReduceTitle(String category, String title) {	
		String str = "["+category+"] "+title;		
		if(str.length() > 18) {
			if(category.length() > 5 && title.length() > 7) {
				title = title.substring(0, 7) + "...";	
			}else if(title.length() > 12) {
				title = title.substring(0, 12) + "...";	
			}
		}else {
			if(title.length() > 12) {
				title = title.substring(0, 12) + "...";	
			}
		}
		return title;
	}
	
	public String getDateString() {
			
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
	
	public String getFormatDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		
		String sday = "";
		if(day < 10) {
			sday = "0"+day;
		}else {
			sday = day+"";
		}
		String str = year +"-" + month + "-" + sday;
		
		return str;
	}
	
	public String getSimpleLocation() {		
		int f = location.indexOf(" ");		
		int s = location.indexOf(" ", f+1);

		location = location.substring(0, s);		
		return location;
	}
	
	public static String SimpleLocation(String location) {		
		int f = location.indexOf(" ");		
		int s = location.indexOf(" ", f+1);

		location = location.substring(0, s);		
		return location;
	}
	
	public String getHashTags() {
		String str = "";
		
		if(hash1.equals("") || hash1 == null) {
			str += "";
		}else {
			str = "#"+hash1;
		}
		if(hash2.equals("") || hash2 == null) {
			str += "";
		}else {
			str += ",#"+hash2;
		}
		if(hash3.equals("") || hash3 == null) {
			str += "";
		}else {
			str += ",#"+hash3;
		}
		if(hash4.equals("") || hash4 == null) {
			str += "";
		}else {
			str += ",#"+hash4;
		}
		if(hash5.equals("") || hash5 == null) {
			str += "";
		}else {
			str += ",#"+hash5;
		}
		
		return str;
	}
}
