<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PetMeeting-funding</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	
    <jsp:include page="/WEB-INF/views/funding/fundingNavbar.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fundingboard_resources/css/fundingboard.css">
</head>
<body>

<jsp:useBean id="dates" class="com.petmeeting.joy.funding.util.DateUtil"/>

<div class="container">
	<div class="wtitle">
		<div class="detailti">
			" ${dto.title } "
		</div>
		<div class="deleUp">
			<c:if test="${login.auth eq '8' }">
				<input id="updateBtn" type="button" value="수정">
				<input id="deleteBtn" type="button" value="삭제">
			</c:if>
		</div>
		
	
		<div class="deDate">
			<fmt:formatDate pattern="yyyy-MM-dd" value="${dto.sdate }"/> ~ <fmt:formatDate pattern="yyyy-MM-dd" value="${dto.edate }"/>
		</div>
		<div class="deRead">
			조회수: ${dto.readcount }
		</div>

		<div class="deContent">
			${dto.content }
		</div>	

		<div class="deicon">	
		<%-- 			
			<div class="deiconFont">
				모인후원금액<img class="listicon" src="./fundingboard_resources/img/savings.png"> [ <fmt:formatNumber value="${dto.current_price }" pattern="#,###"/>  ] 원
			</div>
			<div class="deiconFont">
				목표후원금액<img class="listicon" src="./fundingboard_resources/img/savings.png"> [ <fmt:formatNumber value="${dto.max_price }" pattern="#,###"/>  ] 원
			</div> --%>
			<div class="deiconFont">
				<img class="listicon" src="./fundingboard_resources/img/group.png"> 
				<div class="countdiv">
					<span class="countspan">${dto.personcount }</span>명이 후원 중입니다
				</div>
			</div>
			<div class="deiconFont">
				<c:if test="${dto.islike eq 'true' }">
					<a><img id="likeBtn" class="listicon" src="./fundingboard_resources/img/hearts.png" ></a> 
					 <div class="countdiv">
					  <span class="countspan" id="likecount">${dto.likecount }</span>명이 좋아합니다 
					 </div>
				</c:if>
				<c:if test="${dto.islike eq 'false' }">
					<a><img id="likeBtn" class="listicon" src="./fundingboard_resources/img/wheart.png" ></a>   
					<div class="countdiv">
						<span class="countspan" id="likecount">${dto.likecount }</span>명이 좋아합니다
					</div>
				</c:if>
			</div>
			<input type="hidden" name="seq" id="_seq" value="${dto.seq }">
		</div>		

		<div class="deBtn">	
			<c:if test="${dates.isEnd(dto.edate) eq 'false' && dto.current_price ne dto.max_price && login.auth ne '8'}">
				<input type="button" id="fundingBtn" value="후원하기">
			</c:if>
			<c:if test="${dates.isEnd(dto.edate) eq 'true' || dto.current_price eq dto.max_price}">
				<input type="button" id="endListBtn" value="후원 내역 보기">
			</c:if>
		
	<!-- The Modal -->
    <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content">
		    <div class="xBtn" onClick="close_pop();">
               <img class="xicon" src="./fundingboard_resources/img/close.png">
            </div>
            <div class="state">
      			내역서
      		</div>	
            <iframe class="staiframe" src="fundingstaDetail.do?seq=${dto.seq}"></iframe>
       </div>
 
    </div>
        <!--End Modal-->
		
		
		</div>
	</div>
</div>




<script type="text/javascript">

$("#fundingBtn").click(function() {
	//alert("후원하기");
	if( ${dto.isfunding == true} ){
		alert("이미 후원을 하셨습니다. 지속적인 관심 감사드립니다.");
	}else{
	window.open('fundingPay.do?board_seq='+$("#_seq").val()+'&funding_seq='+$("#_seq").val()+'&seq='+$("#_seq").val()+'&email=${login.email }',"후원하기","width=1000,height=900,left=100,top=50");
	}
});

$("#likeBtn").click(function() {
	//alert($("#likecount").text());
 	$.ajax({
		url: "fundinglike.do",
		type:"POST",
		cache:false,
		data: "board_seq=${dto.seq}&email=${login.email}&seq=${dto.seq}",
		success: function(msg){
			var count = parseInt( $("#likecount").text() );
			if(msg=="YES"){
			  //alert("[좋아요]를 한다");  
			  $("#likeBtn").attr('src', './fundingboard_resources/img/hearts.png');
			  $("#likecount").text(count+1);
			}else if(msg=="NO"){
			  //alert("이미좋아요를 했다 그래서 [좋아요 취소]");
			  $("#likeBtn").attr('src' , './fundingboard_resources/img/wheart.png');
			  $("#likecount").text(count-1);

			}
			
		},error:function(){
			alert("error");
		}
		
	}); 
});



$("#updateBtn").click(function() {
	if (confirm("수정 하시겠습니까?") == true){    //확인
		location.href="fundUpdate.do?seq=${dto.seq}"
	}else{   //취소
	    return;
	}
});

$("#deleteBtn").click(function() {
	if (confirm("삭제 하시겠습니까?") == true){    //확인
		location.href="fundDelete.do?seq=${dto.seq}&email=${login.email}";
	}else{   //취소
	    return;
	}
});

$("#endListBtn").click(function() {
	if(${dto.isfundingsta} == false){
		alert("내역서가 없습니다");
	}else{
		  $('#myModal').show();
	}
});

function close_pop(flag) {
	$('#myModal').hide();
};


</script>
</body>
</html>