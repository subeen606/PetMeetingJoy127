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
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/admin_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/fundingboard.css">
</head>
<body>

<div id="right-panel" class="right-panel">
	<!--::header part start::-->
		<jsp:include page="/admin_resources/admincss/templates/adminheader.jsp" flush="false"/>
	<!-- Header part end-->
<!-- Content -->
<div class="content">
<div class="container">

<div class="mainTitle">후원 목록</div>	
		<jsp:useBean id="dates" class="com.petmeeting.joy.funding.util.DateUtil"/>
		

		<input type="button" id="makefund" value="funding 만들기" onclick="location.href='fundingWrite.do'">
		
		<form id="frm">
		
			<select id="selec" name= "f_categorys" onchange="sele()">
				<option value="전체" <c:if test="${f_categorys == '전체' }">selected</c:if>>전체목록</option>
				<option value="진행" <c:if test="${f_categorys == '진행' }">selected</c:if>>진행중인 후원</option>
				<option value="마감" <c:if test="${f_categorys == '마감' }">selected</c:if>>마감된 후원</option>
			</select>
		
		
			<table class="boardTable">
				
				<thead>
					<tr>
						<th><input type="checkbox" name="allfundDel"></th>
						<th>No.</th>
						<th>후원명</th>
						<th>후원내용</th>
						<th>후원기간</th>
						<th>진행률</th>
						<th>진행/마감</th>
						<th>후원내역서</th>
					</tr>
				</thead>
				
				<tbody>
				<c:if test="${empty flist }">
					<tr>
					<td colspan="7">해당 후원이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${flist }" var="list" varStatus="vs">
					<tr>
						<td><input type="checkbox" class="check" name="seq" value="${list.seq }"></td>
						<td>${vs.count}</td>
						<td>
						<a class="titleAnchor" href="adminFundingDetail.do?seq=${list.seq }"> ${list.title } </a>
						</td>
						<td>${list.intro }</td>
						<td><fmt:formatDate pattern="yyyy/MM/dd" value="${list.sdate }"/> ~ <fmt:formatDate pattern="yyyy/MM/dd" value="${list.edate }"/></td>
						<td>${dates.PercentP(list.current_price,list.max_price)}%</td>
						<c:if test="${dates.isEnd(list.edate) eq 'true' || list.current_price eq list.max_price}">
							<td id="end">마감</td>
							<c:if test="${list.isfundingsta eq 'true' }">	
								<td><a href="javascript:popup(${list.seq });"><img class="listicon" src="./fundingboard_resources/img/fileD.png"></a></td>
							</c:if>
							<c:if test="${list.isfundingsta eq 'false' }">	
								<td><a href="statement.do?board_seq=${list.seq }&seq=${list.seq }&email=${login.email}"><img class="listicon" src="./fundingboard_resources/img/fileP.png"></a></td>
							</c:if>
						</c:if>
						<c:if test="${dates.isEnd(list.edate) eq 'false'}">
							<c:if test="${list.current_price lt list.max_price}">
								<td id="ing">진행</td>
								<td> <img class="listicon" src="./fundingboard_resources/img/fileX.png"> </td>
							</c:if>
						</c:if>
					</tr>
				</c:forEach>
				</tbody>	
			</table>
			
			<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber }">
			<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?0:recordCountPerPage }">
			
		</form>
		
		<input id="deleteBtn" type="button" value="삭제">
		
		
		<!-- 페이징 -->
		<div id="paging_wrap">
			<jsp:include page="/WEB-INF/views/admin/playboard/paging.jsp" flush="false">
				<jsp:param  name="pageNumber" value="${pageNumber }"/>
				<jsp:param  name="totalRecordCount" value="${totalRecordCount }"/>
				<jsp:param  name="pageCountPerScreen" value="${pageCountPerScreen }"/>
				<jsp:param  name="recordCountPerPage" value="${recordCountPerPage }"/>
			</jsp:include>
		</div>
	</div>
	</div>
</div>
<script type="text/javascript">

function popup(seq){
	window.open("fundingstaDetail.do?seq="+seq,"후원 내역서","width=780,height=820,left=100,top=50");
}

function sele(){
	$("#_pageNumber").val(0);
	$("#frm").attr({"action":"adminFundingList.do","method":"post"}).submit();
}

/*페이징 번호*/
function goPage( pageNumber ) {
	//alert(pageNumber);
	$("#_pageNumber").val(pageNumber);
	$("#frm").attr({"action":"adminFundingList.do","method":"post"}).submit();
}


/*전체 체크박스*/
$("input[name='allfundDel']").click(function() {
	if($("input[name='allfundDel']").prop("checked")){
		$("input.check").prop("checked",true);
	}else{
		$("input.check").prop("checked",false);
	}

	$("input.check").click(function() {
		$("input[name='allfundDel']").prop("checked",false);
	});
});


/*삭제버튼*/
$("#deleteBtn").on('click',function(){
	var checkbox = $("input[name=seq]:checked");
	var checks = $("input[name=seq]");
    var isChecked = false;
		
	for (var i = 0; i < checks.length; i++) {
		if(checks.eq(i).prop("checked")) {
			isChecked = true;
		}
	}
		
	if(!isChecked){
		alert("삭제할 게시글을 선택해주세요");
	}
	else{
	checkbox.each(function(i) {
		var tr = checkbox.parent().parent().eq(i);// 부모는 td , 부모의부모는 tr
		var td = tr.children();
		//alert(td.eq(6).text()); //마감 진행 

		if(td.eq(6).text() == '진행'){
			$(this).prop("checked",false);
			alert("진행중인 후원은 삭제가 불가합니다");
		}else if(td.eq(6).text() == '마감'){
			//alert("삭제한다");
			if(td.eq(6).text() == '진행'){
				$(this).prop("checked",false);
				alert("진행중인 후원은 삭제가 불가합니다");
			}
			$("#frm").attr("action","adminFundDelete.do").submit();
			}
		});
	}
});


</script>

</body>
</html>