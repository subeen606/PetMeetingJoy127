<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PetMeeting-소모임</title>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    
	<!-- datepicker -->
	<link  href="${pageContext.request.contextPath}/playboard_resources/datepicker/datepicker.css?after" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/playboard_resources/datepicker/datepicker.js"></script>

	<!-- 도로명 주소 -->
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<!-- CKEditor -->
	<script src="${pageContext.request.contextPath}/playboard_resources/ckeditor/ckeditor.js"></script>
	
    <link rel="icon" href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
      
    <link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/playboardWrite.css">
    <link href="https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap" rel="stylesheet">
</head>
<body>


<!--::header part start::-->
<header class="header_area">
	<jsp:include page="/common/navbar/templates/header.jsp" flush="false"/>
</header>
<!-- Header part end-->
    
<!-- 글쓰기 폼 -->
<form id="boardUpdateFrm" action="" method="post" enctype="multipart/form-data">
<input type="hidden" name="email" value="${detail.email }">
<input type="hidden" name="seq" value="${detail.seq }"> 
    <div class="container">
    	<div class="playWrtieContainer">
    		<div class="Title">
	    		<img src="${pageContext.request.contextPath}/playboard_resources/img/playboardIcon.png" width="100px">&nbsp;&nbsp;소모임 만들기 - 수정
	    	</div>
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>모임 유형</span>
	    		</div>
	    		<div class="writeInput">
	    			<select name="category">
	    				<option>선택</option>
	    				<option value="산책">산책</option>
	    				<option value="봉사">봉사</option>
	    				<option value="원데이 클래스">원데이 클래스</option>
	    				<option value="동호회">동호회</option>
	    				<option value="기타">기타</option>
	    			</select>
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>모임 제목</span>
	    		</div>
	    		<div class="writeInput">
	    			<input type="text" name="title" value="${detail.title }">
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>모임 예정일</span>
	    		</div>
	    		<jsp:useBean id="pUtil" class="com.petmeeting.joy.playboard.Util.PlayboardUtil"/>
	    		<jsp:setProperty property="myDate" name="pUtil" value="${detail.pdate }"/>
	    		<div class="writeInput">
	    			<label>
	    			<input type="text" autocomplete="off" id="_playDate" readonly="readonly" value='<jsp:getProperty property="formatDate" name="pUtil"/>'>
	    			<input type="hidden" name="pyear" value="${dateBean.pyear }">
	    			<input type="hidden" name="pmonth" value="${dateBean.pmonth }">
	    			<input type="hidden" name="pday" value="${dateBean.pday }">
	    			<img src="${pageContext.request.contextPath}/playboard_resources/img/schedule.png" width="20px" height="20px;" style="opacity: 0.4">
	    			</label>	    			
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>모집 마감일</span>
	    		</div>
	    		<jsp:setProperty property="myDate" name="pUtil" value="${detail.edate }"/>
	    		<div class="writeInput">
	    			<label>
	    			<input type="text" autocomplete="off" id="_endDate" readonly="readonly" value='<jsp:getProperty property="formatDate" name="pUtil"/>'>	   
	    			<input type="hidden" name="eyear" value="${dateBean.eyear }">
	    			<input type="hidden" name="emonth" value="${dateBean.emonth }">
	    			<input type="hidden" name="eday" value="${dateBean.eday }"> 			
	    			<img src="${pageContext.request.contextPath}/playboard_resources/img/schedule.png" width="20px" height="20px;" style="opacity: 0.4">
	    			</label>
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle-address">
	    			<span>모임 장소</span>
	    		</div>
	    		<div class="writeInput-address">
	    			<!-- <input type="text" id="sample6_postcode" placeholder="우편번호"> -->					
					<input type="text" name="location" id="sample6_address" placeholder="주소" value="${detail.location }"><button type="button" id="findAddr" onclick="sample6_execDaumPostcode()">우편번호 찾기</button><br>
					<input type="text" name="location_detail" id="sample6_detailAddress" placeholder="상세주소" value="${detail.location_detail }">
					<!-- <input type="text" id="sample6_extraAddress" placeholder="참고항목"> -->
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>모임 예상비용</span>
	    		</div>
	    		<div class="writeInput">
	    			<input type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name="price" id="_price" value="${detail.price }" style="width: 100px;">원
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>모집 인원</span>
	    		</div>
	    		<div class="writeInput">
	    			<input type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name="people" id="_people" value="${detail.people }" style="width: 100px;">명
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>기존 대표사진</span>
	    		</div>
	    		<div class="writeInput" style="height: auto;">
	    			<input type="hidden" name="filename" value="${detail.filename }">
	    			<div id="previewDiv"><img src="${pageContext.request.contextPath}/playboardUpload/${detail.filename }" width="400px;"></div>
	    		</div>
    		</div>
    		
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>대표사진 수정</span>
	    		</div>
	    		<div class="writeInput">
	    			<input type="file" name="fileload" accept="image/gif, image/jpeg, image/jpg, image/png" style="width: 50%;">
	    		</div>
    		</div>
    		
    		<div class="writeRow-content">
	    		모임 상세설명
	    		<div class="contentNotice">
	    			주최하시는 모임에 대해 상세히 적어주세요!     		 
	    		</div>
    		</div>
    		
    		<div class="writeRow">
	    		<textarea name="content" id="editor" rows="10" cols="100" style="width:95%; height:600px; display:none;">${detail.content }</textarea>
    		</div>
    		
    		<div class="writeRow">
	    		<div class="writeTitle">
	    			<span>태그</span>
	    		</div>
	    		<div class="writeInput">
	    			<jsp:setProperty property="hash1" name="pUtil" value="${hashs.hash1 }"/>
	    			<jsp:setProperty property="hash2" name="pUtil" value="${hashs.hash2 }"/>
	    			<jsp:setProperty property="hash3" name="pUtil" value="${hashs.hash3 }"/>
	    			<jsp:setProperty property="hash4" name="pUtil" value="${hashs.hash4 }"/>
	    			<jsp:setProperty property="hash5" name="pUtil" value="${hashs.hash5 }"/>
	    			<input type="text" name="hashtag" placeholder="#태그를 입력해보세요!" value='<jsp:getProperty property="hashTags" name="pUtil"/>'>
	    			<input type="hidden" name="hash1" value="${hashs.hash1 }">
	    			<input type="hidden" name="hash2" value="${hashs.hash2 }">
	    			<input type="hidden" name="hash3" value="${hashs.hash3 }">
	    			<input type="hidden" name="hash4" value="${hashs.hash4 }">
	    			<input type="hidden" name="hash5" value="${hashs.hash5 }">	    			
	    		</div>
    		</div>
    		
    		<div class="writeRow">	    		
    			<div class="btnDiv">
		    		<button type="button" id="cancelBtn">취소</button>
		    		<button type="button" id="submitBtn">수정</button>
	    		</div>
    		</div>
    		
    	</div>   
    </div>
</form>

<script type="text/javascript">
$(function () {
	
	/* 셀렉트값 기존 값 선택되어있도록 제어 */
	$("select[name='category']").val("${detail.category }").attr("selected", "selected");
	
	/* 취소버튼 */
	$("#cancelBtn").click(function () {
		var check = confirm("수정을 쥐소하시겠습니까?");
		if(check){
			location.href="playboard.do";
		}		
	});
	
	var today = new Date();
	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate()+1);
	var mindate;
	var week =["일", "월", "화", "수", "목", "금", "토"];
	var month = ["1월","2월","3월","4월","5월","6월", "7월","8월","9월","10월","11월","12월"];

	/* 모임 예정일 */
	$("#_playDate").datepicker({	
		format: 'yyyy-mm-dd',
		startDate: tomorrow,
		language: "ko",
		todayHighlight: false,
		daysMin: week,
		months: month,
		autoHide: true,
		yearFirst: true,
		yearSuffix: '년',
		setDate: new Date($("input[name='pyear']").val(), $("input[name='pmonth']").val(), $("input[name='pday']").val())
	});
	
	$('#_playDate').on('pick.datepicker', function (e) {
		var pdate = $('#_playDate').datepicker('getDate');
		var endDate = new Date(pdate);
		endDate.setDate(pdate.getDate()-1);
		$("#_endDate").datepicker('setEndDate', endDate);
		
		$("input[name='pyear']").val(pdate.getFullYear());
		$("input[name='pmonth']").val(pdate.getMonth()+1);
		$("input[name='pday']").val(pdate.getDate());	
		
	});

	/* 모집 마감일 */
	$("#_endDate").datepicker({
		format: 'yyyy-mm-dd',
		startDate: today,
		language: "ko",
		todayHighlight: false,
		daysMin: week,
		months: month,
		autoHide: true,
		yearFirst: true,
		yearSuffix: '년',
		setDate: new Date($("input[name='eyear']").val(), $("input[name='emonth']").val(), $("input[name='eday']").val())
	});
	$('#_endDate').on('pick.datepicker', function (e) {
		var edate = $('#_endDate').datepicker('getDate');
		
		$("input[name='eyear']").val(edate.getFullYear());
		$("input[name='emonth']").val(edate.getMonth()+1);
		$("input[name='eday']").val(edate.getDate());
	});
	
	/* hashtag 감지 */
	$("input[name='hashtag']").keyup(function (e) {
		var hashs = $(this).val();
		var count = (hashs.match(/#/g) || []).length;
		var comma = (hashs.match(/,/g) || []).length;
		
		/* 특수문자 검색위한 정규식 #과 ,는 제외 */
		var regExp = /[\{\}\[\]\/?.;:|\)*~`!^\-+<>@\$%&\\\=\(\'\"]/gi;	
		/* #과 ,제외하지 않은 정규식 	var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi; */
		 
		/* #과 ,제외한 특수문자 입력 제어 */
		if(regExp.test(hashs)){
			alert("태그 작성시 특수문자는 사용하실 수 없습니다");
			var length = hashs.length;
			$("input[name='hashtag']").val(hashs.substring(0, length-1));
			$("input[name='hashtag']").focus();
		}
		
		/* 첫 번째 글자가 #이 아닐 경우 제어 */
		if(hashs.indexOf("#") != 0){
			alert("태그 앞에는 '#'을 붙여주세요");
			$("input[name='hashtag']").val("");
			$("input[name='hashtag']").focus();
		}
		
		/* 태그가 한 개 이상인 경우 */
		/* 콤마로 구분해주지 않았을 때 제어 */
		if(count > 1){
			if(hashs.indexOf(",") < 0 || !((count - comma) <= 1)){	
				alert("태그는 공백없이 ','으로만 구분해주세요\n예시:#반려동물커뮤니티,#펫미팅");
				var index = hashs.lastIndexOf("#");
				$("input[name='hashtag']").val(hashs.substring(0, index));
				$("input[name='hashtag']").focus();
			}
		}
		
		/* 태그 개수 제어 */
		if(count > 5){
			alert("태그는 5개까지만 입력 가능합니다!");
			var index = hashs.lastIndexOf("#");
			$("input[name='hashtag']").val(hashs.substring(0, index));
			$("input[name='hashtag']").focus();
		}
		
		/* 스페이스바 제어 */
		if(e.keyCode == 32){
			alert("태그는 공백없이 ','으로만 구분해주세요 \n예시:#반려동물커뮤니티,#펫미팅");
			$("input[name='hashtag']").val(hashs.replace(" ", ","));
			$("input[name='hashtag']").focus();
		}

	});
		
	$("input[name='hashtag']").focusout(function () {
		
		var hashs = $("input[name='hashtag']").val();
		var split = hashs.split(",");
		console.log(split);
		for(i=0; i<split.length; i++){
			$("input[name='hash" + (i+1) + "']").val(split[i]);
		} 
	});

	
	/* 소모임 글쓰기 submit */
	$("#submitBtn").click(function () {
		if($("select[name='category']").val() == "선택"){
			alert("모임 유형을 선택해주세요!");
			$("select[name='category']").focus();
			return false;
		}
		
		if($.trim($("input[name='title']").val()) == ""){
			alert("제목을 입력해주세요!");
			$("input[name='title']").focus();
			return false;
		}else if($("input[name='title']").val().length < 5){
			alert("제목은 띄어쓰기 제외 5글자 이상으로 입력해 주세요");
			$("input[name='title']").focus();
			return false;
		}
		
		var data = CKEDITOR.instances.editor.getData();
		if($.trim(data) == ""){
			alert("모임에 대한 설명을 입력해주세요!");
			return false;
		}
		
		if($.trim($("#_playDate").val()) == ""){
			alert("모임 예정일을 선택해주세요!");
			return false;
		}
		
		if($.trim($("#_endDate").val()) == ""){
			alert("모집 마감일을 선택해주세요!");
			return false;
		}
		
		if($.trim($("input[name='price']").val()) == ""){
			alert("모임 예상비용을 입력해주세요! 예상비용이 없는 경우 0을 입력하시면 됩니다.");
			$("input[name='price']").focus();
			return false;
		}
		
		if($.trim($("input[name='people']").val()) == ""){
			alert("모집 인원을 입력해주세요!");
			$("input[name='people']").focus();
			return false;
		}
		
		if($.trim($("input[name='location']").val()) == ""){
			alert("모임 장소를 입력해주세요!");
			return false;
		}
		
		
		
		$("#boardUpdateFrm").attr("action", "updatePlayAf.do").submit();
	});
});


/* CKEDITOR */
  CKEDITOR.editorConfig = function( config ) {
  	config.removePlugins = 'easyimage, cloudservices';
  	config.fillEmptyBlocks = false;
  	config.fullPage = false;
  	config.enterMode = CKEDITOR.ENTER_BR;
  	config.filebrowserUploadMethod = 'form';
  	config.language = 'ko';
  };
  
  CKEDITOR.replace('editor', {
	  	 filebrowserUploadUrl: '${path }fileupload.do',
	  	 width: '100%',
	     height: 500
  });

  
/* 도로명주소 API */
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
/*
            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }
*/
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
          //  document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").value = "";
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

</script>


</body>
</html>