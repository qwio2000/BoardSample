<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="j_pagination">
	<c:choose>
	<c:when test="${param.currentPage ne param.firstPageNo }">
		<span>
				<a href="/board/${param.boardKindName }/${param.firstPageNo}" class="first">
					<img alt="처음" src="/public/img/btn_paging_first.gif">
				</a>
		</span>
		<span>
				<a href="/board/${param.boardKindName }/${param.prevPageNo}" class="prev">
					<img alt="이전" src="/public/img/btn_paging_prev.gif">
				</a>
		</span>
	</c:when>
	<c:otherwise>
		<span><img alt="처음" src="/public/img/btn_paging_first.gif"></span>
		<span><img alt="이전" src="/public/img/btn_paging_prev.gif"></span>
	</c:otherwise>
	</c:choose>
	<span> 
		<c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
			<c:choose>
				<c:when test="${i eq param.currentPage}"><a href="#" class="current">${i}</a></c:when>
				<c:otherwise>
					<a href="/board/${param.boardKindName }/${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</span>
	<c:choose>
	<c:when test="${param.currentPage ne param.finalPageNo }">
		<span>
			<a href="/board/${param.boardKindName }/${param.nextPageNo}" class="next">
				<img alt="다음" src="/public/img/btn_paging_next.gif">
			</a>
		</span>
		<span>
			<a href="/board/${param.boardKindName }/${param.finalPageNo}" class="last">
				<img alt="마지막" src="/public/img/btn_paging_last.gif">
			</a>
		</span>
	</c:when>
	<c:otherwise>
		<span><img alt="다음" src="/public/img/btn_paging_next.gif"></span>
		<span><img alt="마지막" src="/public/img/btn_paging_last.gif"></span>
	</c:otherwise>
	</c:choose>
</div>