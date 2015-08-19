package com.jei.spring.domain.board;
/**
 * 첨부파일 정보를 저장하는 객체
 */
public class AttachFile {
	private int fileIdx;					//파일번호(PK)
	private String fileOriginalName;		//원본파일명
	private String fileName;				//서버에 저장될 파일명
	private long fileSize;					//파일 크기
	private String fileUrl;					//파일 경로
	private String fileExt;					//파일 확장자
	private int fileDownloadCount;			//파일 다운로드 횟수
	
	public AttachFile() {}
	public AttachFile(int fileIdx, String fileOriginalName, String fileName,
			long fileSize, String fileUrl, String fileExt, int fileDownCount) {
		super();
		this.fileIdx = fileIdx;
		this.fileOriginalName = fileOriginalName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileUrl = fileUrl;
		this.fileExt = fileExt;
		this.fileDownloadCount = fileDownCount;
	}
	
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public int getFileDownloadCount() {
		return fileDownloadCount;
	}
	public void setFileDownloadCount(int fileDownloadCount) {
		this.fileDownloadCount = fileDownloadCount;
	}
}
