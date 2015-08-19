<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/public/css/common_admin.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_admin_center.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${boardKindName}</title>
</head>
<body>
	<div id="wrap">
		<div id="container">
			<div id="content">
				<div id="primary_content" class="primary-content">
					 <div class="j_table_02">
					 <br>
					 <div style="float: right;">
					 	<sec:authorize access="isAuthenticated()">
						<a href="<c:url value='/logout' />"><img style="width:100px;height: 50px;float: right;" src="/public/img/btn_logout.png"/></a>
						</sec:authorize>
					</div>
					<div style="float: right;margin-top: 13px;font-size: 15px;">
						<sec:authentication property="principal.memberId"/>님 환영합니다.
					</div>
                        <table>
                            <colgroup>
                                <col width="95px">
                                <col width="*">							
                                <col width="90px">
                                <col width="150px">							
                                <col width="90px">							
                            </colgroup>
                           <tbody>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>등록일</th>
									<th>조회수</th>
								</tr>
								<c:forEach var="article" items="${boardList }" varStatus="status">
									<tr>
										<td>${paging.startBoardIdx-status.index }</td>
										<td>
											<div class="td-left">
												<a href="/board/${boardKindName }/${article.boardIdx}/view?currentPage=${paging.currentPage}">
												${article.boardSubject }
												[${article.commentTotalCount }]
												</a>
												<c:if test="${article.newData == 'new' }">
												<img alt="NEW" src="/public/img/icon_new_white.gif">
												</c:if>
												
											</div>
										</td>
										<td>${article.memberId }</td>
										<td>${article.boardRegDate }</td>
										<td>${article.boardReadCount }</td>
									</tr>
								</c:forEach>
                        </tbody>
                        </table>
                    </div><br>
                    <span class="button btn-type-C float-l">
						<a href="/board/${boardKindName }/1">목록</a>
					</span>
                    <span class="button btn-type-C float-r">
						<a href="/board/${boardKindName }/new?currentPage=${currentPage}">글 작성</a>
					</span>
						<jsp:include page="paging.jsp" flush="true">
							<jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
							<jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
							<jsp:param name="startPageNo" value="${paging.startPageNo}" />
							<jsp:param name="currentPage" value="${paging.currentPage}" />
							<jsp:param name="endPageNo" value="${paging.endPageNo}" />
							<jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
							<jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
							<jsp:param name="boardKindName" value="${boardKindName}" />
						</jsp:include>
						<form method="get">
                        <fieldset class="board-search2">
                            <select class="formSelect" name="searchType" title="검색조건선택">
                                <option value="boardSubject">제목</option>
                                <option value="memberId">작성자</option>
                                <option value="boardContent">내용</option>
                            </select>
                            <input type="text" name="searchWord" class="text">
                            <span class="button"><input type="submit" value="검색"></span>
                        </fieldset>
                    </form>
				</div>
			</div>
		</div>
	
	</div>
</body>
</html>