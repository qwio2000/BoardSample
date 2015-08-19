package com.jei.spring.repository;

import java.util.HashMap;
import java.util.List;

import com.jei.spring.domain.board.AttachFile;
import com.jei.spring.domain.board.BoardArticle;
import com.jei.spring.domain.board.Comment;

public interface BoardRepository {

	public List<BoardArticle> selectBoardList(HashMap<String, Object> map);

	public int selectBoardKindCode(String boardKindName);

	public BoardArticle selectBoardArticle(Integer boardIdx);

	public List<AttachFile> selectAttachFileList(Integer boardIdx);

	public List<Comment> selectCommentList(Integer boardIdx);

	public void updateReadCount(Integer boardIdx);

	public int selectBoardListCount(HashMap<String, Object> map);

	public int insertBoardArticle(HashMap<String, Object> map);

	public void insertAttachFile(HashMap<String, Object> map);

	public int selectLatestBoard(String boardKindName);

	public void updateDownloadCount(HashMap<String, Object> map);

	public void insertDownloadInfo(HashMap<String, Object> map);

	public void insertComment(HashMap<String, Object> map);

	public void deleteComment(int commentIdx);

	public void deleteBoardArticle(Integer boardIdx);

	public void deleteAttachFile(Integer boardIdx);

	public void deleteComments(Integer boardIdx);

	public AttachFile selectAttachFile(int fileIdx);

	public void deleteAttachFileByFileIdx(int fileIdx);

	public void updateBoardArticle(HashMap<String, Object> map);
	
}
