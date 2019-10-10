package com.petmeeting.joy.playboard.model;

public class PlayboardDateBean {
	
	private int pyear;
	private int pmonth;
	private int pday;
	
	private int eyear;
	private int emonth;
	private int eday;
	
	public PlayboardDateBean() {
	}

	public PlayboardDateBean(int pyear, int pmonth, int pday, int eyear, int emonth, int eday) {
		super();
		this.pyear = pyear;
		this.pmonth = pmonth;
		this.pday = pday;
		this.eyear = eyear;
		this.emonth = emonth;
		this.eday = eday;
	}

	public int getPyear() {
		return pyear;
	}

	public void setPyear(int pyear) {
		this.pyear = pyear;
	}

	public int getPmonth() {
		return pmonth;
	}

	public void setPmonth(int pmonth) {
		this.pmonth = pmonth;
	}

	public int getPday() {
		return pday;
	}

	public void setPday(int pday) {
		this.pday = pday;
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

	@Override
	public String toString() {
		return "playboardDateBean [pyear=" + pyear + ", pmonth=" + pmonth + ", pday=" + pday + ", eyear=" + eyear
				+ ", emonth=" + emonth + ", eday=" + eday + "]";
	}
	
	

}
