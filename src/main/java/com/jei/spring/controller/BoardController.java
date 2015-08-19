package com.jei.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jei.spring.domain.board.BoardArticle;
import com.jei.spring.domain.board.Paging;
import com.jei.spring.service.BoardService;

@Controller
@RequestMapping("/board/{boardKindName}")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	/**
	 * 게시판 리스트 불러오는 메소드
	 * @param boardKindName
	 * @param currentPage
	 * @param searchType : 검색 종류(ex.제목, 작성자, 내용)
	 * @param searchWord : 검색어
	 */
	@RequestMapping(value = "/{currentPage}", method = RequestMethod.GET)
    public ModelAndView boardList(@PathVariable String boardKindName, @PathVariable Integer currentPage, 
    		String searchType, String searchWord) {
		Paging paging = new Paging();
		paging.setCurrentPage(currentPage);
		paging.setRowCount(10);
		paging.setPageBlockSize(10);
		paging.setTotalCount(boardService.getBoardListCount(boardKindName,searchType,searchWord));
		paging.setPageTotalNum((int) Math.ceil((double)paging.getTotalCount()/(double)paging.getRowCount()));
		paging.setOffset((paging.getCurrentPage() - 1) * paging.getRowCount());
		List<BoardArticle> boardList = boardService.getBoardList(boardKindName, paging,searchType,searchWord);
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardKindName", boardKindName);
		mav.addObject("boardList", boardList);
		mav.addObject("paging", paging);
		mav.setViewName("/board/boardList");
        return mav;
    }
	/**
	 * 쿠키 값에서 사용자 ID 불러오는 메서드
	 * @param request
	 * @return memberId
	 */
	private String getCookieValueMemberId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		if(cookies == null){	
		}else{
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("AUTH")){
					try {
						return URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						return null;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 게시판 리스트 중 currentPage값을 받지 못한 경우를 수행하는 메서드
	 * @param boardKindName
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView boardListDefault(@PathVariable String boardKindName) {
		int currentPage = 1;
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/"+boardKindName+"/"+currentPage);
		return mav;
	}
	/**
	 * 게시글 보기를 수행하는 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param currentPage
	 */
	@RequestMapping(value = "/{boardIdx}/view", method = RequestMethod.GET)
	public ModelAndView boardView(@PathVariable String boardKindName, @PathVariable Integer boardIdx, int currentPage,HttpServletRequest request) {
		BoardArticle boardArticle = boardService.getBoardArticle(boardIdx);
		boardArticle.setBoardContent(boardArticle.getBoardContent().replaceAll("\r\n", "<br/>"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberId",getCookieValueMemberId(request));
		mav.addObject("currentPage", currentPage);
		mav.addObject("boardKindName", boardKindName);
		mav.addObject("boardArticle", boardArticle);
		mav.setViewName("/board/boardContent");
		return mav;
	}
	/**
	 * 게시글 입력 화면 출력을 수행하는 메서드
	 * @param boardKindName
	 * @param currentPage
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView boardWriteForm(@PathVariable String boardKindName,int currentPage) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("category","write");
		mav.addObject("currentPage", currentPage);
		mav.addObject("boardKindName", boardKindName);
		mav.setViewName("/board/boardWrite");
		return mav;
	}
	
	/**
	 * 게시글 입력을 수행하는 메서드
	 * @param boardKindName
	 * @param boardArticle
	 * @param mreq : 다중 파일 업로드를 수행하기 위한 객체
	 * @param currentPage
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView boardWrite(@PathVariable String boardKindName, BoardArticle boardArticle,
			MultipartHttpServletRequest mreq, int currentPage) throws IllegalStateException, IOException {
		boardArticle.setMemberId(getCookieValueMemberId(mreq));
		boardArticle.setBoardContent(StringEscapeUtils.escapeHtml(boardArticle.getBoardContent()));
		List<MultipartFile> mf = mreq.getFiles("attachFile");
		boardService.writeBoard(boardKindName, boardArticle, mf);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/"+boardKindName);
		return mav;
	}
	/**
	 * 게시글 수정 화면 출력을 수행하는 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param currentPage
	 */
	@RequestMapping(value = "/{boardIdx}/edit", method = RequestMethod.GET)
	public ModelAndView boardModifyForm(@PathVariable String boardKindName, @PathVariable Integer boardIdx, int currentPage) {
		BoardArticle boardArticle = boardService.getBoardArticle(boardIdx);
		ModelAndView mav = new ModelAndView();
		mav.addObject("category","edit");
		mav.addObject("currentPage", currentPage);
		mav.addObject("boardKindName", boardKindName);
		mav.addObject("boardArticle", boardArticle);
		mav.setViewName("/board/boardWrite");
		return mav;
	}
	
	/**
	 * 게시글 수정을 수행하는 메서드
	 * @param boardKindName
	 * @param boardArticle
	 * @param mreq : 다중 파일 업로드를 수행하기 위한 객체
	 * @param currentPage
	 */
	@RequestMapping(value="/{boardIdx}", method = RequestMethod.POST)
	public ModelAndView boardModify(@PathVariable String boardKindName,@PathVariable int boardIdx, BoardArticle boardArticle,
			MultipartHttpServletRequest mreq, int currentPage) throws IllegalStateException, IOException {
		List<MultipartFile> mf = mreq.getFiles("attachFile");
		boardArticle.setBoardContent(StringEscapeUtils.escapeHtml(boardArticle.getBoardContent()));
		boardService.modifyBoard(boardIdx, boardArticle, mf, boardKindName);
		ModelAndView mav = new ModelAndView();
		mav.addObject("currentPage",currentPage);
		mav.setViewName("redirect:/board/"+boardKindName+"/"+boardIdx+"/view");
		return mav;
	}
	/**
	 * 게시물 삭제 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param currentPage
	 */
	@RequestMapping(value = "/{boardIdx}/delete", method = RequestMethod.DELETE)
	public ModelAndView boardDelete(@PathVariable String boardKindName, @PathVariable Integer boardIdx, int currentPage){
		boardService.deleteBoardArticle(boardIdx);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/board/"+boardKindName+"/"+currentPage);
		return mav;
	}
	/**
	 * 댓글 등록 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param commentContent : 댓글 내용
	 * @param currentPage
	 */
	@RequestMapping(value = "/{boardIdx}/reply", method = RequestMethod.POST)
	public ModelAndView commentRegist(@PathVariable String boardKindName, @PathVariable Integer boardIdx, String commentContent, int currentPage, HttpServletRequest request){
		boardService.insertComment(boardIdx, commentContent, getCookieValueMemberId(request));
		ModelAndView mav = new ModelAndView();
		mav.addObject("currentPage", currentPage);
		mav.setViewName("redirect:/board/"+boardKindName+"/"+boardIdx+"/view");
		return mav;
	}
	/**
	 * 댓글 삭제 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param currentPage
	 * @param commentIdx
	 */
	@RequestMapping(value = "/{boardIdx}/reply", method = RequestMethod.DELETE)
	public ModelAndView commentDelete(@PathVariable String boardKindName, @PathVariable Integer boardIdx, int currentPage, int commentIdx){
		boardService.deleteReply(commentIdx);
		ModelAndView mav = new ModelAndView();
		mav.addObject("currentPage", currentPage);
		mav.setViewName("redirect:/board/"+boardKindName+"/"+boardIdx+"/view");
		return mav;
	}
	/**
	 * 파일 다운로드 처리 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param fileName : 다운로드 받을 파일이 서버에 저장된 실제 파일 명
	 * @param fileUrl : 다운로드 받을 파일이 존재하는 경로
	 * @param fileOriginalName : 원본 파일 명
	 */
	@Transactional
	@RequestMapping(value = "/{boardIdx}/download", method = RequestMethod.GET)
	public ModelAndView attachFileDownload(@PathVariable String boardKindName, @PathVariable Integer boardIdx,
			String fileName, String fileUrl, String fileOriginalName, HttpServletRequest request){
		boardService.insertFileDownloadInfo(boardIdx, fileName, getCookieValueMemberId(request));
		String fullUrl = fileUrl + "\\/" +  fileName;
		File file = new File(fullUrl);
		ModelAndView mav = new ModelAndView("download", "downloadFile", file);
		mav.addObject("fileOriginalName", fileOriginalName);
		return mav;
	}
	/**
	 * 첨부파일 삭제 메서드
	 * @param boardKindName
	 * @param boardIdx
	 * @param currentPage
	 * @param fileIdx
	 */
	@RequestMapping(value = "/{boardIdx}/fileDelete", method = RequestMethod.GET)
	public ModelAndView attachFileDelete(@PathVariable String boardKindName, @PathVariable Integer boardIdx,int fileIdx, int currentPage){
		boardService.deleteAttachFile(fileIdx);
		ModelAndView mav = new ModelAndView();
		mav.addObject("currentPage",currentPage);
		mav.setViewName("redirect:/board/"+boardKindName+"/"+boardIdx+"/edit");
		return mav;
	}
	
}
