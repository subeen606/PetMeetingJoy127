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
<c:if test="${empty reasons }">
	<div class="emptyReport">
	<img src="${pageContext.request.contextPath}/admin_resources/img/exclamation-mark.png" width="30px" height="30px" style="vertical-align: top;">
	신고 내역이 존재하지 않습니다.
	</div>
</c:if> 

<c:forEach items="${reasons }" var="reason" varStatus="i">
	<table class="reasonTable">
		<tr>
			<th>${i.count }</th>
			<td>
			<form id="reportDeleteFrm">
				<input type="hidden" name="seq" value="${reason.seq }">
				<input type="hidden" name="board_seq" value="${reason.board_seq }">
				<input type="hidden" name="board_code" value="${reason.board_code }">
			</form>
			<button type="button" class="reportCancelBtn">신고 삭제</button>
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
					홍보성 게시글
				</c:if>
				<c:if test="${reason.reason eq '비방'}">
					홍보성 게시글
				</c:if>
				<c:if test="${reason.reason ne '홍보' && reason.reason ne '음란' && reason.reason ne '비방' }">
					${reason.reason }
				</c:if>			
			</td>
		</tr>
	</table>
</c:forEach>

</div>

<script type="text/javascript">
$(function() {
	$(".reportCancelBtn").click(function () {
		var check = confirm("해당 신고를 삭제하시겠습니까?");
		if(check){
			$("#reportDeleteFrm").attr({"action":"adminBoradReportDelete.do", "method":"post"}).submit();
			window.opener.document.location.reload(true);
		}		
	});
});
</script>

</body>
</html>