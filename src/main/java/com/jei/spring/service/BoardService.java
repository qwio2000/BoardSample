package com.jei.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jei.spring.domain.board.AttachFile;
import com.jei.spring.domain.board.BoardArticle;
import com.jei.spring.domain.board.Paging;
import com.jei.spring.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	public int getBoardListCount(String boardKindName, String searchType, String searchWord) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardKindName", boardKindName);
		map.put("searchType", searchType);
		map.put("searchWord", searchWord);
		return boardRepository.selectBoardListCount(map);
	}

	public List<BoardArticle> getBoardList(String boardKindName, Paging paging, String searchType, String searchWord) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardKindName", boardKindName);
		map.put("rowCount", paging.getRowCount());
		map.put("offset", paging.getOffset());
		map.put("searchType", searchType);
		map.put("searchWord", searchWord);
		return boardRepository.selectBoardList(map);
	}

	public BoardArticle getBoardArticle(Integer boardIdx) {
		// TODO Auto-generated method stub
		BoardArticle boardArticle = new BoardArticle();
		boardRepository.updateReadCount(boardIdx);
		boardArticle = boardRepository.selectBoardArticle(boardIdx);
		boardArticle.setAttachFileList(boardRepository.selectAttachFileList(boardIdx));
		boardArticle.setCommentList(boardRepository.selectCommentList(boardIdx));
		return boardArticle;
	}
	@Transactional
	public void writeBoard(String boardKindName, BoardArticle boardArticle,
			List<MultipartFile> mf) throws IllegalStateException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardKindName", boardKindName);
		map.put("boardArticle", boardArticle);
		boardRepository.insertBoardArticle(map);
		String realFolder = "e:/uploadFile/"+boardKindName+"/";
		File dir = new File(realFolder);
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		if(mf.size()==1 && mf.get(0).getOriginalFilename().equals("")){
		}else{
			for (int i = 0; i < mf.size(); i++) {
				AttachFile attachFile = new AttachFile(0,
						mf.get(i).getOriginalFilename(), 
						UUID.randomUUID().toString()+"."+getExtension(mf.get(i).getOriginalFilename()), 
						mf.get(i).getSize(), 
						realFolder,
						getExtension(mf.get(i).getOriginalFilename()), 
						0);
				String savePath = realFolder + attachFile.getFileName();
				mf.get(i).transferTo(new File(savePath));
				map.put("attachFile", attachFile);
				boardRepository.insertAttachFile(map);
			}
		}
	}

	private String getExtension(String originalFilename) {
		// TODO Auto-generated method stub
		return originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	}

	public void insertFileDownloadInfo(Integer boardIdx, String fileName, String memberId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardIdx", boardIdx);
		map.put("memberId", memberId);
		map.put("fileName", fileName);
		boardRepository.updateDownloadCount(map);
		boardRepository.insertDownloadInfo(map);
	}

	public void insertComment(Integer boardIdx, String commentContent, String memberId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardIdx", boardIdx);
		map.put("memberId", memberId);
		map.put("commentContent", commentContent);
		boardRepository.insertComment(map);
	}

	public void deleteReply(int commentIdx) {
		// TODO Auto-generated method stub
		boardRepository.deleteComment(commentIdx);
	}

	public void deleteBoardArticle(Integer boardIdx) {
		// TODO Auto-generated method stub
		List<AttachFile> attachFileList = boardRepository.selectAttachFileList(boardIdx);
		if (attachFileList != null && attachFileList.size() > 0) {
			for (int i = 0; i < attachFileList.size(); i++) {
				File attachFile = new File(attachFileList.get(i).getFileUrl()+attachFileList.get(i).getFileName());
				if(attachFile.exists()){
					attachFile.delete();
				}
			}
		}
		boardRepository.deleteAttachFile(boardIdx);
		boardRepository.deleteComments(boardIdx);
		boardRepository.deleteBoardArticle(boardIdx);
	}

	public void deleteAttachFile(int fileIdx) {
		// TODO Auto-generated method stub
		AttachFile attachFile = boardRepository.selectAttachFile(fileIdx);
		File realFile = new File(attachFile.getFileUrl()+attachFile.getFileName());
		if(realFile.exists()){
			realFile.delete();
		}
		boardRepository.deleteAttachFileByFileIdx(fileIdx);
	}

	public void modifyBoard(int boardIdx, BoardArticle boardArticle,
			List<MultipartFile> mf, String boardKindName) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardIdx", boardIdx);
		map.put("boardArticle", boardArticle);
		boardRepository.updateBoardArticle(map);
		String realFolder = "e:/uploadFile/"+boardKindName+"/";
		File dir = new File(realFolder);
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		if(mf.size()==1 && mf.get(0).getOriginalFilename().equals("")){
		}else{
			for (int i = 0; i < mf.size(); i++) {
				AttachFile attachFile = new AttachFile(0,
						mf.get(i).getOriginalFilename(), 
						UUID.randomUUID().toString()+"."+getExtension(mf.get(i).getOriginalFilename()), 
						mf.get(i).getSize(), 
						realFolder,
						getExtension(mf.get(i).getOriginalFilename()), 
						0);
				String savePath = realFolder + attachFile.getFileName();
				mf.get(i).transferTo(new File(savePath));
				map.put("attachFile", attachFile);
				boardRepository.insertAttachFile(map);
			}
		}
	}
}
