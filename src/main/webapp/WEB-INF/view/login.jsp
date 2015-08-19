<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/public/css/common_admin.css">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
</head>
<body id="login">
<c:if test="${param.error == 'true'}">
<script type="text/javascript">
$(function(){
	alert("계정과 암호가 일치하지 않습니다.");
	$("#loginFrm").attr("action","/login");
	$("#loginFrm").submit();
});
</script>
</c:if>
<div id="loginWrapper">
		<!-- header -->
		<div id="header">
			<h1></h1>
		</div>
		<!-- //header -->

		<!-- container -->
		<div id="loginContainer">
			<h2><img src="/public/img/h2_login.gif" alt="로그인" /><sec:authorize access="isAuthenticated()">&nbsp;&nbsp;<sec:authentication property="principal.memberId"/>님 환영합니다. <a style="float:right;" href="<c:url value='/board/notice' />"><img style="width:100px;height:50px;" src="/public/img/btn_go.gif"/></a></sec:authorize></h2>
			<form id="loginFrm" action="<c:url value='/loginCheck' />" method="post">
			<div class="login-box">
			<sec:authorize access="isAnonymous()">
					<fieldset>
						<legend>로그인 페이지</legend>
						<div>
							<p><input type="text" placeholder="아이디"  name="memberId" maxlength="8"/></p>
							<p><input type="password" placeholder="비밀번호"  name="memberPassword" maxlength="100"/></p>
							<span class="button btn-login"><input type="submit" value="로그인" /></span>
						</div>
						<ul>
							<li>Copyright 2015 JEI Corporation. All rights reserved.</li>
						</ul>
				
					</fieldset>
			
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
			<a href="<c:url value='/logout' />"><img src="/public/img/btn_logout.png"/></a>
			</sec:authorize>
			<input type="hidden" name="returl" value="${param.returl}" />
			</div>
			</form>
		</div>
		<!-- //container -->
	</div>
</body>
</html>