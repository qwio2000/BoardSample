package com.jei.spring.domain.board;

import java.util.List;
/**
 * 게시글 정보를 저장하는 객체
 */
public class BoardArticle {
	private int boardIdx;					//게시판 글 번호(PK)
	private String memberId;				//작성자 아이디
	private int boardKindCode;				//게시판 코드
	private String boardSubject;			//게시글 제목
	private String boardContent;			//게시글 내용
	private int boardReadCount;				//게시글 조회수
	private String boardRegDate;			//게시글 등록일
	private List<AttachFile> attachFileList;//게시글 첨부파일 리스트
	private List<Comment> commentList;		//게시글 댓글 리스트
	private int commentTotalCount;			//게시글 댓글 개수
	private String newData;					//신규 글인지 확인(하루 단위)
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getBoardKindCode() {
		return boardKindCode;
	}
	public void setBoardKindCode(int boardKindCode) {
		this.boardKindCode = boardKindCode;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public int getBoardReadCount() {
		return boardReadCount;
	}
	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}
	public String getBoardRegDate() {
		return boardRegDate;
	}
	public void setBoardRegDate(String boardRegDate) {
		this.boardRegDate = boardRegDate;
	}
	public List<AttachFile> getAttachFileList() {
		return attachFileList;
	}
	public void setAttachFileList(List<AttachFile> attachFileList) {
		this.attachFileList = attachFileList;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public int getCommentTotalCount() {
		return commentTotalCount;
	}
	public void setCommentTotalCount(int commentTotalCount) {
		this.commentTotalCount = commentTotalCount;
	}
	public String getNewData() {
		return newData;
	}
	public void setNewData(String newData) {
		this.newData = newData;
	}
}
