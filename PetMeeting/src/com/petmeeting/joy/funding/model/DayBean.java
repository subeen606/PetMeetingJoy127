package com.petmeeting.joy.funding.model;

import java.io.Serializable;
import java.util.Date;

import com.petmeeting.joy.funding.util.DateUtil;

public class DayBean implements Serializable{

	private String email;
	
	private int syear;	// 시작일
	private int smonth;
	private int sday;
	
	private int eyear;	// 종료일
	private int emonth;
	private int eday;
	
	private Date sdate;
	private Date edate;
	
	public Date getSDate() {
		return DateUtil.toDate(getSyear(),getSmonth(),getSday());
	}
	
	public Date getEDate() {
		return DateUtil.toDate(getEyear(), getEmonth(), getEday());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
	}

	public int getSmonth() {
		return smonth;
	}

	public void setSmonth(int smonth) {
		this.smonth = smonth;
	}

	public int getSday() {
		return sday;
	}

	public void setSday(int sday) {
		this.sday = sday;
	}

	public int getEyear() {
		return eyear;
	}

	public void setEyear(int eyear) {
		this.eyear = eyear;
	}

	public int getEmonth() {
		return emonth;
	}

	public void setEmonth(int emonth) {
		this.emonth = emonth;
	}

	public int getEday() {
		return eday;
	}

	public void setEday(int eday) {
		this.eday = eday;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	@Override
	public String toString() {
		return "dayBean [email=" + email + ", syear=" + syear + ", smonth=" + smonth + ", sday=" + sday + ", eyear="
				+ eyear + ", emonth=" + emonth + ", eday=" + eday + ", sdate=" + sdate + ", edate=" + edate + "]";
	}
	
	
	
}
