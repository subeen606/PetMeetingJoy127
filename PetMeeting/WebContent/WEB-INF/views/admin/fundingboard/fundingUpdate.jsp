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
    
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/admin_resources/css/admin_common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/admin_resources/css/fundingboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/fundingboard_resources/css/fundingboard.css">
     
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
		<form method="post" id="frm" enctype="multipart/form-data">
		<input type="hidden" name="email" value="${login.email }">
		<input type="hidden" name="seq" value="${dto.seq }"> 
			<div class="writetitle">
				<div class="wtitle">
					<img class="writeimg" src="./fundingboard_resources/img/dog3.png">
					<span class="Titlehead">Funding</span>
					<img class="writeimg" src="./fundingboard_resources/img/cat3.png">
				</div>
			</div>
			
			<table class="WriteTable">
			<tr>
				<th>작성자</th>
				<td>${login.email }</td>
			</tr>
			<tr>
				<th>후원기간</th>
				<td colspan="2"> 
				<img class="calimg" src="./fundingboard_resources/img/calendar.png">
					<input class="cal" type="text" id="_Sdate"  value= "<fmt:formatDate pattern="yyyy-MM-dd" value="${dto.sdate }"/>" autocomplete="off"> ~ 
				<img class="calimg" src="./fundingboard_resources/img/calendar.png">
				 	<input class="cal" type="text" id="_Edate"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${dto.edate }"/>" autocomplete="off"> 
					<input type="hidden" name="syear" value="<fmt:formatDate pattern="yyyy" value="${dto.sdate }"/>"><input type="hidden" name="smonth" value="<fmt:formatDate pattern="MM" value="${dto.sdate }"/>"><input type="hidden" name="sday" value="<fmt:formatDate pattern="dd" value="${dto.sdate }"/>">
					<input type="hidden" name="eyear" value="<fmt:formatDate pattern="yyyy" value="${dto.edate }"/>"><input type="hidden" name="emonth" value="<fmt:formatDate pattern="MM" value="${dto.edate }"/>"><input type="hidden" name="eday" value="<fmt:formatDate pattern="dd" value="${dto.edate }"/>">
				</td>
			</tr>
			<tr>
				<th>후원 목표금액</th>
				<td><input type="text" class="number" name="max_price" value="<fmt:formatNumber value="${dto.max_price }" pattern="#,###"/>"> 원</td>
			</tr>
			<tr>
				<th>후원 명</th>
				<td><input type="text" id="fundT" name="title" style="width: 40%;" value="${dto.title }"/></td>
			</tr>
			<tr>
				<th>후원 소제목</th>
				<td><textarea rows="3" cols="70" id="introtitle" name="intro">${dto.intro }</textarea></td>
			</tr>
			<tr>
				<th>현재 대표사진</th>
				<td><img class="listimg2" src="fundingFileupload/${dto.thumbnail }">${dto.thumbnail }</td>
			</tr>
			<tr>
				<th>대표사진 재설정</th>
				<td><input id="fundI" type="file" name="fileload"></td>
			</tr>
			<tr>
				<th colspan="2">후원내용</th>
			</tr>
			<tr>
				<td colspan="2"><textarea id="summernote" name="content">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" id="subBtn" value="수정완료"></td>
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
	var num = $(".number").val().replace(/[^\d]+/g, "");
	
	/*후원일정 제어*/
	if($("#_Sdate").val() == "" || $("#_Edate").val() == ""){
		alert("후원기간을 설정해주세요.");
	}
	/*목표금액 제어*/
	if($(".number").val() == ""){
		alert("목표금액을 작성해주세요.");
	}
	if(num > 50000000){
		alert("목표금액은 50,000,000 이하로 설정해주세요.");
	}
	/*제목, 소제목 , 내용 , 대표사진 제어*/
	if($("#fundT").val() == "") {
		alert("후원명을 입력해주세요.");
	}
	if($("#introtitle").val() == ""){
		alert("후원 소제목을 입력해주세요.");
	}
	if($("#summernote").val() == ""){
		alert("후원내용을 입력해주세요.");
	}
	else if($("#_Sdate").val() != "" && $("#_Edate").val() != ""
			&& $(".number").val() != "" && $("#fundT").val() != "" && $("#introtitle").val() != ""
			&& $("#summernote").val() != ""){
				var num = $(".number").val().replace(/[^\d]+/g, "");
				$(".number").val(parseInt(num));
				//alert($(".number").val());
				if(num <= 50000000){
					$("#frm").attr("action","fundingUpateAf.do").submit();
				}
			}
});

$(document).ready(function() {
	  
	  var date = new Date();
	  var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	  var tomorrow = new Date(date.getFullYear(), date.getMonth(), date.getDate()+1);
	  //var end = new Date(date.getFullYear(), date.getMonth(), date.getDate());
	  
$("#_Sdate").datepicker({   

	      format: 'yyyy-mm-dd',
	      startDate: today,
	      endDate: today,
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
	      //alert("sdate : " + sdate);
	      
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
});

$(document).on("keyup", "input[type=text].number", function () {
    var $this = $(this);
    var num = $this.val().replace(/[^0-9]/g,"");
 
    var parts = num.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    $this.val(parts.join("."));
});
</script>
</body>
</html>