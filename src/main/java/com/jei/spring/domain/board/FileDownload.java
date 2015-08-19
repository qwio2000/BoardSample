package com.jei.spring.domain.board;

import java.util.Date;
/**
 * 첨부파일 다운로드 정보를 저장하는 객체
 */
public class FileDownload {
	private int fileDownIdx;	//파일다운로드 번호(PK)
	private int boardIdx;		//게시글 번호
	private int fileIdx;		//첨부파일 번호
	private String memberId;	//다운로드 받은 사용자 아이디
	private Date fileDownDate;	//다운로드 받은 날짜
	
	public FileDownload() {}
	public FileDownload(int fileDownIdx, int boardIdx, int fileIdx,
			String memberId, Date fileDownDate) {
		super();
		this.fileDownIdx = fileDownIdx;
		this.boardIdx = boardIdx;
		this.fileIdx = fileIdx;
		this.memberId = memberId;
		this.fileDownDate = fileDownDate;
	}
	
	public int getFileDownIdx() {
		return fileDownIdx;
	}
	public void setFileDownIdx(int fileDownIdx) {
		this.fileDownIdx = fileDownIdx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getFileDownDate() {
		return fileDownDate;
	}
	public void setFileDownDate(Date fileDownDate) {
		this.fileDownDate = fileDownDate;
	}
}
