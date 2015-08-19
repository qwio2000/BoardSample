package com.jei.spring.domain.board;

/**
 * 댓글 정보를 저장하는 객체
 */
public class Comment {
	private int commentIdx;			//댓글 번호(PK)
	private int boardIdx;			//게시글 번호
	private String memberId;		//댓글 작성자
	private String commentContent;	//댓글 내용
	private String commentRegDate;	//댓글 등록일
	
	public Comment() {}
	public Comment(int commentIdx, int boardIdx, String memberId,
			String commentContent, String commentRegDate) {
		super();
		this.commentIdx = commentIdx;
		this.boardIdx = boardIdx;
		this.memberId = memberId;
		this.commentContent = commentContent;
		this.commentRegDate = commentRegDate;
	}
	
	public int getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(int commentIdx) {
		this.commentIdx = commentIdx;
	}
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
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentRegDate() {
		return commentRegDate;
	}
	public void setCommentRegDate(String commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

}
