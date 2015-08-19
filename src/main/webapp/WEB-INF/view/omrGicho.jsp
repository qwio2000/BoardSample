<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
<script type="text/javascript" src="/public/js/omrGicho.js"></script>
</head>
<body>
<div>
		<span>omrGicho 입력</span>
		<form id="omrGichoFrm" name="omrGichoFrm">
			<p>회원코드:<input name="hkey" maxlength="8" required="required"> (예: AA123456) PK</p>
			<p>과목:<input name="kwamok" maxlength="2" required="required"> (예: KM) PK</p>
			<p>read/write/보충처방:<input name="rw" maxlength="1"> (예: 0또는 1)</p>
			<p>학습목표별처방:<input name="nOmr" maxlength="1"> (예: 0또는 1)</p>
			<p>회원이름(First):<input name="mFirstName" maxlength="20"></p>
			<p>회원이름(Last):<input name="mLastName" maxlength="20"></p>
			<p>선생님코드:<input name="skey" maxlength="5"> (예: 00112)</p>
			<p>선생님명:<input name="sName" maxlength="20"></p>
			<p>처방등급:<input name="omrGrd" maxlength="1"> (예: G)</p>
			<p>학년:<input name="omrHak" maxlength="2"> (예: U1또는 03)</p>
			<p>처방종류:<input name="omrKind" maxlength="1"> (예: 1~6)</p>
			<p>관리요일1:<input name="omrDay" maxlength="2"> (예: 1~7)</p>
			<p>관리요일2:<input name="omrDay2" maxlength="2"> (예: 1~7)</p>
			<p>불출수:<input name="omrCbulsu" maxlength="1"> (예: 1또는 2)</p>
			<p>생년월일:<input name="omrBirth" class="datePicker" maxlength="10"></p>
			<p>작업자:<input name="workID" maxlength="7"> (예: x0001)</p>
			<p>조직코드:<input name="branch" maxlength="7"> (예: 0300012)</p>
			<p>지사코드:<input name="team" maxlength="2"> (예: 03)</p>
			<p>보충세트수:<input name="boSetSu" maxlength="5"></p>
			<p>처방결과등급:<input name="nDung" maxlength="1"> (예: E)</p>
			<p>처방결과명:<input name="pan" maxlength="10"> (예: 총괄)</p>
			<p>처방실행완료여부:<input name="inSta" maxlength="1"> (예: 1~3)</p>
			<p>처방유입경로:<input name="omrPath" maxlength="2"> (예: 1~3)</p>
		</form>
	</div>
	<div>
		<input type="button" style="width:250px;" onclick="$.omrGichoSave();" value="insert쿼리이용하는로직 저장">
		<input type="button" style="width:250px;" onclick="$.spOmrGichoSave();" value="sp이용하는로직 저장">
		<input type="button" style="width:250px;" onclick="$.omrGichoList();" value="select쿼리이용하는 로직 리스트 보기">
		<input type="button" style="width:250px;" onclick="$.spOmrGichoList();" value="sp이용하는 로직 리스트 보기">

	</div>
	<div>
		<span id="totalCnt" style="color:green;"></span>&nbsp;&nbsp;&nbsp;<span id="logicId" style="color:red;"></span>
	</div>
	<div id="dataList">
	</div>
	<div id="pageNavi"></div>
	<input type="hidden" id="currentPage" value="${currentPage}">
</body>
</html>