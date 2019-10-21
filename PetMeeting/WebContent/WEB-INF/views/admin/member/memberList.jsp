<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/memberList.css?after">

</head>
<body>
<div id="right-panel" class="right-panel">
	<!--::header part start::-->
		<jsp:include page="/admin_resources/admincss/templates/adminheader.jsp" flush="false"/>
	<!-- Header part end-->

	<!-- Content -->
	<div class="content">
	
		<div class="container">
		<div class="mainTitle">회원 목록</div>
		
			<form id="searchFrm">
			<input type="hidden" name="start" value="${searchBean.start }">
			<input type="hidden" name="end" value="${searchBean.end }">
			<input type="hidden" name="currPage" value="${searchBean.currPage }">
				<select name="sortingType">
					<option value="전체">전체보기</option>
					<option value="신고">신고된 회원</option>
				</select>
				
				<select name="grade">
					<option value="전체">등급별 보기</option>
					<option value="new">NEW</option>
					<option value="family">FAMILY</option>
					<option value="vip">VIP</option>
					<option value="vvip">VVIP</option>
				</select>
				
				<div class="searchWrap">
				<select name="search_category">
					<option value="선택">검색 분류</option>
					<option value="이메일">이메일</option>
					<option value="닉네임">닉네임</option>
				</select>
				<input type="search" name="search_txt">
				<button type="button" id="searchBtn">검색</button>
				</div>
			</form>
			
			<form id="leaveMemberFrm" action="adminLeaveMember.do" method="post">
			<table class="boardTable">
					<col width="5%"><col width="23%"><col width="23%"><col width="10%"><col width="10%"><col width="10%"><col width="14%">
					<thead>
						<tr>
							<th><input type="checkbox" name="allmem"></th><th>E-Mail</th><th>닉네임</th><th>등급</th><th>신고 수</th><th>활동중지 여부</th><th>회원정보 보기</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${memberList }" var="memList" varStatus="i">
							<tr <c:if test="${memList.reportcount > 0 }">style="background-color: #fff5f2;"</c:if>>
								<td align="center"><input type="checkbox" name="memcheck" value="${memList.email }"></td>							
								<td align="center">${memList.email }</td>
								<td align="center">${memList.nickname }</td>
								<td align="center">
									<img src="./admin_resources/img/${memList.grade_name }_icon.png" width="30px" style="vertical-align: middle" title="회원등급: ${memList.grade_name }"> 
								</td>							
								<td align="center">																	
									<font <c:if test="${memList.reportcount > 0 }">color="red"</c:if>>
									${memList.reportcount }</font>
								</td>
								<td align="center">
									<c:if test="${memList.leavememberCheck eq true }">
										<font class="leaveMem">활동중지</font>
									</c:if>
								</td>
								<td align="center"><button type="button" class="infoBtn" email="${memList.email }"></button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</form>
				
				<button type="button" id="leaveBtn" onclick="memCheck()">활동중지</button>
				
				<div class="pagingWrap">
					<jsp:include page="/WEB-INF/views/admin/member/paging.jsp" flush="false">													
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
$(function () {
	/* 선택 유지 */
	if("${searchBean.sortingType }" == ""){
		$("select[name='sortingType']").val("전체").attr("selected", "selected");
	}else{
		$("select[name='sortingType']").val("${searchBean.sortingType }").attr("selected", "selected");
	}	
	if("${searchBean.grade }" == ""){
		$("select[name='grade']").val("전체").attr("selected", "selected");
	}else{
		$("select[name='grade']").val("${searchBean.grade }").attr("selected", "selected");
	}
	$("select[name='search_category']").val("${searchBean.search_category }").attr("selected", "selected");
	
	/* 정렬 */
	$("select[name='sortingType']").on("change", function () {
		$("input[name='currPage']").val("0");
		$("input[name='start']").val("0");
		$("input[name='end']").val("0");
		$("#searchFrm").attr({"action":"adminMemberList.do", "method":"post"}).submit();
	});
	$("select[name='grade']").on("change", function () {
		$("input[name='currPage']").val("0");
		$("input[name='start']").val("0");
		$("input[name='end']").val("0");
		$("#searchFrm").attr({"action":"adminMemberList.do", "method":"post"}).submit();
	});
	
	/* 검색 */
	$("#searchBtn").on("click", function () {
		if($.trim($("input[name='search_txt']").val()).length == 0){
			alert("검색어를 입력하십시오");
			$("input[name='search_txt']").focus();
			return false;
		}
		$("input[name='currPage']").val("0");
		$("input[name='start']").val("0");
		$("input[name='end']").val("0");
		$("#searchFrm").attr({"action":"adminMemberList.do", "method":"post"}).submit();
	});
		
	/* 전체선택 및 전체선택 해제 */
	$("input:checkbox[name='allmem']").change(function() {
		if($("input:checkbox[name='allmem']").is(":checked")){
			//alert("체크");
			$("input:checkbox[name='memcheck']").each(function () {
				$(this).prop("checked", true);
			});
		}else{
			//alert("체크 해제");
			$("input:checkbox[name='memcheck']").each(function () {
				$(this).prop("checked", false);
			});
		}
	});
	
	$("#leaveBtn").click(function () {
		var check = confirm("선택하신 회원들을 활동중지 하시겠습니까?");
		if(check){
			$("#leaveMemberFrm").submit();
		}else{
			return false;
		}
		
	});
	
	$(".infoBtn").click(function () {		
		var option = "width = 700, height = 800, top = 100, left = 300, location = no, resizeable = no";
		window.open("adminMemberDetail.do?email="+$(this).attr("email"), "memberDetail", option);
	});
	
});

/* 선택 여부 확인 */
function memCheck() {	
	var checklen = $("input:checkbox[name='memcheck']:checked").length;
	if(checklen == 0 ){
		alert("활동중지할 회원을 선택해 주세요");
		return false;
	}
	else {		
		return true;
	}
}

/* 페이징 함수 */
function goPage( pageNumber ) {
	$("input[name='currPage']").val(pageNumber);
	$("#searchFrm").attr({"action":"adminMemberList.do", "method":"post"}).submit();
}
</script>
</body>
</html>