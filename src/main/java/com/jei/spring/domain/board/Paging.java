package com.jei.spring.domain.board;

public class Paging {
	private int currentPage;  //현재 페이지 번호
	private int rowCount;	  //한 페이지에 출력될 게시물 수
	private int totalCount;   //전체 게시물 수
	private int pageBlockSize;//한 번에 보여질 페이징 구간 수
	private int pageTotalNum; //페이지 총 개수
	private int offset;		  //게시물 시작 오프셋
    private int firstPageNo; // 첫 번째 페이지 번호
    private int prevPageNo; // 이전 페이지 번호
    private int startPageNo; // 시작 페이지 (페이징 네비 기준)
    private int endPageNo; // 끝 페이지 (페이징 네비 기준)
    private int nextPageNo; // 다음 페이지 번호
    private int finalPageNo; // 마지막 페이지 번호
    private int startBoardIdx;
    
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.makePaging();
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
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getFirstPageNo() {
		return firstPageNo;
	}
	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}
	public int getPrevPageNo() {
		return prevPageNo;
	}
	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getFinalPageNo() {
		return finalPageNo;
	}
	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}
	/**
     * 페이징 생성
     */
    private void makePaging() {
        if (this.totalCount == 0) return; // 게시 글 전체 수가 없는 경우
        if (this.currentPage == 0) this.setCurrentPage(1); // 기본 값 설정
        if (this.rowCount == 0) this.setRowCount(10); // 기본 값 설정

        int finalPage = (totalCount + (rowCount - 1)) / rowCount; // 마지막 페이지
        if (this.currentPage > finalPage) this.setCurrentPage(finalPage); // 기본 값 설정

        if (this.currentPage < 0 || this.currentPage > finalPage) this.currentPage = 1; // 현재 페이지 유효성 체크

        boolean isNowFirst = currentPage == 1 ? true : false; // 시작 페이지 (전체)
        boolean isNowFinal = currentPage == finalPage ? true : false; // 마지막 페이지 (전체)

        int startPage = ((currentPage - 1) / 10) * 10 + 1; // 시작 페이지 (페이징 네비 기준)
        int endPage = startPage + 10 - 1; // 끝 페이지 (페이징 네비 기준)

        if (endPage > finalPage) { // [마지막 페이지 (페이징 네비 기준) > 마지막 페이지] 보다 큰 경우
            endPage = finalPage;
        }

        this.setFirstPageNo(1); // 첫 번째 페이지 번호

        if (isNowFirst) {
            this.setPrevPageNo(1); // 이전 페이지 번호
        } else {
            this.setPrevPageNo(((currentPage - 1) < 1 ? 1 : (currentPage - 1))); // 이전 페이지 번호
        }

        this.setStartPageNo(startPage); // 시작 페이지 (페이징 네비 기준)
        this.setEndPageNo(endPage); // 끝 페이지 (페이징 네비 기준)

        if (isNowFinal) {
            this.setNextPageNo(finalPage); // 다음 페이지 번호
        } else {
            this.setNextPageNo(((currentPage + 1) > finalPage ? finalPage : (currentPage + 1))); // 다음 페이지 번호
        }

        this.setFinalPageNo(finalPage); // 마지막 페이지 번호
        this.setStartBoardIdx(totalCount-((currentPage-1)*rowCount));
    }
	public int getStartBoardIdx() {
		return startBoardIdx;
	}
	public void setStartBoardIdx(int startBoardIdx) {
		this.startBoardIdx = startBoardIdx;
	}

}
