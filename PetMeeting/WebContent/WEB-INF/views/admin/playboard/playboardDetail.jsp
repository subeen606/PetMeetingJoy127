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

<table class="detailTable">
	<tr>
		<th>소모임 유형</th><td>${detail.category }</td>
	</tr>
	<tr>
		<th>제목</th><td>${detail.title }</td>
	</tr>
	<tr>
		<th>작성자</th><td>${detail.email }(${detail.nickname })</td>
	</tr>
	
	<tr>
		<th>작성일</th><td><fmt:formatDate value="${detail.regdate }"  pattern="yyyy.MM.dd HH:mm"/></td>
	</tr>
	<tr>
		<th>모임 예정일</th><td><fmt:formatDate value="${detail.pdate }"  pattern="yyyy.MM.dd"/></td>
	</tr>
	<tr>
		<th>모집 마감일</th><td><fmt:formatDate value="${detail.edate }"  pattern="yyyy.MM.dd"/></td>
	</tr>
</table>

</body>
</html>