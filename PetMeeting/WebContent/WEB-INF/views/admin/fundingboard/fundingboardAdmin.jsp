<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>
<style type="text/css">
table,tr,th,td { border: 1px solid;}

</style>

</head>
<body>
<a href="fundingWrite.do">funding 만들기</a>
<br><br><br><br><br>

<select id="selec" onchange="sele()">
	<option value="" <c:if test="${f_categorys == null }">selected</c:if>>전체목록</option>
	<option value="진행" <c:if test="${f_categorys == '진행' }">selected</c:if>>진행중</option>
	<option value="마감" <c:if test="${f_categorys == '마감' }">selected</c:if>>마감</option>
</select>


<table>
	<c:forEach items="${flist }" var="list" varStatus="vs">
		<tr>
			<th><input type="checkbox" name="fundDel"></th>
			<th>No.</th>
			<th>후원명</th>
			<th>후원내용</th>
			<th>후원기간</th>
			<th>참여인원</th>
			<th>진행/마감</th>
			<th>수정</th>
			<th>후원내역서</th>
		</tr>
		<tr>
			<td></td>
		</tr>
	
	
	</c:forEach>
</table>



<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber }">
<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?0:recordCountPerPage }">

	
<!-- 페이징 -->
<div id="paging_wrap">
	<!-- jsp 태그안에 include태그로 pagging.jsp불러오기 / flush=false는 다시읽지마라 한번만 가지고와라의 의미-->
	<jsp:include page="/WEB-INF/views/admin/playboard/paging.jsp" flush="false">
		<jsp:param  name="pageNumber" value="${pageNumber }"/>
		<jsp:param  name="totalRecordCount" value="${totalRecordCount }"/>
		<jsp:param  name="pageCountPerScreen" value="${pageCountPerScreen }"/>
		<jsp:param  name="recordCountPerPage" value="${recordCountPerPage }"/>
	</jsp:include>
</div>

<script type="text/javascript">
function sele(){
	alert($("#selec").val());
}

</script>

</body>
</html>