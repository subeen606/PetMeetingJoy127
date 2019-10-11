package com.petmeeting.joy.funding.model;

import java.io.Serializable;

public class fundingBean implements Serializable{

	private String f_categorys; 
	
	//paging에 필요한 부분
	private int pageNumber = 0; //현재 페이지(초기값주기)
	private int recordCountPerPage = 10; //표현할 한 페이지의 글수 
	
	// [1] 페이지에 있는 게시글은 1~10(db번호로) 까지 : start와 end는 db용 변수(db에서 불러오기위한)
	private int start = 1; //초기값 주기
	private int end = 10; 
	
	public fundingBean() {}

	public String getF_categorys() {
		return f_categorys;
	}

	public void setF_categorys(String f_categorys) {
		this.f_categorys = f_categorys;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public fundingBean(String f_categorys, int pageNumber, int recordCountPerPage, int start, int end) {
		super();
		this.f_categorys = f_categorys;
		this.pageNumber = pageNumber;
		this.recordCountPerPage = recordCountPerPage;
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "fundingBean [f_categorys=" + f_categorys + ", pageNumber=" + pageNumber + ", recordCountPerPage="
				+ recordCountPerPage + ", start=" + start + ", end=" + end + "]";
	}
	
	
	
	
}
