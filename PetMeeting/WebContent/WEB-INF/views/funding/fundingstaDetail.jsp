<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Required meta tags -->
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/fundingboardDetail.css">

</head>
<body>

<form id=frm method="post">
	<input type="hidden" name="seq" value="${sta.seq }">
	<%-- <input type="hidden" name="content" value="${sta.content }"> --%>
	
	<c:if test="${login.auth eq 8 }">
		<div id="staTitle">후원내역서</div>
		<div id="staContent">${sta.content }</div>
		
		<div id="staBtn">
			<input id="StadelBtn" type="button" value="삭제">
		</div>
	</c:if>
	
	<c:if test="${login.auth ne 8 }">
		<div>${sta.content }</div>
	</c:if>
</form>

<script type="text/javascript">

$("#StadelBtn").click(function() {
	var queryString = $("#frm").serialize();
	$.ajax({
		url:"fundingStaDel.do",
		type:"POST",
		chache:false,
		data: queryString,
		success:function(data){
			alert("삭제되었습니다.");
			self.close();
			opener.document.location.reload();
		},error: function(){
			alert("error");
		}
		
	});
});

</script>

</body>
</html>