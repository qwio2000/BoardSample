<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/public/css/common_admin.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_admin_center.css" />
<title>Insert title here</title>
<script type="text/javascript">
	function commentSubmit(){
		var frm = document.frm;
		if(frm.commentContent.value == null || frm.commentContent.value.length == 0){
			alert('댓글 내용을 입력해 주십시오');
			frm.commentContent.focus();
			return false;
		}
		frm.submit();
	}
	function boardDelete(boardKindName,boardIdx){
		var check = confirm("삭제하시겠습니까?");
		if(check){
		var frm = document.boardForm;
		frm.action="/board/"+boardKindName+"/"+boardIdx+"/delete";
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = "boardIdx";
		input.value = boardIdx;
		frm.appendChild(input);
		var method = document.createElement("input");
		method.type = "hidden";
		method.name = "_method";
		method.value = "delete";
		frm.appendChild(method);

		frm.submit();
		}
	}
	function commentDelete(commentIdx,boardKindName,boardIdx){
		var check = confirm("삭제하시겠습니까?");
		if(check){
		var frm = document.frm;
		frm.action="/board/"+boardKindName+"/"+boardIdx+"/reply";
		var input = document.createElement("input");
		input.type = "hidden";
		input.name = "commentIdx";
		input.value = commentIdx;
		frm.appendChild(input);
		var method = document.createElement("input");
		method.type = "hidden";
		method.name = "_method";
		method.value = "delete";
		frm.appendChild(method);

		frm.submit();
		}
	}
</script>
</head>
<body>
<div id="wrap">
		<div id="container">
			<div id="content">
				<div id="primary_content" class="primary-content">	
	<div class="j_table_01">
	<form name="boardForm" method="post">
		<input type="hidden" name="currentPage" value="${currentPage }"/>
		</form>
		<table>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="10%">
				<col width="15%">
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td><div class="td-left">${boardArticle.boardSubject }</div></td>
					<th>등록일</th>
					<td>${boardArticle.boardRegDate}</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td><div class="td-left">${boardArticle.memberId }</div></td>
					<th>조회수</th>
					<td>${boardArticle.boardReadCount }</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><div class="td-left">
							<c:choose>
								<c:when test="${empty boardArticle.attachFileList}"></c:when>
								<c:otherwise>
									<c:forEach var="attachFileList" items="${boardArticle.attachFileList }" varStatus="status">
										<a href="/board/${boardKindName }/${boardIdx }/download?fileName=${attachFileList.fileName}&fileUrl=${attachFileList.fileUrl}
										&fileOriginalName=${attachFileList.fileOriginalName}">
										${attachFileList.fileOriginalName }</a><br>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					<th>다운</th>
					<td>
						<c:choose>
							<c:when test="${empty boardArticle.attachFileList}">0</c:when>
							<c:otherwise>
								<c:forEach var="attachFileList" items="${boardArticle.attachFileList }" varStatus="status">
									${attachFileList.fileDownloadCount }<br>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<div class="td-left" style="overflow: auto; height:300px;overflow-x:hidden;">${boardArticle.boardContent }</div>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<form method="post" name="frm" action="/board/${boardKindName}/${boardIdx}/reply">
		<input type="hidden" name="currentPage" value="${currentPage }"/>
		<table>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="15%">
			</colgroup>
			<tbody>
				<tr>
					<th>댓글</th>
					<td><div class="td-left"><input type="text" name="commentContent" style="width: 600px" class="text"></div></td>
					<td>
						<span class="button btn-type-C float-r">
							<a href="javascript:commentSubmit()">등록</a>
						</span>
					</td>
				</tr>
				<c:choose>
						<c:when test="${empty boardArticle.commentList}"></c:when>
						<c:otherwise>
							<c:forEach var="commentList" items="${boardArticle.commentList }" varStatus="status">
								<tr>
									<th>${commentList.memberId }<br>${commentList.commentRegDate }</th>
									<td><div class="td-left">${commentList.commentContent }</div></td>
									<td>
										<c:if test="${memberId == commentList.memberId }">
										<span class="button btn-type-C float-r">
											<a href="javascript:commentDelete('${commentList.commentIdx }','${boardKindName}','${boardIdx }')">삭제</a>
										</span>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		</form>
	</div>
	<br>
	<span class="button btn-type-C float-l">
		<a href="/board/${boardKindName }/${currentPage}">목록</a>
	</span>
	<%-- <c:set var="memberId">
		<sec:authentication property="principal.memberId"/>
	</c:set> --%>
	<c:if test="${memberId == boardArticle.memberId }">
	<span class="button btn-type-C float-r">
		<a href="javascript:boardDelete('${boardKindName}','${boardIdx }')">삭제</a>
	</span>
	<span class="button btn-type-C float-r">
		<a href="/board/${boardKindName }/${boardIdx}/edit?currentPage=${currentPage}">수정</a>
	</span>
	</c:if>
</div>
</div>
</div>
</div>
</body>
</html>