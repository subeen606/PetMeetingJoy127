<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Meeting - 관리자</title>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	
	<link rel="icon" href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/playboardDetail.css">
</head>
<body>
<div class="container">
<div class="mainTitle">소모임 상세보기</div>
	<table class="detailTable">
		<tr>
			<th>소모임 유형</th><td>${detail.category }</td>
		</tr>
		<tr>
			<th>제목</th><td>${detail.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
			<c:if test="${empty profile || empty profile.myprofile_img }">
				<img src="${pageContext.request.contextPath}/playboard_resources/img/user.png" width="50px" height="50px" style="vertical-align: middle">
			</c:if>
			<c:if test="${not empty profile || not empty profile.myprofile_img }">
				<img id="profilePic" src="${pageContext.request.contextPath}/playboard_resources/img/brandon.jpg">
			</c:if>
			${detail.email }(${detail.nickname })
			</td>
		</tr>
		
		<tr>
			<th>작성일</th><td><fmt:formatDate value="${detail.regdate }"  pattern="yyyy년 MM월 dd일 HH시 mm분"/></td>
		</tr>
		<tr>
			<th>모임 예정일</th><td><fmt:formatDate value="${detail.pdate }"  pattern="yyyy년 MM월 dd일"/></td>
		</tr>
		<tr>
			<th>모집 마감일</th><td><fmt:formatDate value="${detail.edate }"  pattern="yyyy년 MM월 dd일"/></td>
		</tr>
		<tr>
			<th>모임 장소</th><td>${detail.location } ${detail.location_detail }</td>
		</tr>
		<tr>
			<th>태그</th>
			<td>
				<c:if test="${not empty hashs.hash1 }">#${hashs.hash1 }</c:if>	
				<c:if test="${not empty hashs.hash2 }">#${hashs.hash2 }</c:if>	
				<c:if test="${not empty hashs.hash3 }">#${hashs.hash3 }</c:if>	
				<c:if test="${not empty hashs.hash4 }">#${hashs.hash4 }</c:if>	
				<c:if test="${not empty hashs.hash5 }">#${hashs.hash5 }</c:if>	
			</td>
		</tr>
		<tr>
			<th>좋아요</th><td>${detail.likecount }</td>
		</tr>
		<tr>
			<th>신고</th><td>${detail.reportcount }&emsp;<button type="button" id="reportResaonBtn">신고내역 보기</button></td>
		</tr>
		<tr>
			<th>참가자 목록</th>
			<td>
				<c:if test="${empty partList }">
  					<font color="#379392">현재 참여자가 없습니다.</font>
  				</c:if>	
  				<c:if test="${not empty partList }">
  					<div class="playPartMems">				
		   				<div class="memberProfile">	   					
			   				<c:forEach items="${partList }" var="partMem">		   						   						   					
			   						<c:if test="${empty partMem.memProfile.myprofile_img }">
			   							<div class="memberProfileCell">
			   							<img src="${pageContext.request.contextPath}/playboard_resources/img/user.png" width="40px" height="40px" style="vertical-align: middle;">&nbsp;&nbsp;
			   							${partMem.email }(${partMem.nickname })
			   							</div>
			   						</c:if>			
			   						
			   						<c:if test="${not empty partMem.memProfile.myprofile_img }">
			   							<div class="memberProfileCell">
			   							<img class="partMemProfilePic" src="${pageContext.request.contextPath}/playboard_resources/img/bakar.jpg">&nbsp;&nbsp;		   							
			   							${partMem.email }(${partMem.nickname })
			   							</div>
			   						</c:if>	   						   				
			   				</c:forEach>
		   				</div>
		   			</div>
  				</c:if>					
			</td>
		</tr>
		<tr>
			<th colspan="2">상세 내용</th>
		</tr>
		<tr>
			<td colspan="2"><div>${detail.content }</div></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><button type="button" id="deleteBtn">삭제</button></td>
		</tr>
	</table>
</div>

<script type="text/javascript">
$(function () {
	$("#reportResaonBtn").click(function () {
		if("${detail.reportcount }" == "0"){
			alert("신고 내역이 없습니다");
		}else{
			var option = "width = 550, height = 500, top = 100, left = 300, location = no, resizeable = no";
			window.open("adminBoardReportReason.do?seq=+${detail.seq }"+"&board_code=PLAY", "report_reason", option);
		}
		
	});
});
</script>

</body>
</html>