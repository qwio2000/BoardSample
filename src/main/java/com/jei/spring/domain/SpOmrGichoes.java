package com.jei.spring.domain;

import java.util.List;

public class SpOmrGichoes {
	
	private List<SpOmrGicho> spOmrGichoes;
	private int currentPage;
	private int rowCount;
	private int pageBlockSize;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageBlockSize() {
		return pageBlockSize;
	}
	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}
	public List<SpOmrGicho> getSpOmrGichoes() {
		return spOmrGichoes;
	}
	public void setSpOmrGichoes(List<SpOmrGicho> spOmrGichoes) {
		this.spOmrGichoes = spOmrGichoes;
	}
	
}
