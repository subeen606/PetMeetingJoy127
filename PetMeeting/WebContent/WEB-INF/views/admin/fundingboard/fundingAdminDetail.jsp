<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/admin_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/fundingboardDetail.css">
</head>
<body>
<div id="right-panel" class="right-panel">
	<!--::header part start::-->
		<jsp:include page="/admin_resources/admincss/templates/adminheader.jsp" flush="false"/>
	<!-- Header part end-->

<!-- Content -->
<div class="content">
<div class="container">
<div class="mainTitle">후원 상세보기</div>

<jsp:useBean id="dates" class="com.petmeeting.joy.funding.util.DateUtil"/>

<form id="frm">
<input type="hidden" id="seq" name="seq" value="${dto.seq }">
	<table class="detailTable">
		<tr>
			<th>후원명</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>후원일정</th>
			<td><fmt:formatDate pattern="yyyy년  MM월 dd일" value="${dto.sdate }"/> ~ <fmt:formatDate pattern="yyyy년  MM월 dd일" value="${dto.edate }"/> </td>
		</tr>
		<tr>
			<th>후원소개</th>
			<td>${dto.intro }</td>
		</tr>
		<tr>
			<th>목표금액</th>
			<td><fmt:formatNumber value="${dto.max_price }" pattern="#,###"/> 원 </td>
		</tr>
		<tr>
			<th>후원받은 금액</th>
			<td><fmt:formatNumber value="${dto.current_price }" pattern="#,###"/> 원</td>
		</tr>
		<tr>
			<th>좋아요</th>
			<td>${dto.likecount } 개</td>
		</tr>
		<tr>
			<th>후원자</th>
			<td>${dto.personcount } 명</td>
		</tr>
		<tr>
			<th>후원한 회원</th>
			<td><a id="fundMemlist" href="javascript:popupOpen();" >후원회원 List</a></td>
		</tr>
		<tr>
			<th colspan="2">후원내용</th>
		</tr>
		<tr>
			<td colspan="2" id="deContent" >${dto.content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<!-- 마감된 후원은 삭제버튼 / 진행중인 후원은 수정버튼 생성  -->
				<c:if test="${dates.isEnd(dto.edate) eq 'true' || dto.current_price eq dto.max_price}">
					<input type="button" id="delBtn" value="삭제">
				</c:if>
				<c:if test="${dates.isEnd(dto.edate) eq 'false'}">
					<c:if test="${dto.current_price lt dto.max_price}">
						<input type="button" id="upBtn" value="수정">
					</c:if>
				</c:if>
				<input type="button" id="listBtn" value="목록">			
			</td>
		</tr>
	</table>
</form>

</div>
</div>
</div>

<script type="text/javascript">
$("#delBtn").click(function(){
	if (confirm("삭제하시겠습니까?") == true){    //확인
		location.href="adminFundDelete.do?seq="+$("#seq").val();
	}else{   //취소
	    return;
	}
});

$("#upBtn").click(function(){
	$("#frm").attr("action","fundUpdate.do").submit();
	
});

$("#listBtn").click(function(){
	location.href="adminFundingList.do";
});

function popupOpen(){
	window.open('fundingMempop.do?seq='+$("#seq").val(),"후원하기","width=710,height=410,left=200,top=100");
	}

</script>


</body>
</html>