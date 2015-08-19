<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/public/css/common_admin.css" />
<link rel="stylesheet" type="text/css" href="/public/css/layout_admin_center.css" />
<title>${boardKindName } 글 쓰기</title>
<script type="text/javascript">
	function submit(boardKindName, boardIdx){
		var frm = document.frm;
		if(frm.boardSubject.value == null || frm.boardSubject.value.length == 0){
			alert('제목을 입력해 주십시오');
			frm.boardSubject.focus();
			return false;
		}
		if(frm.boardContent.value == null || frm.boardContent.value.length == 0){
			alert('내용을 입력해 주십시오');
			frm.boardContent.focus();
			return false;
		}
		if(frm.category.value=="write"){
			frm.action="/board/"+boardKindName;
		}else if(frm.category.value=="edit"){
			frm.action="/board/"+boardKindName+"/"+boardIdx;
		}
		frm.submit();
	}
</script>
</head>
<body>
	<div id="wrap">
		<div id="container">
			<div id="content">
				<div id="primary_content" class="primary-content">
					<div class="j_table_01">
						<form name="frm" action="/board/${boardKindName }" method="post" enctype="multipart/form-data">
						<input type="hidden" name="currentPage" value="${currentPage }"/>
						<input type="hidden" name="category" value="${category }"/>
	                        <table>
	                            <colgroup>
	                                <col width="17%">
	                                <col width="45%">
	                                <col width="17%">
	                                <col width="33%">
	                            </colgroup>
	                            <tbody>
									<tr>
										<th>제목</th>
										<td colspan="3"><input type="text" name="boardSubject" style="width: 500px" class="text" value="${boardArticle.boardSubject }"></td>
									</tr>
									<tr>
		                                <th>첨부파일 등록</th>
		                                <td colspan="3">
		                                <input type="file" name="attachFile" multiple="multiple">
		                                <div style="float: right;">
		                                <c:choose>
											<c:when test="${empty boardArticle.attachFileList}"></c:when>
											<c:otherwise>
												<c:forEach var="attachFileList" items="${boardArticle.attachFileList }" varStatus="status">
													<a href="/board/${boardKindName }/${boardIdx}/fileDelete?fileIdx=${attachFileList.fileIdx}&currentPage=${currentPage}">
													<img width="10px" height="10px" src="/public/img/btn_gal_close.png"/></a>&nbsp;${attachFileList.fileOriginalName }<br/>
												</c:forEach>
											</c:otherwise>
										</c:choose>
										</div>
		                                <span class="vertical-8"></span>
		                                <p><span class="c-code">※ </span><em class="c-code">파일 첨부는 최대 10MB까지만 가능합니다</em></p>
		                                </td>
		                            </tr>
		                            <tr>
		                                <th>내용</th>
		                                <td colspan="3" class="padding">
		                                    <div style="background-color:#cacaca; height:350px;">
												<textarea name="boardContent" style="width: 792px; height: 338px;">${boardArticle.boardContent }</textarea>
		                                    </div>
		                                </td>
		                            </tr>
	                        	</tbody>
	                        </table>
	                	</form>
                    </div>
					<div class="j_btn_01 text-c">
						<span class="button btn-type-C float-l"> <a href="/board/${boardKindName }/${currentPage}">목록</a>
						</span>
						<span class="button btn-type-B">
							<a href="javascript:submit('${boardKindName }','${boardIdx }')">저장</a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>