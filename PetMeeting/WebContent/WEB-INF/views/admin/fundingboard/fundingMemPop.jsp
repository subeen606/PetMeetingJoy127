<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/admin_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/fundingboard.css">
</head>
<body>

<div class="container">

<div class="Title">[ ${dto.title } ]</div>
<div id="fundMem_list">후원회원 리스트</div>	

<table class="boardTable">
	<thead>
		<tr id="poptr">
			<th>No.</th>
			<th>EMAIL</th>
			<th>NICNAME</th>
			<th>후원금</th>
			<th>후원일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty mlist }">
			<tr>
				<td colspan="6">후원한 회원이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!empty mlist }">
		<c:forEach items="${mlist }" var="list" varStatus="vs">
			<tr>
				<td>${vs.count }</td>
				<td>${list.email }</td>
				<td>${list.nickname }</td>
				<td><fmt:formatNumber value="${list.donation }" pattern="#,###원"/></td>
				<td><fmt:formatDate pattern="MM월 dd일" value="${list.d_date }"/></td>
			</tr>
		</c:forEach>
		</c:if>
	</tbody>
</table>
</div>

</body>
</html>