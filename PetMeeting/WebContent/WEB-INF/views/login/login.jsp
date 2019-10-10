<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
간이로그인
<form id="login-form">
	<table border="1">
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td colspan="2"><button type="button" id="login-btn">로그인</button></td>
		</tr>
		<tr>
			<td colspan="2"><a href="account.do">회원가입</a></td>
		</tr>
	</table>
</form>

<script type="text/javascript">
$(function () {
	$("#login-btn").on("click", function () {
		var id = $("input[name=email]").val();
		var pwd = $("input[name=pwd]").val();
		
		if(id == "") {
			alert("이메일 입력해라");
		}else if(pwd == "") {
			alert("비밀번호 입력해라");
		}else {
			$("#login-form").attr("action", "loginAf.do").submit();
		}
	});
});
</script>
</body>
</html>