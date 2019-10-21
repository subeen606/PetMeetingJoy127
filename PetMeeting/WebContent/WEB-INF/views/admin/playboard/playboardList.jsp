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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/admin_common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/playboard.css">
</head>
<body>

<div id="right-panel" class="right-panel">
	<!--::header part start::-->
		<jsp:include page="/admin_resources/admincss/templates/adminheader.jsp" flush="false"/>
	<!-- Header part end-->

	<!-- Content -->
	<div class="content">
	
		<div class="container">
		
		<div class="mainTitle">소모임 글목록</div>
		
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
				
				<select name="sortingType">
					<option value="선택" selected="selected">전체보기</option>
					<option value="삭제">삭제된 게시글</option>
					<option value="마감">마감된 게시글</option>
				</select>
				
				<div class="searchWrap">
				<select name="searchCategory">
					<option value="선택">검색 분류</option>
					<option value="작성자">작성자(아이디)</option>
					<option value="제목">제목</option>
					<option value="내용">내용</option>
					<option value="장소">장소</option>
				</select>
				<input type="search" name="searchText">
				<button type="button" id="searchBtn">검색</button>
				</div>
			</form>
			
			<form id="delFrm" action="adminPlayboardDelete.do" method="post">
				<table class="boardTable">
					<col width="5%"><col width="5%"><col width="15%"><col width="35%"><col width="20%"><col width="5%"><col width="25%">
					<thead>
						<tr>
							<th><input type="checkbox" name="alldel"></th><th>No.</th><th>모임 유형</th><th>제목</th><th>작성자</th><th>신고 수</th><th>작성일</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${playboardList }" var="pList" varStatus="i">
							<tr>
								<td align="center"><input type="checkbox" name="delcheck" value="${pList.seq }"></td>
								<td align="center">${i.count }</td>
								<td align="center">${pList.category }</td>
								<td style="padding-left: 20px;">
									<a class="titleAnchor" seq="${pList.seq }">
										<c:if test="${pList.deadlineCheck eq true }"><font color="red">[모집 마감]</font></c:if> 
										<c:if test="${pList.del eq 1 }"><font color="red">[게시자에의해 삭제된 게시글]</font></c:if>
										<c:if test="${pList.pdateCheck eq true }"><font color="red">[모임예정일 지남]</font></c:if>
										${pList.title }
									</a>
								</td>
								<td align="center">${pList.email }</td>
								<td align="center">${pList.reportcount }</td>
								<td align="center"><fmt:formatDate value="${pList.regdate }"  pattern="yyyy.MM.dd HH:mm"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			
			<button type="button" id="deleteBtn" onclick="delCheck()">삭제</button>
			
			<div class="pagingWrap">
			<jsp:include page="/WEB-INF/views/admin/playboard/paging.jsp" flush="false">													
					<jsp:param name="pageNumber" value="${searchBean.currPage }" />
					<jsp:param name="totalRecordCount" value="${totalRowCount }" />
					<jsp:param name="pageCountPerScreen" value="10" />
					<jsp:param name="recordCountPerPage" value="10" />
			</jsp:include>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

/* 선택 여부 확인 */
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
	/* 셀렉트 박스 선택 유지 */
	if("${searchBean.playCategory }" == ""){
		$("select[name='playCategory']").val("선택").attr("selected", "selected");
	}else{
		$("select[name='playCategory']").val("${searchBean.playCategory }").attr("selected", "selected");
	}
	$("select[name='searchCategory']").val("${searchBean.searchCategory }").attr("selected", "selected");
	
	if("${searchBean.searchText }" != ""){
		$("input[name='searchText']").val("${searchBean.searchText }");
	}
	
	/* 셀렉트 박스 바뀔 때 마다 검색 및 페이징 되도록 */
	$("select[name='playCategory']").on("change", function () {
		$("input[name='currPage']").val("0");
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
	});
	$("select[name='sortingType']").on("change", function () {
		$("input[name='currPage']").val("0");
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
	});
	$("#searchBtn").click(function () {
		if($("select[name='searchCategory']").val() == "선택"){
			alert("검색 분류를 선택하세요");
			return false;
		}
		if($.trim($("input[name='searchText']").val()) == ""){
			alert("검색내용을 입력해주세요");
			return false;
		}
		
		$("input[name='currPage']").val("0");
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
	});
	
	
	/* 전체선택 및 전체선택 해제 */
	$("input:checkbox[name='alldel']").change(function() {
		if($("input:checkbox[name='alldel']").is(":checked")){
			//alert("체크");
			$("input:checkbox[name='delcheck']").each(function () {
				$(this).prop("checked", true);
			});
		}else{
			//alert("체크 해제");
			$("input:checkbox[name='delcheck']").each(function () {
				$(this).prop("checked", false);
			});
		}
	});
	
	/* 선택삭제 */
	$("#deleteBtn").click(function () {
		var check = confirm("선택하신 글들을 삭제하시겠습니까?");
		if(check){
			$("#delFrm").submit();
		}else{
			return false;
		}
		
	});
	
	$(".titleAnchor").click(function () {
		location.href="adminPlayboardDetail.do?seq="+$(this).attr("seq");
	});

});

/* 페이징 함수 */
function goPage( pageNumber ) {
	$("input[name='currPage']").val(pageNumber);
	$("#searchFrm").attr({"action":"adminPlayboardList.do", "method":"post"}).submit();
}
</script>
</body>
</html>