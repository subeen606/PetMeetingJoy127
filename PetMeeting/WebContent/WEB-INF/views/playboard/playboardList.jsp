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
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/bootstrap.min.css">
    <!-- animate CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/animate.css">
    <!-- owl carousel CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/owl.carousel.min.css">
    <!-- themify CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/themify-icons.css">
    <!-- flaticon CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/flaticon.css">
    <!-- font awesome CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/magnific-popup.css">
    <!-- style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/style.css">
    <!-- 우리가 추가한 css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/common/navbar/css/custom.css?after">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/playboard_resources/css/playboard.css?after">
</head>
<body>

 <!--::header part start::-->
    <header class="header_area">
        <div class="sub_header">
            <div class="container">
                <div class="row align-items-center">
                 <!--  <div class="col-4 col-md-4 col-xl-6"> -->
                      <div id="logo">
                          <a href="main.do"><img src="${pageContext.request.contextPath}/common/navbar/img/petmeetinglogo.png" alt="" title="" width="400px" /></a>
                      </div>
                      
                  <!-- </div> -->
                  <!-- 
                  <div class="col-8 col-md-8 col-xl-6 ">
                      <div class="sub_header_social_icon float-right">
                        <a href="#"><i class="flaticon-phone"></i>+02 213 - 256 (365)</a>
                        <a href="#" class="btn_1 d-none d-md-inline-block">Become a Volunteer</a>
                      </div>
                    </div>
                     -->
                </div>
            </div>
        </div>
        
        <!-- 로그인/회원가입/마이페이지 등 로그인 정보 나타내는 div -->
        <div id="loginInfo">
        
        	<c:if test="${not empty login }">
        		<a href="logout.do">로그아웃</a>
        	</c:if>
        	
        	<c:if test="${empty login }">
        		<a href="login.do">로그인</a>
        	</c:if>
           
        </div>
        
        <div class="main_menu">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <nav class="navbar navbar-expand-lg navbar-light">
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                <i class="ti-menu"></i>
                            </button>

                            <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
                                <ul class="navbar-nav">
                                    <li class="nav-item active">
                                        <a class="nav-link active" href="main.do">Home</a>
                                    </li>
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                         	자유게시판
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="">강아지</a>
                                            <a class="dropdown-item" href="">고양이</a>
                                             <a class="dropdown-item" href="">기타 소동물</a>
                                        </div>
                                    </li>
                                    <li class="nav-item">
                                        <a href="playboard.do" class="nav-link">소모임</a>
                                    </li>
                                    <li class="nav-item">
                                        <a href="" class="nav-link">후원</a>
                                    </li>
                                    <li class="nav-item">
                                        <a href="" class="nav-link">스토어</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header part end-->
    
    
    
    <!-- 소모임 목록 -->
    <div class="playboardContainer">
    
	    <!-- 검색 & 정렬 -->
	    <div class="searchBar">
	    	
	    	<!-- 검색 -->
	    	<div class="searchDiv">
		    	<span>검색</span>
		    	<select>
		    		<option>선택</option>
		    		<option>제목</option>
		    		<option>장소</option>
		    		<option>내용</option>
		    	</select>
		    	
		    	<input type="search">
		    	
		    	<button>검색</button>	    	
	    	</div>
	    	
	    	<!-- 정렬 -->
		    <div class="sortingDiv">
		    	<span>정렬</span>
		    	<select>
		    		<option>좋아요순</option>
		    		<option>모임일 임박순</option>
		    		<option>마감일 임박순</option>
		    	</select>
		    </div>
		    
	    </div>
	    
	    <!-- 해쉬태그 검색 바 -->
	    <div class="hashtagBar">
	    
	    </div>
	    
	    <!-- 소모임 목록 -->
	    <div class="playListContainer">
	    	<div class="playContent">
	    		<img src="${pageContext.request.contextPath}/common/navbar/img/playthumbnail.jpg">
	    		<div class="palyTitle">소모임 제목</div>
	    		<div class="playDate"><img src="${pageContext.request.contextPath}/playboard_resources/img/calendar.png" width="20px" height="20px;">소모임 날짜</div>
	    	</div>
	    </div>
    
    </div>
    
  <!-- jquery plugins here-->
    <!-- jquery -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/jquery-1.12.1.min.js"></script>
    <!-- popper js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/popper.min.js"></script>
    <!-- bootstrap js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/bootstrap.min.js"></script>
    <!-- counterup js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/jquery.counterup.min.js"></script>
    <!-- waypoints js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/waypoints.min.js"></script>
    <!-- easing js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/jquery.magnific-popup.js"></script>
    <!-- particles js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/owl.carousel.min.js"></script>
    <!-- custom js -->
    <script src="${pageContext.request.contextPath}/common/navbar/js/custom.js"></script>

</body>
</html>