<%@page import="com.petmeeting.joy.playboard.Util.DateUtil"%>
<%@page import="com.petmeeting.joy.playboard.Util.PlayboardUtil"%>
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
	<link rel="icon" href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/playboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/bootstrap.min.css?after">
    <link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css"> 
</head>
<body>

 	<!--::header part start::-->
    <header class="header_area">
    	<jsp:include page="/common/navbar/templates/header.jsp" flush="false"/>
    </header>
    <!-- Header part end-->
    
    <div class="mainTitle">
        <div class="container">
            <div class="row align-content-center">
                <div class="col-lg-7 col-xl-6">
                    <div class="banner_text">
                        <h5>PetMeeting</h5>
                        <h1>소모임</h1>
                        <h5>소모임설명설명설명</h5>                        
                    </div>    
                </div>
            </div>
        </div>
 	 </div>
       
    
    <!-- 소모임 목록 -->
    <div class="playboardContainer">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 p-45">
                	
                	
				    <!-- 해쉬태그 검색 바 -->
				    <div class="btnBar">
				    	<button type="button" id="makePlayBtn">소모임 만들기</button>
				    </div>
                	
                	<!-- 검색 & 정렬 -->
	    			<div class="searchBar">
	    				<form id="searchFrm">
						<input type="hidden" name="startRow" value="${searchBean.startRow }">
						<input type="hidden" name="endRow" value="${searchBean.endRow }">
						
						<!-- 유형별 보기 -->
						<div class="playCategoryDiv">
				    		<!-- <span>모임유형</span> -->
		    				<select name="playCategory">
						    		<option value="선택" selected="selected">모임유형</option>
				    				<option value="산책">산책</option>
				    				<option value="봉사">봉사</option>
				    				<option value="원데이 클래스">원데이 클래스</option>
				    				<option value="동호회">동호회</option>
				    				<option value="기타">기타</option>
						    </select>
						</div>
						
				    	<!-- 검색 -->
				    	<div class="searchDiv">				    						    	
					    	<!-- <span>검색</span> -->
					    	<select name="searchCategory">
					    		<option value="선택" selected="selected">검색분류</option>
					    		<option value="제목">제목</option>
					    		<option value="장소">장소</option>
					    		<option value="내용">내용</option>
					    	</select>
					    	
					    	<input type="search" name="searchText">
					    	
					    	<button id="searchBtn">검색</button>	    	
				    	</div>
				    	
				    	<!-- 정렬 -->
					    <div class="sortingDiv">
					    	<!-- <span>정렬</span> -->
					    	<select name="sortingType">
					    		<option value="선택" selected="selected">최신순</option>
					    		<option value="좋아요">좋아요순</option>
					    		<option value="모임일">모임일 임박순</option>
					    		<option value="마감일">마감일 임박순</option>
					    	</select>
					    </div>
		    			</form>
	   		  		</div>
	    
                   
                </div>
            </div>
            
        <jsp:useBean id="pUtil" class="com.petmeeting.joy.playboard.Util.PlayboardUtil"/>
        	 <div class="row">
				<c:forEach items="${playboardList }" var="pList" varStatus="i">       
				<jsp:setProperty property="title" name="pUtil" value="${pList.title }"/>     
				<jsp:setProperty property="category" name="pUtil" value="${pList.category }"/>   
				<jsp:setProperty property="myDate" name="pUtil" value="${pList.pdate }"/>
				<jsp:setProperty property="location" name="pUtil" value="${pList.location }"/>
	                <div class="col-lg-4 col-md-4 col-md-6">
	                    <div class="play-items">
	                        <div class="play-img set-bg">                           
	                           <c:if test="${pList.reportcount >= 3 }">
	                           	<a class="reportAnchor"><img src="${pageContext.request.contextPath}/playboard_resources/img/reportmark.png" width="100%" height="250px"></a>
	                           </c:if>
	                           
	                           <c:if test="${pList.reportcount < 3 }">
	                            <a class="titleAnchor" seq="${pList.seq }">
	                          	 <img src="${pageContext.request.contextPath}/playboardUpload/${pList.filename }" width="100%" height="250px">
	                            </a>	 
	                           </c:if>
	                                                    
	                        </div>
	                        
	                        <div class="play-text">
	                          <div class="play-details">
	                                <div class="play-title">	
	                                   <c:if test="${pList.reportcount >= 3 }">
	                           			<div class="playTitle"><a class="reportAnchor">[${pList.category }] <jsp:getProperty property="reduceTitle" name="pUtil"/></a></div>
	                           		   </c:if>
	                           
			                           <c:if test="${pList.reportcount < 3 }">
			                           	<div class="playTitle"><a class="titleAnchor" seq="${pList.seq }">[${pList.category }] <jsp:getProperty property="reduceTitle" name="pUtil"/></a></div> 
			                           </c:if>                            	                      	
	                                    
							    		<div class="playDate"><img src="${pageContext.request.contextPath}/playboard_resources/img/calicon.png" width="20px" height="20px;">&nbsp;&nbsp;<jsp:getProperty property="dateString" name="pUtil"/></div>
							    		<div class="playLocation"><img src="${pageContext.request.contextPath}/playboard_resources/img/pin.png" width="20px" height="20px;">&nbsp;&nbsp;<jsp:getProperty property="simpleLocation" name="pUtil"/></div>
							    		<div class="playLikes"><img src="${pageContext.request.contextPath}/playboard_resources/img/hearticon.png" width="20px" height="20px;">&nbsp;&nbsp;${pList.likecount }</div>
							    		<div class="playPeople"><img src="${pageContext.request.contextPath}/playboard_resources/img/peopleicon.png" width="20px" height="20px;">&nbsp;&nbsp;${pList.personcount }명 참여중&nbsp;&nbsp;모집인원 ${pList.people }명</div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>	        	
       			</c:forEach>
			</div>			
   	 	</div>
   	 <c:if test="${totoalRowCount > 6  }">
    	<button type="button" id="loadmore">LOAD MORE</button><br><br>
     </c:if>
    
    </div>


<script type="text/javascript">
$(function () {
	
	$(document).on('click', '.titleAnchor', function(){
		if("${login.email }" == ""){
			alert("로그인을 하셔야 글을 보실 수 있습니다.");
			location.href="login.do";
		}else{
			location.href="detailPlay.do?seq="+$(this).attr("seq");
		}
	});
	
	$(document).on('click', '.reportAnchor', function(){
		alert("신고로 인해 관리자가 심사 중인 게시글 입니다.");
	});

/*
	
	$(".titleAnchor").on("click", function () {		
		if("${login.email }" == ""){
			alert("로그인을 하셔야 글을 보실 수 있습니다.");
			location.href="login.do";
		}else{
			location.href="detailPlay.do?seq="+$(this).attr("seq");
		}
	});
*/	
	if("${searchBean.playCategory }" == ""){
		$("select[name='playCategory']").val("선택").attr("selected", "selected");
	}else{
		$("select[name='playCategory']").val("${searchBean.playCategory }").attr("selected", "selected");
	}
	
	
	$("select[name='searchCategory']").val("${searchBean.searchCategory }").attr("selected", "selected");
	$("input[name='searchText']").val("${searchBean.searchText }");
	$("select[name='sortingType']").val("${searchBean.sortingType }").attr("selected", "selected");
	
	$("#searchBtn").click(function () {
		if($("select[name='searchCategory']").val() == "선택"){
			alert("검색 항목을 선택해주세요");
			return false;
		}
		$("select[name='sortingType']").val("선택").attr("selected", "selected");
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"playboard.do", "method":"post"}).submit();
	});
	
	$("select[name='sortingType']").on("change", function () {
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"playboard.do", "method":"post"}).submit();
	});
	
	$("select[name='playCategory']").on("change", function () {
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#searchFrm").attr({"action":"playboard.do", "method":"post"}).submit();
	});
	
	$("#makePlayBtn").click(function () {
		
		/*
		if(${login.auth } != 3){
			var check = ("본인인증이 완료되어야 모임을 주죄하실 수 있습니다.\n본인인증을 하시겠습니까?");
			if(check == true){
				
			}else{
				
			}
		}
		*/
		location.href="makePlay.do";
	});
	
	$("#loadmore").click(function () {
		var startRow = parseInt($("input[name='startRow']").val());
		var endRow = parseInt($("input[name='endRow']").val());
		$("input[name='startRow']").val(endRow + 1);
		$("input[name='endRow']").val(endRow + 6);
		if(parseInt($("input[name='endRow']").val()) > ${totoalRowCount }){
			$("input[name='endRow']").val("${totoalRowCount }");
		}
		
		var formData = $("#searchFrm").serialize();
		
		$.ajax({
			url: 'loadMore.do',
			data: formData,
			type: 'post',
			datatype: 'json',
			success: function (data) {
				//alert("성공");				
				 var str = "";
				$.each(data, function(idx, val) {
					var date = new Date(val.pdate);
					var day = "";
					if(date.getDay() == 0){
						day="(일)";
					}else if(date.getDay() == 1){
						day="(월)";
					}else if(date.getDay() == 2){
						day="(화)";
					}else if(date.getDay() == 3){
						day="(수)";
					}else if(date.getDay() == 4){
						day="(목)";
					}else if(date.getDay() == 5){
						day="(금)";
					}else if(date.getDay() == 6){
						day="(토)";
					}
					var strDate = date.getFullYear() + "년 " + (date.getMonth()+1) + "월 " + date.getDate() + "일 " + day;
					
					str += "<div class='col-lg-4 col-md-4 col-md-6'>";
					str += "<div class='play-items'>";
					str += "<div class='play-img set-bg'>";
					if(val.reportcount >= 3){
						str += "<a class='reportAnchor'><img src='${pageContext.request.contextPath}/playboard_resources/img/reportmark.png' width='100%' height='250px'></a>";
					}else if(val.reportcount < 3){
						str += "<a class='titleAnchor' seq='"+val.seq+"'><img src='${pageContext.request.contextPath}/playboardUpload/" + val.filename + "' width='100%' height='250px'></a>";						
					}
										
					str += "<div class='play-text'>";
					str += "<div class='play-details'>";
					str += "<div class='play-title'>";
					if(val.reportcount >= 3){
						str += "<div class='playTitle'><a class='reportAnchor'>"+"["+val.category+"] "+val.title+ "</a></div>";
					}else if(val.reportcount < 3){
						str += "<div class='playTitle'><a class='titleAnchor' seq='"+val.seq+"'>"+"["+val.category+"] "+val.title+ "</a></div>";
					}
					
					str += "<div class='playDate'><img src='${pageContext.request.contextPath}/playboard_resources/img/calicon.png' width='20px' height='20px;'>&nbsp;&nbsp;"+strDate+"</div>";
					str += "<div class='playLocation'><img src='${pageContext.request.contextPath}/playboard_resources/img/pin.png' width='20px' height='20px;'>&nbsp;&nbsp;"+val.location+"</div>";
					str += "<div class='playLikes'><img src='${pageContext.request.contextPath}/playboard_resources/img/hearticon.png' width='20px' height='20px;'>&nbsp;&nbsp;"+val.likecount+"</div>";
					str += "<div class='playPeople'><img src='${pageContext.request.contextPath}/playboard_resources/img/peopleicon.png' width='20px' height='20px;'>&nbsp;&nbsp;"+val.personcount+"명 참여중&nbsp;&nbsp;모집인원 "+val.people+"명</div>";
					
					str += "</div>";
					str += "</div>";
					str += "</div>";
					str += "</div>";
					str += "</div>";
					str += "</div>";
				});
				
				$(".row").last().append(str);
				if(parseInt($("input[name='endRow']").val()) == ${totoalRowCount }){
					$("#loadmore").hide();
				}
				
			},
			error: function () {
				alert("에러");
			}
		});

	});
});
</script>
</body>
</html>