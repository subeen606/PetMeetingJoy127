<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PetMeeting-채팅</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  
	<link rel="icon" href="${pageContext.request.contextPath}/common/navbar/img/petmeetingicon.png">
</head>
<body>
	 <!--::header part start::-->
    <header class="header_area">
    	<jsp:include page="/common/navbar/templates/header.jsp" flush="false"/>
    </header>
    <!-- Header part end-->
    
    
    
 <!-- 채팅명: <input type="text" id="nickname"> -->

<input type="button" id="enterBtn" onclick="connect()" value="입장">
<input type="button" id="exitBtn" onclick="disconnect()" value="나가기">

<br><br>

<h1>대화영역</h1>
<div id="chatArea">
	<div id="chatMessageArea">
	
	</div>
</div>
<input type="text" id="meassage">
<input type="button" id="sendBtn" onclick="send()" value="전송">

<div id="chatMems"></div>

<div id="onlines"></div>

<script>

// 웹 소켓 변수
var wsocket =null;
var memlist = new Array();
var templist = new Array();

// 입장버튼 클릭시 호출되는 함수
function connect(){
	if(wsocket != undefined && wsocket.readySatate != WebSocket.CLOSED){	// 이미 socket이 생성된 경우
		alert("이미 입장하셨습니다");
		return;
	}
	
	// WebSocket생성
	if("${login.nickname }" == "미키맘"){
		wsocket = new WebSocket("ws://localhost:8090/PetMeeting/echo.do");
	}else{
		wsocket = new WebSocket("ws://192.168.31:8090/PetMeeting/echo.do");
	}
	//wsocket = new WebSocket("ws://localhost:8090/190829SpringSamples/echo.do");	// echo.do --> handler에서 처리
//	alert("wsocket : " + wsocket);
	
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.close = onClose;
	//console.log("wsocket : " + WebSocket.state);
}



function disconnect(){
	//wsocket.close();
	memlist.slice(0, memlist.length);
	wsocket.close();
	location.href="chatting.do";
}


// onopen(연결), onmessage(메시지수신), onclose(연결끊김)
function onOpen(event){
	appendMessage("채팅방에 입장하셨습니다");	
}



// 서버로부터 메시지가 도착했을 때 호출되는 함수
function onMessage(event){
	
	var data = event.data;
	/*
	if(data.substring(0, 4) == 'msg:'){
		appendMessage(data.substring(4));	// 4번째 글짜부터 모두
	}
	*/
	appendMessage(data);
	/*
	if(data == "나감"){
		memlist = 0;
	}
	*/
	var list = JSON.parse(data);

	
	if(list != null){
		//console.log(list);		
		if(memlist.indexOf(list.list) == -1){	// 새로 접속된 경우
			memlist.push(list.list);
		}
		
		$("#onlines").html(list.list);
	}
	
	/* for(i=0; i<memlist.length; i++){
		if(templist.indexOf(memlist[i]) == -1){
			memlist.slice(i, 1);
		}
	} */
	console.log("memlist : " + memlist); 	
	
	$("#chatMems").text("");
	memlist.forEach(function (item, index, array) {  			
  		$("#chatMems").text(item + "<br>"); 			 		
	});
	
	

}


function onClose(event){
	appendMessage("채팅방과의 연결이 종료되었습니다");
}


function send(){
	//var nickname = $("#nickname").val();
	var msg = $("#meassage").val();
	
	// 실제 전송부분
	wsocket.send("msg:" + "${login.nickname }" + " : " + msg);
	
	$("#meassage").val("");
	
}


function appendMessage(msg) {
	
	// 메시지를 추가하고 개행
	$("#chatMessageArea").append(msg + "<br>");
	
	var chatAreaHeight = $("#chatArea").height();
	var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
	
	 $("#chatArea").scrollTop(maxScroll);
	
}
</script>

</body>
</html>