<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Meeting - 신고사유</title>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	
	<link rel="icon" href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/reportReason.css?after">
</head>
<body>

<div class="mainTitle">
<img src="${pageContext.request.contextPath}/playboard_resources/img/siren.png" width="30px" height="30px" style="vertical-align: sub">신고내역
</div>

<div class="reasonWrap">
<c:if test="${empty board_reasons && empty  member_reasons }">
	<div class="emptyReport">
	<img src="${pageContext.request.contextPath}/admin_resources/img/exclamation-mark.png" width="30px" height="30px" style="vertical-align: top;">
	신고 내역이 존재하지 않습니다.
	</div>
</c:if> 

<c:if test="${not empty board_reasons }">
<form id="boardReportDeleteFrm">
	<input type="hidden" name="seq">
	<input type="hidden" name="board_seq" value="${board_seq }">
	<input type="hidden" name="board_code" value="${board_code }">
</form>
	<c:forEach items="${board_reasons }" var="reason" varStatus="i">
		<table class="reasonTable">
		
			<tr>
				<th>${i.count }</th>
				<td>
				<button type="button" class="boardReportCancelBtn" seq="${reason.seq }">신고 삭제</button>
				</td>
			</tr>
			<tr>
				<th>신고자</th>
				<td>${reason.email }(${reason.nickname })</td>
			</tr>
			<tr>
				<th>신고 사유</th>
				<td>
					<c:if test="${reason.reason eq '홍보'}">
						홍보성 게시글
					</c:if>
					<c:if test="${reason.reason eq '음란'}">
						음란성 또는 청소년에게 부적합한 게시글
					</c:if>
					<c:if test="${reason.reason eq '비방'}">
						비방 또는 심한 욕설사용
					</c:if>
					<c:if test="${reason.reason ne '홍보' && reason.reason ne '음란' && reason.reason ne '비방' }">
						${reason.reason }
					</c:if>			
				</td>
			</tr>
		</table>
	</c:forEach>
</c:if>

<c:if test="${not empty member_reasons }">
<form id="memberReportDeleteFrm">
	<input type="hidden" name="seq">
	<input type="hidden" name="bad_email" value="${bad_email }">
</form>
	<c:forEach items="${member_reasons }" var="reason" varStatus="i">
		<table class="reasonTable">
			<tr>
				<th>${i.count }</th>
				<td>				
				<button type="button" class="memberReportCancelBtn" seq="${reason.seq }">신고 삭제</button>
				</td>
			</tr>
			<tr>
				<th>신고자</th>
				<td>${reason.email }(${reason.nickname })</td>
			</tr>
			<tr>
				<th>신고 사유</th>
				<td>
					<c:if test="${reason.reason eq '홍보'}">
						홍보성 게시글
					</c:if>
					<c:if test="${reason.reason eq '음란'}">
						음란성 또는 청소년에게 부적합한 게시글
					</c:if>
					<c:if test="${reason.reason eq '비방'}">
						비방 또는 심한 욕설사용
					</c:if>
					<c:if test="${reason.reason ne '홍보' && reason.reason ne '음란' && reason.reason ne '비방' }">
						${reason.reason }
					</c:if>			
				</td>
			</tr>
		</table>
	</c:forEach>
</c:if>

</div>

<script type="text/javascript">
$(function() {
	$(".boardReportCancelBtn").click(function () {
		$("#boardReportDeleteFrm input[name='seq']").val($(this).attr("seq"));
		var check = confirm("해당 신고를 삭제하시겠습니까?");
		if(check){
			$("#boardReportDeleteFrm").attr({"action":"adminBoardReportDelete.do", "method":"post"}).submit();
			window.opener.document.location.reload(true);
		}		
	});
	
	$(".memberReportCancelBtn").click(function () {
		$("#memberReportDeleteFrm input[name='seq']").val($(this).attr("seq"));
		
		var check = confirm("해당 신고를 삭제하시겠습니까?");
		if(check){
			$("#memberReportDeleteFrm").attr({"action":"adminMemberReportDelete.do", "method":"post"}).submit();
		}
		
	});
});
</script>

</body>
</html>