<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PetMeeting-신고하기</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<link rel="icon" href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/report.css">
</head>
<body>

<div class="container">
<form id="reportFrm">
<input type="hidden" name="seq" value="0">
<input type="hidden" name="board_seq" value="${detail.seq }">
<input type="hidden" name="email" value="${login.email }">
	<div class="row">
		<img src="${pageContext.request.contextPath}/playboard_resources/img/siren.png" width="30px" height="30px" style="transform: translate(5px, 10px);">&nbsp;&nbsp;<h3>신고하기</h3>
	</div>
	<div class="line"></div>
	<div class="row">
		<div class="title">
			작성자
		</div>
		<div class="content">
			${detail.nickname }(${detail.email })
		</div>
	</div>
	
	<div class="row">
		<div class="title">
			제목
		</div>
		<div class="content">
			[${detail.category }] ${detail.title }
		</div>
	</div>
	
	<div class="line"></div>
	
	<div class="row">
		<div class="title">
			신고 사유
		</div>
		<div class="content-reason">
			<label><input type="radio" name="reason" value="홍보">홍보성 게시글<br></label>
			<label><input type="radio" name="reason" value="음란">음란성 또는 청소년에게 부적합한 게시글<br></label>
			<label><input type="radio" name="reason" value="비방">비방 또는 심한 욕설사용<br></label>
			<label><input type="radio" name="reason" value="기타">기타 사유</label>
			<textarea rows="3" id="_reasonTxt" name="reasonTxt"></textarea>
		</div>
	</div>
	
	<div class="row">
		<button id="reportBtn">신고하기</button>
	</div>
	</form>
</div>

<script type="text/javascript">
$(function () {
	
	

	$("input[name='reason']").on("change", function () {
		if($("input[name='reason']:checked").val() == "기타"){
			$("#_reasonTxt").css("display","block");
		}else{
			$("#_reasonTxt").css("display","none");
		}
	});
	
	$("#reportBtn").click(function () {
		
		var checked = $("input[name='reason']:checked").length;
		if(checked == 0){
			alert("신고 사유를 선택하십시오.");
			return false;
		}
		
		if($("input[name='reason']:checked").val() == "기타" && $.trim($("#_reasonTxt").val()) == ""){
			alert("신고 사유를 작성하십시오.");
			return false;
		}
		
		var check = confirm("신고는 취소하실 수 없습니다. 신고하시겠습니까?\n(무분별한 신고는 운영자에 의해 제재될 수 있습니다.)");
		if(check){
			
			
			//alert(checked);			
			
				$("#reportFrm").attr("action", "reportAf.do").submit();	
				window.opener.document.location.reload(true);
				self.close();	
				
				 
			 /*
			 var formdata = $("#reportFrm").serialize();
			 $.ajax({
				url: 'reportAf.do',
				data: formdata,
				datatype: 'json',
				async: false,
				type: 'post',
				success: function () {
					alert("성공");
					window.opener.location.href="detailPlay.do?seq="+${detail.seq };
					window.self.close();	
				},
				errer: function () {
					alert("에러");
				}
			 });
			 */
		}else{
			self.close();	
		}
	});
});
</script>

</body>
</html>