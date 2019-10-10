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
			
	<div class="container">
		<form method="post" id="frm">
		<input type="hidden" name="seq" value="${dto.seq }"> 
			<div class="writetitle">
				<div class="wtitle">
					<img class="writeimg" src="./fundingboard_resources/img/dog3.png">
					<span class="Titlehead">funding내역서</span>
					<img class="writeimg" src="./fundingboard_resources/img/cat3.png">
				</div>
			</div>
			
		<div class="divdiv">	
			<div class="writediv">
				<div class="writeT">작성자</div>
				<input type="text" name="email" value="${login.email }" readonly="readonly"/>
			</div>
			
			<div class="writediv">
				<div class="writeT">후원기간</div>
				<img class="calimg" src="./fundingboard_resources/img/calendar.png">
				<input class="cal" type="text" id="_Sdate" value="<fmt:formatDate pattern="yyyy-MM-dd [E]" value="${dto.sdate }"/>" autocomplete="off" readonly="readonly"> ~ 
				<img class="calimg" src="./fundingboard_resources/img/calendar.png">
				 <input class="cal" type="text" id="_Edate" value="<fmt:formatDate pattern="yyyy-MM-dd [E]" value="${dto.edate }"/>" autocomplete="off" readonly="readonly"> 
				<input type="hidden" name="syear"><input type="hidden" name="smonth"><input type="hidden" name="sday">
				<input type="hidden" name="eyear"><input type="hidden" name="emonth"><input type="hidden" name="eday">
			</div>
			<div class="writediv">
				<div class="writeT">목표금액</div>
				<input type="text" name="max_price" value="${dto.max_price }" readonly="readonly">원
			</div>
			<div class="writediv">
				<div class="writeT">후원받은 금액</div>
				<input type="text" name="current_price" value="${dto.current_price }" readonly="readonly">원
			</div>
			<div class="writediv">
				<div class="writeT">후원 명</div>
				<input type="text" name="title" style="width: 40%;" value="${dto.title }" readonly="readonly"/>
			</div>
			<div class="writediv">
				<div class="writeT">소제목</div>
				<textarea style="resize: none; width: 70%;" name="intro" readonly="readonly">${dto.intro }</textarea>
			</div>
			<div class="writediv">
				<div class="wcontent">후원 내용</div>	
				${dto.content }
			</div>
			<div class="writediv">
				<div class="wcontent">후원 내역</div>	
				<textarea id="summernote" name="content"></textarea>
			</div>
			
			</div>
			<div class="writediv2">
				<div>
					<input id="subBtn" type="button" value="내역서 등록" />
				</div>
			</div>
		
		</form>
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