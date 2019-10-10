<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
회원가입
<form id="account-form">
	<table border="1">
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="pwd"></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td><input type="text" name="nickname"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<input type="text" id="sample6_postcode" name="postcode" placeholder="우편번호" readonly="readonly">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
			</td>
		</tr>
		
		<tr>
			<th>주소</th>
			<td><input type="text" id="sample6_address" name="address" placeholder="주소" readonly="readonly"></td>
		</tr>
		
		<tr>
			<th rowspan="2">상세주소</th>
			<td><input type="text" id="sample6_detailAddress" name="address_detail" placeholder="상세주소"></td>
		</tr>
		<tr>
			<td><input type="text" id="sample6_extraAddress" name="address_refer" placeholder="참고항목" readonly="readonly"></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" id="account-btn">회원가입</button>
			</td>
		</tr>
	</table>
</form>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

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

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

$(function () {
	$("#account-btn").on("click", function () {
		var email = $("input[name=email]").val();
		var pwd = $("input[name=pwd]").val();
		var nickname = $("input[name=nickname]").val();
		var name = $("input[name=name]").val();
		var address = $("input[name=address]").val();
		var address_detail = $("input[name=address_detail]").val();
		var phone = $("input[name=phone]").val();
		
		if(email == "") {
			alert("이메일을 입력해주세요");
		}else if(pwd == "") {
			alert("비밀번호를 입력해주세요");
		}else if(nickname == "") {
			alert("닉네임을 입력해주세요");
		}else if(name == "") {
			alert("이름을 입력해주세요");
		}else if(address == "") {
			alert("주소를 입력해주세요");
		}else if(address_detail == "") {
			alert("상세주소를 입력해주세요");
		}else if(phone == "") {
			alert("핸드폰번호를 입력해주세요");
		}else {
			$("#account-form").attr("action", "accountAf.do").submit();
		}
	});
});
</script>

</body>
</html>