package com.jei.spring.domain;

import java.util.List;

public class OmrGichoes {
	
	private List<OmrGicho> omrGichoes;
	private int currentPage;
	private int rowCount;
	private int totalCnt;
	private int pageBlockSize;
	private int pageTotalNum;
	
	public List<OmrGicho> getOmrGichoes() {
		return omrGichoes;
	}
	public void setOmrGichoes(List<OmrGicho> omrGichoes) {
		this.omrGichoes = omrGichoes;
	}
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
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getPageBlockSize() {
		return pageBlockSize;
	}
	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}
	public int getPageTotalNum() {
		return pageTotalNum;
	}
	public void setPageTotalNum(int pageTotalNum) {
		this.pageTotalNum = pageTotalNum;
	}
	
}
