package com.prokopiv.bean;


public class Search {

	private String searchType = "all";
	private String searchRow;
	
	public Search() {
	}
	
	public String getSearchRow() {
		return searchRow;
	}

	public void setSearchRow(String searchRow) {
		this.searchRow = searchRow;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
}
