package com.petmeeting.joy.admin.model;

public class MemberSearchBean {
	
	private String sortingType;
	
	private String search_category;
	private String search_txt;
	
	private String grade;
	
	private int currPage;
	
	private int start;
	private int end;
	
	public MemberSearchBean() {
	}

	public MemberSearchBean(String sortingType, String search_category, String search_txt, String grade, int currPage,
			int start, int end) {
		super();
		this.sortingType = sortingType;
		this.search_category = search_category;
		this.search_txt = search_txt;
		this.grade = grade;
		this.currPage = currPage;
		this.start = start;
		this.end = end;
	}

	public String getSortingType() {
		return sortingType;
	}

	public void setSortingType(String sortingType) {
		this.sortingType = sortingType;
	}

	public String getSearch_category() {
		return search_category;
	}

	public void setSearch_category(String search_category) {
		this.search_category = search_category;
	}

	public String getSearch_txt() {
		return search_txt;
	}

	public void setSearch_txt(String search_txt) {
		this.search_txt = search_txt;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
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

	@Override
	public String toString() {
		return "MemberSearchBean [sortingType=" + sortingType + ", search_category=" + search_category + ", search_txt="
				+ search_txt + ", grade=" + grade + ", currPage=" + currPage + ", start=" + start + ", end=" + end
				+ "]";
	}
	
	
}
