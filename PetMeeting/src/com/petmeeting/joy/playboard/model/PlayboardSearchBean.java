package com.petmeeting.joy.playboard.model;

import java.io.Serializable;

public class PlayboardSearchBean implements Serializable{
	
	private String playCategory;
	
	private String searchCategory;
	private String searchText;
	
	private String sortingType;
	
	private int currPage;
	
	private int startRow;
	private int endRow;
	
	public PlayboardSearchBean() {
	}

	public PlayboardSearchBean(String playCategory, String searchCategory, String searchText, String sortingType,
			int startRow, int endRow) {
		super();
		this.playCategory = playCategory;
		this.searchCategory = searchCategory;
		this.searchText = searchText;
		this.sortingType = sortingType;
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public String getPlayCategory() {
		return playCategory;
	}

	public void setPlayCategory(String playCategory) {
		this.playCategory = playCategory;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSortingType() {
		return sortingType;
	}

	public void setSortingType(String sortingType) {
		this.sortingType = sortingType;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	@Override
	public String toString() {
		return "PlayboardSearchBean [playCategory=" + playCategory + ", searchCategory=" + searchCategory
				+ ", searchText=" + searchText + ", sortingType=" + sortingType + ", currPage=" + currPage
				+ ", startRow=" + startRow + ", endRow=" + endRow + "]";
	}


	
	
	
}
