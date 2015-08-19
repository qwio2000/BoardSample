$(function(){
		$.extend({
			omrGichoSave:function(){
				var check = validationCheck();	
				if(check){
					var data = $('#omrGichoFrm').serialize();
					$.post("/omrGichoSave",data).done(function(){
						alert("성공");
					});
				}
			},
			
			spOmrGichoSave:function(){
				var check = validationCheck();
				if(check){
					var data = $('#omrGichoFrm').serialize();
					$.post("/spOmrGichoSave",data).done(function(){
						alert("성공");
					});
				}
			},
			
			omrGichoList:function(){
				var currentPage = $("#currentPage").val();				
				
				$.get("/omrGichoList/"+currentPage).done(function(jsonData){
					if(jsonData){
						var omrGichoesList = jsonData.omrGichoes;
						
						$("#dataList").empty();	
						$("#dataList").css({"border":"1px black solid","width":"905px"});
						$.each(omrGichoesList,function(i,val){
							$("#dataList").append("<div><span>이름 : "+val.mFirstName+", </span> " +
												"<span>hkey : "+val.hkey+", </span> " +
												"<span>과목 : "+val.kwamok+", </span>" +
												"<span>등록일 : "+val.omrDate+", </span>" +
												"<span>처방등급 : "+val.omrGrd+", </span>" +
												"<span>학년 : "+val.omrHak+", </span>" +
												"<span>생년월일 : "+val.omrBirth+"</span>" +
												"</div>");
						});
						
						$("#totalCnt").text('총 '+jsonData.totalCnt+'개');
						$("#logicId").text("sql이용");
						
						$("#pageNavi").html($.paging(jsonData.currentPage, jsonData.pageTotalNum, jsonData.pageBlockSize));	
						
						$(".naviPage").on("click",function() {
							var page = $(this).attr('pageNo');
							$('#currentPage').val(page);
							$.omrGichoList();
						});	
						
					}
				},"json");
			},
			
			spOmrGichoList:function(){
				var currentPage = $("#currentPage").val();	
				
				$.get("/spOmrGichoList/"+currentPage).done(function(jsonData){
					if(jsonData){
						var omrGichoesList = jsonData.spOmrGichoes;
						
						var pageTotalNum = 0;
						var totalCnt = 0;
						
						$("#dataList").empty();	
						$("#dataList").css({"border":"1px black solid","width":"905px"});
						
						$.each(omrGichoesList,function(i,val){
							$("#dataList").append("<div><span>이름 : "+val.mFirstName+", </span> " +
												"<span>hkey : "+val.hkey+", </span> " +
												"<span>과목 : "+val.kwamok+", </span>" +
												"<span>등록일 : "+val.omrDate+", </span>" +
												"<span>처방등급 : "+val.omrGrd+", </span>" +
												"<span>학년 : "+val.omrHak+", </span>" +
												"<span>생년월일 : "+val.omrBirth+"</span>" +
												"</div>");
							
							if(i == 0){
								pageTotalNum = val.totalPageNum;
								totalCnt =  val.totalCnt;
							}
						});
						
						$("#totalCnt").text('총 '+totalCnt+'개');
						$("#logicId").text("sp이용");
						
						$("#pageNavi").html($.paging(jsonData.currentPage, pageTotalNum, jsonData.pageBlockSize));	
						
						$(".naviPage").on("click",function() {
							var page = $(this).attr('pageNo');
							$('#currentPage').val(page);
							$.spOmrGichoList();
						});	
						
					}
				},"json");
			}			
		});
		function validationCheck(){
			var frm = document.omrGichoFrm;
			if(frm.hkey.value.length<1 || frm.hkey.value.length!=8){
				alert('회원코드는 8자리를 입력하여 주십시오');
				frm.hkey.focus();
				return false;
			}else if(is_han(frm.hkey.value)){
				alert('회원코드에는 한글을 입력할 수 없습니다');
				frm.hkey.focus();
				return false;
			}
			
			if(frm.kwamok.value.length!=2||frm.kwamok.value.length<1){
				alert('과목은 2자리를 입력하여 주십시오');
				frm.kwamok.focus();
				return false;
			}
			if(frm.rw.value.length>=1 && frm.rw.value.length!=1){
				alert('rw는 1자리를 입력하여 주십시오');
				frm.rw.focus();
				return false;
			}
			if(frm.nOmr.value.length>=1 && frm.nOmr.value.length!=1){
				alert('학습목표별처방은 1자리를 입력하여 주십시오');
				frm.nOmr.focus();
				return false;
			}
			if(frm.mFirstName.value.length>=1 && frm.mFirstName.value.length>20){
				alert('회원이름(First)는 20자리를 넘을 수 없습니다');
				frm.mFirstName.focus();
				return false;
			}
			if(frm.mLastName.value.length>=1 && frm.mLastName.value.length>20){
				alert('회원이름(Last)는 20자리를 넘을 수 없습니다');
				frm.mLastName.focus();
				return false;
			}
			
			if(frm.skey.value.length>=1 && frm.skey.value.length!=5){
				alert('선생님 코드는 5자리를 입력하여 주십시오');
				frm.skey.focus();
				return false;
			}
			if(frm.sName.value.length>=1 && frm.sName.value.length>20){
				alert('선생님이름은 20자리를 넘을 수 없습니다');
				frm.sName.focus();
				return false;
			}
			if(frm.omrGrd.value.length>=1 && frm.omrGrd.value.length!=1){
				alert('처방 등급은 1자리를 입력하여 주십시오');
				frm.skey.focus();
				return false;
			}
			if(frm.omrHak.value.length>=1 && frm.omrHak.value.length!=2){
				alert('학년은 2자리를 입력하여 주십시오');
				frm.omrHak.focus();
				return false;
			}
			if(frm.omrKind.value.length>=1 && frm.omrKind.value.length!=1){
				alert('처방종류는 1자리를 입력하여 주십시오');
				frm.omrKind.focus();
				return false;
			}
			if(frm.omrDay.value.length>=1 && frm.omrDay.value.length>2){
				alert('관리요일1은 2자리를 넘을 수 없습니다.');
				frm.omrDay.focus();
				return false;
			}
			if(frm.omrDay2.value.length>=1 && frm.omrDay2.value.length>2){
				alert('관리요일2는 2자리를 넘을 수 없습니다.');
				frm.omrDay2.focus();
				return false;
			}
			if(frm.omrCbulsu.value.length>=1 && frm.omrCbulsu.value.length!=1){
				alert('불출수는 1자리를 입력하여 주십시오');
				frm.omrCbulsu.focus();
				return false;
			}
			if(frm.omrBirth.value.length>=1 && frm.omrBirth.value.length!=10){
				alert('생년월일은 10자리를 입력하여 주십시오');
				frm.omrBirth.focus();
				return false;
			}
			if(frm.workID.value.length>=1 && frm.workID.value.length>7){
				alert('작업자는 7자리를 넘을 수 없습니다.');
				frm.workID.focus();
				return false;
			}
			if(frm.branch.value.length>=1 && frm.branch.value.length!=7){
				alert('조직코드는 7자리를 입력하여 주십시오');
				frm.branch.focus();
				return false;
			}
			if(frm.team.value.length>=1 && frm.team.value.length!=2){
				alert('지사코드는 2자리를 입력하여 주십시오');
				frm.team.focus();
				return false;
			}
			if(frm.nDung.value.length>=1 && frm.nDung.value.length!=1){
				alert('처방결과등급은 1자리를 입력하여 주십시오');
				frm.nDung.focus();
				return false;
			}
			if(frm.pan.value.length>=1 && frm.pan.value.length>10){
				alert('처방결과명은 10자리를 넘을 수 없습니다');
				frm.pan.focus();
				return false;
			}
			if(frm.inSta.value.length>=1 && frm.inSta.value.length!=1){
				alert('처방실행완료여부는 1자리를 입력하여 주십시오');
				frm.inSta.focus();
				return false;
			}
			if(frm.omrPath.value.length>=1 && frm.omrPath.value.length>2){
				alert('처방유입경로는 2자리를 넘을 수 없습니다');
				frm.omrPath.focus();
				return false;
			}
		}
	});