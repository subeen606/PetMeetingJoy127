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
	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/admin_common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin_resources/css/fundingboardDetail.css">	
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fundingboard_resources/css/fundingboard.css">
     
    <!-- include libraries(jQuery, bootstrap) summernote -->
	<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<!-- include summernote css/js-->
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>
	<!-- include summernote-ko-KR -->
	<script src="fundingboard_resources/js/summernote-ko-KR.js"></script>
    
    <!-- Date Picker -->
    <link  href="${pageContext.request.contextPath}/fundingboard_resources/datepicker/datepicker.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/fundingboard_resources/datepicker/datepicker.js"></script>  

</head>
<body>
<div id="right-panel" class="right-panel">
	<!--::header part start::-->
		<jsp:include page="/admin_resources/admincss/templates/adminheader.jsp" flush="false"/>
	<!-- Header part end-->

<!-- Content -->
<div class="content">	
<div class="container">
		<form method="post" id="frm">
		<input type="hidden" name="seq" value="${dto.seq }"> 
		<input type="hidden" name="email" value="${dto.email }">
			<div class="writetitle">
				<div class="wtitle">
					<img class="writeimg" src="./fundingboard_resources/img/dog3.png">
					<span class="Titlehead">funding내역서</span>
					<img class="writeimg" src="./fundingboard_resources/img/cat3.png">
				</div>
			</div>
			<table class="StaWriTable">
				<tr>
					<th>후원명</th>
					<td>${dto.title }</td>
				</tr>
				<tr>
					<th>후원일정</th>
					<td><fmt:formatDate pattern="yyyy년  MM월 dd일" value="${dto.sdate }"/> ~ <fmt:formatDate pattern="yyyy년  MM월 dd일" value="${dto.edate }"/> </td>
				</tr>
				<tr>
					<th>후원소개</th>
					<td>${dto.intro }</td>
				</tr>
				<tr>
					<th>목표금액</th>
					<td><fmt:formatNumber value="${dto.max_price }" pattern="#,###"/> 원 </td>
				</tr>
				<tr>
					<th>후원받은 금액</th>
					<td><fmt:formatNumber value="${dto.current_price }" pattern="#,###"/> 원</td>
				</tr>
				<tr>
					<th colspan="2">후원내용</th>
				</tr>
				<tr>
					<td colspan="2" id="deContent" >${dto.content }</td>
				</tr>
				<tr>
					<th colspan="2">후원 내역서</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea id="summernote" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input id="subBtn" type="button" value="내역서 등록">
					</td>
				</tr>
			</table>
		</form>
</div>
</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		  $('#summernote').summernote({
		    	placeholder: 'content',
		        minHeight: 370,
		        maxHeight: null,
		        focus: true, 
		        lang : 'ko-KR',
		        callbacks: { // 콜백을 사용
                    // 이미지를 업로드할 경우 이벤트를 발생
				    onImageUpload: function(files, editor, welEditable) {
				    	  for (var i = files.length - 1; i >= 0; i--) {
				              sendFile(files[i], this);

				            }
		        		}
		        	}
		  });
		});
	
	
	function sendFile(file, editor) {
	    // 파일 전송을 위한 폼생성
			data = new FormData();
		    data.append("file", file);
		    $.ajax({ // ajax를 통해 파일 업로드 처리
		        data : data,
		        type : "POST",
		        url : "img.do",
		        cache : false,
		        contentType : false,
		        enctype: 'multipart/form-data',
		        processData : false,
		        success : function(data) { // 처리가 성공할 경우
	            // 에디터에 이미지 출력
		        	$(editor).summernote('editor.insertImage', data);
		        },
		        error : function () {
		        	alert("error");
				}
		    });
		}
	
</script>




<script type="text/javascript">

$("#subBtn").click(function() {
	$("#frm").attr("action","statementAf.do").submit();
});

$(document).ready(function() {
	  
	  var date = new Date();
	  var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	  var tomorrow = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1);
	  //var end = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	  
$("#_Sdate").datepicker({   

	      format: 'yyyy-mm-dd',
	      startDate: today,
	      language: "ko",
	      todayHighlight: true,
	      daysMin: ['일','월','화','수','목','금','토'],
	      months: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
	      autoHide: true,
	      yearFirst: true,
	      yearSuffix: '년'            
	   });
	   
/*Sdate 설정*/
$('#_Sdate').on('pick.datepicker', function (e) {
	      
		  var sdate = $('#_Sdate').datepicker('getDate');
	      alert("sdate : " + sdate);
	      
	      var StartDate = new Date(sdate);
	      StartDate.setDate(sdate.getDate()+1);
	      //alert("StartDate : " + StartDate);

	      $("input[name='syear']").val(sdate.getFullYear());
	      $("input[name='smonth']").val(sdate.getMonth()+1);
	      $("input[name='sday']").val(sdate.getDate()); 
	     
	      
	     /*Edate 설정*/
		      $("#_Edate").datepicker({   
	
		    	    format: 'yyyy-mm-dd',
		    	    startDate: StartDate,
		    	    language: "ko",
		    	    todayHighlight: true,
		    	    daysMin: ['일','월','화','수','목','금','토'],
		    	    months: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		    	    autoHide: true,
		    	    yearFirst: true,
		    	    yearSuffix: '년'     
		    	 });
	
		    	$('#_Edate').on('pick.datepicker', function (e) {
		    	    
		    	var edate = $('#_Edate').datepicker('getDate');
		    		  //alert($('#_Edate').datepicker('getDate'));
		    	 
		  	      $("input[name='eyear']").val(edate.getFullYear());
			      $("input[name='emonth']").val(edate.getMonth()+1);
			      $("input[name='eday']").val(edate.getDate()); 
		    	    
		    	 });
	      
	   });
	   
$("input:text[numberOnly]").on("keyup",function() {
	$(this).val($(this).val().replace(/[^0-9]/g,""));
});

	   
});
</script>
</body>
</html>