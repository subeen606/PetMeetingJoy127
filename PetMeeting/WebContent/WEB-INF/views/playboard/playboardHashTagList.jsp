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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/playboard.css?after">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/bootstrap.min.css?after">
</head>
<body>
	
	<!--::header part start::-->
    <header class="header_area">
    	<jsp:include page="/common/navbar/templates/header.jsp" flush="false"/>
    </header>
    <!-- Header part end-->
<div class="playboardContainer">
    <div class="container">
   		<div class="row">        
   			<div class="hashBar">     
	            <div class="hashResult">
	              	태그 검색결과 : #${hashtag } (${hashCount  }개)
	            </div>                         
	             
	           	<div class="sortingDiv">
	              	<form id="hashSearchFrm">
	              		<input type="hidden" name="startRow" value="${searchBean.startRow }">
						<input type="hidden" name="endRow" value="${searchBean.endRow }">
						<input type="hidden" name="searchText" value="${hashtag }">
		             	<select name="sortingType" style="margin-top: 10px;">
		              		<option value="선택" selected="selected">최신순</option>
							<option value="좋아요">좋아요순</option>
						</select>
					</form>
				</div>
			</div>
         </div>
    
     <jsp:useBean id="pUtil" class="com.petmeeting.joy.playboard.Util.PlayboardUtil"/>
        	 <div class="row">
				<c:forEach items="${hashList }" var="pList" varStatus="i">       
				<jsp:setProperty property="title" name="pUtil" value="${pList.title }"/>     
				<jsp:setProperty property="category" name="pUtil" value="${pList.category }"/>   
				<jsp:setProperty property="myDate" name="pUtil" value="${pList.pdate }"/>
				<jsp:setProperty property="location" name="pUtil" value="${pList.location }"/>
	                <div class="col-lg-4 col-md-4 col-md-6">
	                    <div class="play-items">
	                        <div class="play-img set-bg">
	                           <a class="titleAnchor" seq="${pList.seq }"><img src="${pageContext.request.contextPath}/playboardUpload/${pList.filename }" width="100%" height="250px"></a>
	                           <div class="hoverOverlay">
	                           
	                           </div>
	                        </div>
	                        
	                        <div class="play-text">
	                          <div class="play-details">
	                                <div class="play-title">	                                	                      	
	                                    <div class="playTitle"><a class="titleAnchor" seq="${pList.seq }">[${pList.category }] <jsp:getProperty property="reduceTitle" name="pUtil"/></a></div>
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
			<c:if test="${hashCount > 6 }">
				<button type="button" id="loadmore">LOAD MORE</button>
			</c:if>
	</div>
</div>
<script type="text/javascript">


$(document).on('click', '.titleAnchor', function(){
	if("${login.email }" == ""){
		alert("로그인을 하셔야 글을 보실 수 있습니다.");
		location.href="login.do";
	}else{
		location.href="detailPlay.do?seq="+$(this).attr("seq");
	}
});
	
	$("select[name='sortingType']").on("change", function () {
		$("input[name='startRow']").val("0");
		$("input[name='endRow']").val("0");
		$("#hashSearchFrm").attr({"action":"hashSearch.do", "method":"post"}).submit();
	});
	
	
	$("#loadmore").click(function () {
		var startRow = parseInt($("input[name='startRow']").val());
		var endRow = parseInt($("input[name='endRow']").val());
		$("input[name='startRow']").val(endRow + 1);
		$("input[name='endRow']").val(endRow + 6);
		if(parseInt($("input[name='endRow']").val()) > ${hashCount }){
			$("input[name='endRow']").val("${hashCount }");
		}
		
		var formData = $("#hashSearchFrm").serialize();
		
		$.ajax({
			url: 'loadMoreHash.do',
			data: formData,
			type: 'post',
			datatype: 'json',
			success: function (data) {
			//	alert("성공");				
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
					str += "<a class='titleAnchor' seq='"+val.seq+"'><img src='${pageContext.request.contextPath}/playboardUpload/" + val.filename + "' width='100%' height='250px'></a>";
				
					str += "<div class='play-text'>";
					str += "<div class='play-details'>";
					str += "<div class='play-title'>";
					str += "<div class='playTitle'><a class='titleAnchor' seq='"+val.seq+"'>"+"["+val.category+"]"+val.title+ "</a></div>";
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
				if(parseInt($("input[name='endRow']").val()) == ${hashCount }){
					$("#loadmore").hide();
				}
				
			},
			error: function () {
				alert("에러");
			}
		});
	
});

</script>
</body>
</html>