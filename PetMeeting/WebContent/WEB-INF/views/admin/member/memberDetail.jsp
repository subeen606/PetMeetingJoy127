<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Meeting - 회원정보</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link rel="icon"
	href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin_resources/css/admin_common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin_resources/css/memberDetail.css">
</head>
<body>
	<div class="maintitle">
		<img src="./admin_resources/img/memberinfo.png" width="50px"
			style="vertical-align: middle">&nbsp;회원 정보
	</div>

	<table class="memberInfoTable">
		<col width="23%">
		<col width="14%">
		<col width="63%">
		<tr>
			<th>이메일(닉네임)</th>
			<td colspan="2">${detail.email }(${detail.nickname })<img
				src="./admin_resources/img/${detail.grade_name }_icon.png"
				width="50px" style="vertical-align: middle"
				title="회원등급: ${detail.grade_name }">
			</td>
		</tr>

		<tr>
			<th colspan="3" class="subtitle-th">기본 정보</th>
		</tr>

		<tr>
			<th>이름</th>
			<td colspan="2">${detail.name }</td>
		</tr>
		<tr>
			<th rowspan="4">주소</th>
			<td class="addr-td">우편번호</td>
			<td>${detail.postcode }</td>
		</tr>
		<tr>
			<td class="addr-td">주소</td>
			<td>${detail.address }</td>
		</tr>
		<tr>
			<td class="addr-td">상세주소</td>
			<td>${detail.address_detail }</td>
		</tr>
		<tr>
			<td class="addr-td">참고항목</td>
			<td>${detail.address_refer }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td colspan="2">${detail.phone }</td>
		</tr>
		<tr>
			<th>포인트</th>
			<td colspan="2">총 적립 포인트: ${detail.totalpoint }&emsp;/&emsp;가용
				포인트: ${detail.point }</td>
		</tr>
		<tr>
			<th>스토어 적립금</th>
			<td colspan="2">${detail.s_point }</td>
		</tr>
		<tr>
			<th>신고</th>
			<td colspan="2">${detail.reportcount }&emsp;<button type="button" id="reportReasonBtn">신고내역 보기</button></td>
		</tr>

		<tr>
			<th colspan="3" id="myProfile-th" class="subtitle-th">회원 프로필</th>
		</tr>
		<tr class="myProfile-tr">
			<td colspan="3">
				<table class="myProfileTable">				
				<col width="150px"><col width="80px">
				
					<c:if test="${detail.myProfileCheck eq false }">
						<td align="center" colspan="3">등록된 회원 프로필이 없습니다.</td>
					</c:if>
					
					
					<c:if test="${detail.myProfileCheck eq true }">
						<tr style="border: none;">
							<td rowspan="3" align="center">
								<c:if test="${empty detail.myProfile.myprofile_img }">
									<img class="profilePic" alt="" src="./admin_resources/img/user.png">
								</c:if> 
								<c:if test="${not empty detail.myProfile.myprofile_img }">
									<img class="profilePic" src="./admin_resources/img/ppp.jpg">
								</c:if>
							</td>
							<td class="addr-td">나이</td>
							<td>${detail.myProfile.myage }</td>
						</tr>
						<tr>
							<td class="addr-td">성별</td>
							<td>${detail.myProfile.mysex }</td>
						</tr>
						<tr>
							<td class="addr-td">소개글</td>
							<td>${detail.myProfile.myintro }</td>
						</tr>
					</c:if>	
				</table>

			</td>
		</tr>

		<tr>
			<th colspan="3" id="petProfile-th" class="subtitle-th">펫 프로필</th>
		</tr>
		<tr class="petProfile-tr">
			<td colspan="3">
				<table class="petProfileTable">
				<col width="150px"><col width="130px">
					<c:if test="${detail.petProfileCheck eq false }">
						<td align="center" colspan="3">등록된 펫 프로필이 없습니다.</td>
					</c:if>
					
					<c:if test="${detail.petProfileCheck eq true }">
						<tr style="border: none;">
							<td rowspan="7" align="center">
								<c:if test="${empty detail.petProfile.petprofile_img }">
									<img class="profilePic" alt="" src="./admin_resources/img/user.png">
								</c:if> 
								<c:if test="${not empty detail.petProfile.petprofile_img }">
									<img class="profilePic" alt="" src="./admin_resources/img/petprofilepic.png">
								</c:if>
								<br>
								이름: ${detail.petProfile.petname }<br>
								나이: ${detail.petProfile.petage }살
							</td>
							<td class="addr-td">반려동물 유형</td>
							<td>${detail.petProfile.pettype }</td>
							
						</tr>
						
						<tr>
							<td class="addr-td">반려동물 종</td>
							<td>${detail.petProfile.petdetail }</td>
						</tr>
						
						<tr>
							<td class="addr-td">무게</td>
							<td>${detail.petProfile.petweight }</td>
						</tr>
						
						<tr>
							<td class="addr-td">성별</td>
							<td>
								<c:if test="${detail.petProfile.petsex eq 0}">
									<img alt="" src="./admin_resources/img/female.png" width="25px">
								</c:if>
								<c:if test="${detail.petProfile.petsex eq 1}">
									<img alt="" src="./admin_resources/img/male.png" width="25px">
								</c:if>
							</td>
						</tr>
						
						<tr>
							<td class="addr-td">중성화 여부</td>
							<td>
								<c:if test="${detail.petProfile.pettnr eq 1}">O</c:if>
								<c:if test="${detail.petProfile.pettnr eq 0}">X</c:if>
							</td>
						</tr>
						
						<tr>
							<td class="addr-td">반려동물 소개</td>
							<td>${detail.petProfile.petintro }</td>
						</tr>
					</c:if>
				</table>

			</td>
		</tr>

	</table>
<script type="text/javascript">
$(function () {
	
	$("#reportReasonBtn").click(function () {
		var option = "width = 550, height = 500, top = 100, left = 300, location = no, resizeable = no";
		window.open("adminMemberReportReason.do?email=${detail.email }", "report_reason", option);
	});
	/*
	$("#myProfile-th").click(function () {		
		if($(".myProfile-tr").css("display") == "none"){
			$(".myProfile-tr").css("display", "table-row");
		}else if($(".myProfile-tr").css("display") != "none"){
			$(".myProfile-tr").css("display", "none");
		}		
	});
	*/
	
	/*
	$("#petProfile-th").click(function () {
		if($(".petProfile-tr").css("display") == "none"){
			$(".petProfile-tr").css("display", "table-row");
		}else if($(".petProfile-tr").css("display") != "none"){
			$(".petProfile-tr").css("display", "none");
		}
	});
	*/
});

</script>
</body>
</html>