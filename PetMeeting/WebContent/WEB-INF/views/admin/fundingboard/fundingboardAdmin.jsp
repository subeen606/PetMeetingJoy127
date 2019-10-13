<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<title>admin</title>

<style type="text/css">
table,tr,th,td { border: 1px solid;}

.listicon{
	max-width: 100%;
    width: 23px;
}
</style>

</head>
<body>

<jsp:useBean id="dates" class="com.petmeeting.joy.funding.util.DateUtil"/>

<a href="fundingWrite.do">funding 만들기</a>
<br><br><br><br><br>

<form id="frm">

	<select id="selec" onchange="sele()">
		<option value="전체" <c:if test="${f_categorys == '전체' }">selected</c:if>>전체목록</option>
		<option value="진행" <c:if test="${f_categorys == '진행' }">selected</c:if>>진행중인 후원</option>
		<option value="마감" <c:if test="${f_categorys == '마감' }">selected</c:if>>마감된 후원</option>
	</select>


	<table>
			<tr>
				<th><input type="checkbox" name="allfundDel"></th>
				<th>No.</th>
				<th>후원명</th>
				<th>후원내용</th>
				<th>후원기간</th>
				<th>진행률</th>
				<th>진행/마감</th>
				<th>수정/내역서</th>
			</tr>
		<c:forEach items="${flist }" var="list" varStatus="vs">
			<tr>
				<td><input type="checkbox" class="check" name="seq" value="${list.seq }"></td>
				<td>${vs.count}</td>
				<td>${list.title }</td>
				<td>${list.intro }</td>
				<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.sdate }"/> ~ <fmt:formatDate pattern="yyyy/MM/dd" value="${list.edate }"/></td>
				<td>${dates.PercentP(list.current_price,list.max_price)}%</td>
				<c:if test="${dates.isEnd(list.edate) eq 'true' || list.current_price eq list.max_price}">
					<td>마감</td>
					<td><a href="statement.do?board_seq=${list.seq }&seq=${list.seq }&email=${login.email}"><img class="listicon" src="./fundingboard_resources/img/notepad.png"></a></td>
				</c:if>
				<c:if test="${dates.isEnd(list.edate) eq 'false'}">
					<c:if test="${list.current_price lt list.max_price}">
						<td>진행</td>
						<td><a href="fundUpdate.do?seq=${list.seq}">수정</a></td>
					</c:if>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber }">
	<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?0:recordCountPerPage }">
	
</form>

<input id="delBtn" type="button" value="삭제">

<!-- 페이징 -->
<div id="paging_wrap">
	<jsp:include page="/WEB-INF/views/admin/playboard/paging.jsp" flush="false">
		<jsp:param  name="pageNumber" value="${pageNumber }"/>
		<jsp:param  name="totalRecordCount" value="${totalRecordCount }"/>
		<jsp:param  name="pageCountPerScreen" value="${pageCountPerScreen }"/>
		<jsp:param  name="recordCountPerPage" value="${recordCountPerPage }"/>
	</jsp:include>
</div>

<script type="text/javascript">

function sele(){
	location.href="adminFundingList.do?f_categorys="+$("#selec").val();
}

/*페이징 번호*/
function goPage( pageNumber ) {
	//alert(pageNumber);
	$("#_pageNumber").val(pageNumber);
	$("#frm").attr("action","adminFundingList.do").submit();
}


/*전체 체크박스*/
$("input[name='allfundDel']").click(function() {
	if($("input[name='allfundDel']").prop("checked")){
		$("input.check").prop("checked",true);
	}else{
		$("input.check").prop("checked",false);
	}

	$("input.check").click(function() {
		alert($(this).val());
		$("input[name='allfundDel']").prop("checked",false);
	});
});

/* $("input.check").click(function() {
	alert($(this).val());
}); */

/*삭제버튼*/
$("#delBtn").on('click',function(){
	alert("삭제합네다");
	var checks = $("input[name='seq']");
	var isChecked = false;
	
	for(var i = 0 ;  i < checks.length; i++){
		if(checks.eq(i).prop("checked")){
			isChecked = true;
		}
	}
	if(!isChecked){
		alert("삭제할 게시글을 선택해주세요");
		return;
	}
	$("#frm").attr("action","fundDelete.do").submit();
});

</script>

</body>
</html>