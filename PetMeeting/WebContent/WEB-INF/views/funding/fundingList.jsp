<%@page import="com.petmeeting.joy.funding.util.DateUtil"%>
<%@page import="com.petmeeting.joy.funding.model.FundingDto"%>
<%@page import="java.util.List"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
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

	<jsp:include page="/WEB-INF/views/funding/fundingNavbar.jsp" flush="false"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fundingboard_resources/css/fundingboard.css">
    <!-- 슬라이드 화살표 -->
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>



<div class="container">

<div id="wrapper">
	      <div id="slider-wrap">
	             <ul id="slider">     
		         <li>          
					<img src="./fundingboard_resources/img/dog.jpg">
		     	 </li>
		         <li>          
					<img src="./fundingboard_resources/img/dog2.jpg">
		     	 </li>
	     	     <li>          
					<img src="./fundingboard_resources/img/cat.jpg">
	     	  	 </li>
	     	     <li>          
					<img src="./fundingboard_resources/img/cat2.jpg">
	     	  	 </li>
	      	     <li>          
					<img src="./fundingboard_resources/img/ham.jpg">
	     	  	 </li>  
	          </ul>
	          
	           <!--controls-->
	          <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
	          <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
			  <div id="pagination-wrap">
	            <ul>
	            </ul>
	          </div>
	          <!--controls-->  
	        </div>
</div>
	
<jsp:useBean id="dates" class="com.petmeeting.joy.funding.util.DateUtil"/>
<jsp:useBean id="today" class="java.util.Date"/>


		<c:if test="${login.auth eq '8'}"> 
			<a href="fundingWrite.do"> [후원 만들기] </a>
		</c:if>


<div class="pack">

	<div class="select">
		<select id="_select" onchange="select()">
			<option <c:if test="${categorys == '_update' }">selected</c:if> value="_update">등록 순</option>
			<option <c:if test="${categorys == '_like' }">selected</c:if> value="_like">좋아요 순</option>
			<option <c:if test="${categorys == '_join' }">selected</c:if> value="_join">참여자 순</option>
		</select>
	</div>

	<div class="search_1">
		<input id="search_title" type="text" placeholder="후원명을 입력해주세요" <c:if test="${keyword != null}"> value='${keyword}'</c:if> > 
		<input id="searchBtn" type="button" value="검색">
	</div>
</div>


<%-- 	
<c:if test="${dates.isEnd(list.edate) eq 'true'  || list.current_price eq list.max_price && login.auth ne '8'}">
	<div class="fundingContainer" style="display: none; opacity: 0.4;">
</c:if>
<c:if test="${dates.isEnd(list.edate) eq 'false' && list.current_price ne list.max_price }">
	<div class="fundingContainer" style="display: none">
</c:if> 
--%>
<c:forEach var="list" items="${list }" varStatus="vs">
	<div class="fundingContainer" style="display: none;">
		
		<div>
			<div class="endtext">
				<c:if test="${dates.isEnd(list.edate) eq 'true' || list.current_price eq list.max_price}">
					<span class="fundEnd">[후원 마감]</span>
				</c:if>
			</div>
			<div>
				<a href="#" ><img class="listimg" src="fundingFileupload/${list.thumbnail }"></a>
			</div>
		</div>	
			
			<div class="listText">
			
				<div>
					[ <fmt:formatDate pattern="yyyy-MM-dd" value="${list.sdate }"/> ]  ~ [ <fmt:formatDate pattern="yyyy-MM-dd" value="${list.edate }"/> ]		
					<c:if test="${list.isfunding eq 'true' }">
						&nbsp;<img class="listicon" alt="" src="./fundingboard_resources/img/success.png"> 참여중
					</c:if>
				</div>
				
				<div class="listTitle"> ${list.title } </div>
			
			    <!-- 
 				 <div class="list1">
				 	등록일: [ <fmt:formatDate pattern="yyyy-MM-dd" value="${list.regdate }"/> ]
				 </div>	 -->
				 <c:if test="${dates.isEnd(list.edate) eq 'false'}"> 
					 <div class="dday">
					 	<fmt:formatDate value="${today }" var="now" pattern="yyyyMMdd"/>
						<fmt:formatDate var="edate" value="${list.edate }" pattern="yyyyMMdd"/>
						D - ${edate-now }
					 </div>
				 </c:if>
				 <div class="list1">
					[${list.readcount }]명이 조회했습니다
				 </div>	
				 <div class="list1">
				 	<img class="listicon" src="./fundingboard_resources/img/hearts.png"> ${list.likecount }
				 </div>
				 <div class="list1">	
					<img class="listicon" src="./fundingboard_resources/img/group.png">  ${list.personcount } 명의 후원
				</div>
						        
				<div class="list1">
					<img class="listicon" src="./fundingboard_resources/img/trophy.png"> <fmt:formatNumber value="${list.current_price }" pattern="#,###"/>  / <fmt:formatNumber value="${list.max_price }" pattern="#,###"/> (원)
				</div>
				<div class="list1">	
					<div class="graph">
						<span style="width:${dates.PercentP(list.current_price,list.max_price)}%">${dates.PercentP(list.current_price,list.max_price)}%</span>
					</div>
				</div>	
				<div class="fundingintro">${list.intro }</div>
				<div><a href="fundingDetail.do?board_seq=${list.seq }&seq=${list.seq }&email=${login.email}&board_code=FUND"> 자세히보기>> </a></div><br>
				
				<c:if test="${dates.isEnd(list.edate) eq 'true' || list.current_price eq list.max_price }">
					<c:if test="${ login.auth eq '8'}">
						<c:if test="${list. isfundingsta eq 'false' }">
							<a href="statement.do?board_seq=${list.seq }&seq=${list.seq }&email=${login.email}"><img class="listicon" src="./fundingboard_resources/img/notepad.png">내역서 등록</a>
						</c:if>
					</c:if>
				</c:if>
			</div>
	</div>
</c:forEach>		 

	<div class="moreList">
		<span class="more_list"> more </span> 
	</div>
</div>

<script>

 $(document).ready(function() {
	$(".fundingContainer").slice(0,5).show();
	
	if($(".fundingContainer:hidden").length == 0 ){
		$(".more_list").css("display","none");
	}else{
	$(".more_list").click(function(e) {
		e.preventDefault();
		$(".fundingContainer:hidden").slice(0,5).show();
			if($(".fundingContainer:hidden").length == 0 ){
				$(".more_list").css("display","none");
			}
	});
	}
});

function select() {
	//alert("정렬: " + $("#_select").val());
	location.href="funding.do?categorys="+$("#_select").val()+"&keyword=";
}

$("#searchBtn").click(function() {
	//alert("검색어: " + $("#search_title").val() + " 키워드: " + $("#_select").val());
	location.href="funding.do?keyword="+$("#search_title").val()+"&categorys="+$("#_select").val();
});

//current position
var pos = 0;
//number of slides
var totalSlides = $('#slider-wrap ul li').length;
//get the slide width
var sliderWidth = $('#slider-wrap').width();


$(document).ready(function(){
  /* BUILD THE SLIDER*/
    //set width to be 'x' times the number of slides
    $('#slider-wrap ul#slider').width(sliderWidth*totalSlides);
    
    //next slide    
    $('#next').click(function(){
        slideRight();
    });
    
    //previous slide
    $('#previous').click(function(){
        slideLeft();
    });
    /* OPTIONAL SETTINGS*/
    //automatic slider
    var autoSlider = setInterval(slideRight, 3000);
    
    //for each slide 
    $.each($('#slider-wrap ul li'), function() { 

       //create a pagination
       var li = document.createElement('li');
       $('#pagination-wrap ul').append(li);    
    });
    
    //counter
    countSlides();
    
    //pagination
    pagination();
    
    //hide/show controls/btns when hover
    //pause automatic slide when hover
    $('#slider-wrap').hover(
      function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
      function(){ $(this).removeClass('active'); autoSlider = setInterval(slideRight, 3000); }
    );
    
    

});//DOCUMENT READY
    
/* SLIDE LEFT*/
function slideLeft(){
    pos--;
    if(pos==-1){ pos = totalSlides-1; }
    $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos));    
    
    //*> optional
    countSlides();
    pagination();
}


/* SLIDE RIGHT*/
function slideRight(){
    pos++;
    if(pos==totalSlides){ pos = 0; }
    $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos)); 
    
    //*> optional 
    countSlides();
    pagination();
}
/*> OPTIONAL SETTINGS*/
function countSlides(){
    $('#counter').html(pos+1 + ' / ' + totalSlides);
}

function pagination(){
    $('#pagination-wrap ul li').removeClass('active');
    $('#pagination-wrap ul li:eq('+pos+')').addClass('active');
} 
        
</script>    

</body>
</html>