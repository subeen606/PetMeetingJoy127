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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/playboard.css?after">
</head>
<body>

<form id="searchFrm">
<input type="hidden" name="startRow" value="${searchBean.startRow }">
<input type="hidden" name="endRow" value="${searchBean.endRow }">
<input type="hidden" name="currPage" value="${searchBean.currPage }">
	<select name="playCategory">
		<option value="선택" selected="selected">모임유형</option>
		<option value="산책">산책</option>
		<option value="봉사">봉사</option>
		<option value="원데이 클래스">원데이 클래스</option>
		<option value="동호회">동호회</option>
		<option value="기타">기타</option>
	</select>
	
	<select name="searchCategory">
		<option value="선택" selected="selected">검색분류</option>
		<option value="삭제">삭제된 게시글</option>
		<option value="마감">마감된 게시글</option>
	</select>
</form>

<form id="delFrm" action="adminPlayboardDelete.do" method="post">
<table class="boardTable">

	<thead>
		<tr>
			<th><input type="checkbox" name="alldel"></th><th>No.</th><th>제목</th><th>작성자</th><th>작성일</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${playboardList }" var="pList" varStatus="i">
			<tr>
				<td align="center"><input type="checkbox" name="delcheck" value="${pList.seq }"></td>
				<td align="center">${i.count }</td>
				<td>${pList.title }</td>
				<td align="center">${pList.email }</td>
				<td align="center"><fmt:formatDate value="${pList.regdate }"  pattern="yyyy.MM.dd HH:mm"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
<button type="button" id="deleteBtn" onclick="delCheck()">삭제</button>

<jsp:include page="/WEB-INF/views/admin/playboard/paging.jsp" flush="false">													
		<jsp:param name="pageNumber" value="${searchBean.currPage }" />
		<jsp:param name="totalRecordCount" value="${totalRowCount }" />
		<jsp:param name="pageCountPerScreen" value="10" />
		<jsp:param name="recordCountPerPage" value="10" />
</jsp:include>

<script type="text/javascript">

function delCheck() {	
	var checklen = $("input:checkbox[name='delcheck']:checked").length;
	if(checklen == 0 ){
		alert("삭제할 게시글을 선택해 주세요");
		return false;
	}
	else {		
		return true;
	}
}

$(function () {
	
	if("${searchBean.playCategory }" == ""){
		$("select[name='playCategory']").val("선택").attr("selected", "selected");
	}else{
		$("select[name='playCategory']").val("${searchBean.playCategory }").attr("selected", "selected");
	}

	$("select[name='searchCategory']").val("${searchBean.searchCategory }").attr("selected", "selected");
	
	$("select[name='playCategory']").on("change", function () {
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
	});
	$("select[name='searchCategory']").on("change", function () {
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
	});
	
	$("input:checkbox[name='alldel']").change(function() {
		if($("input:checkbox[name='alldel']").is(":checked")){
			//alert("체크");
			$("input:checkbox[name='delcheck']").each(function () {
				$(this).attr("checked", true);
			});
		}else{
			//alert("체크 해제");
			$("input:checkbox[name='delcheck']").each(function () {
				$(this).attr("checked", false);
			});
		}
	});
	
	$("#deleteBtn").click(function () {
		$("#delFrm").submit();
	});

});

function goPage( pageNumber ) {
	$("input[name='currPage']").val(pageNumber);
	$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
}
</script>
</body>
</html>