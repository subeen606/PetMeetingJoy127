<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	<< [1][2][3][4][5][6][7][8][9][10] >>
	<< ...[4][5][6] 7 [8][9][10]... >>
*/

int totalRecordCount;		// 전체 글 수
int pageNumber;				// 현재 페이지 0~9	[1]~[10]
int pageCountPerScreen; 	// 스크린당 페이지 수 = 10
int recordCountPerPage; 	// 한 페이지에 표시할 글 수 

String st1 = request.getParameter("totalRecordCount");
if(st1 == null){
	totalRecordCount = 0;
}else{
	totalRecordCount = Integer.parseInt(st1);
}

String st2 = request.getParameter("pageNumber");
if(st2 == null){	/* 0이 1페이지가 됨 */
	pageNumber = 0;
}else{
	pageNumber = Integer.parseInt(st2);
}

String st3 = request.getParameter("pageCountPerScreen");
if(st3 == null){
	pageCountPerScreen = 0;
}else{
	pageCountPerScreen = Integer.parseInt(st3);
}

String st4 = request.getParameter("recordCountPerPage");
if(st4 == null){
	recordCountPerPage = 0;
}else{
	recordCountPerPage = Integer.parseInt(st4);
}


// 총 페이지 수 구하기
int totalPageCount = totalRecordCount / recordCountPerPage;		/* 총  row 수를 한 페이지에 게시할 row의 개수(10개)로 나눈다 */
if((totalRecordCount % recordCountPerPage) != 0){	/* 만약 총 row 수가 10개면 1페이지만 있으면 되지만, 12개면 2페이지가 필요하므로 10으로 나눠서 딱 떨어지지 않으면 1을 더해 총 페이지 개수를 맞춰줌 */
	totalPageCount++;
}

// 시작페이지
int screenStartPageIndex = ((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen;
							

// 끝페이지
int screenEndPageIndex = (((pageNumber + 1) / pageCountPerScreen) * pageCountPerScreen) + pageCountPerScreen;

// 끝 페이지는 다시 계산
if(screenEndPageIndex > totalPageCount){	/* 만약 총 페이지 수가 7페이지인데 위에서 10이 나오면 총 페이지수를 대입한다! */
	screenEndPageIndex = totalPageCount;
}

if((pageNumber + 1) % pageCountPerScreen == 0){
	screenStartPageIndex = (((pageNumber+1)/pageCountPerScreen)*pageCountPerScreen)-pageCountPerScreen;
	screenEndPageIndex = pageNumber + 1;	// 0 -> 1			9 -> 10
}

%>

<div style="float: left; width: 96%; text-align: center;">
	<!-- << (첫 페이지) -->
	<a href="#none" title="처음 페이지" onclick="goPage('0')">
		<img alt="" src="playboard_resources/img/firstPage.png" style="width: 15px; height: 15px; vertical-align: middle;">
	</a>
	
	
	<!-- < (이전페이지) -->
	<%
	if(screenStartPageIndex > 1){%>
		<a href="#none" title="이전 페이지" onclick="goPage('<%=screenStartPageIndex-1 %>')">
			<img alt="" src="playboard_resources/img/prevPage.png" style="width: 15px; height: 15px; vertical-align: middle;">
		</a>
	<%
	}
	%>
	
	<!-- [1][2] 3  -->
	<%
	for(int i=screenStartPageIndex; i<screenEndPageIndex; i++){
		//현재페이지
		if(i == pageNumber){%>	
			<span style="font-size: 15pt; color: black; font-weight: bold;">
				<%=i+1 %>
			</span>
		
		<%	
		}else{%>
			<a href="#none" title="<%=i+1 %>페이지" onclick="goPage('<%=i %>')" style="font-size: 12pt; color: gray; font-weight: bold;">
				<%=i+1 %>
			</a>
		<%	
		}
	}
	%>
	
	
	<!-- > -->
	<%
	if(screenEndPageIndex < totalPageCount){%>
		<a href="#none" title="다음 페이지" onclick="goPage(<%=screenEndPageIndex %>)">
			<img alt="" src="playboard_resources/img/nextPage.png" style="width: 15px; height: 15px; vertical-align: middle;">
		</a>
	
	<%		
	}
	%>
	
	
	<!-- >> -->
	<%
	int end_page = 0;
	if(totalPageCount > 0){
		end_page = totalPageCount-1;
	}
	%>
	
	<a href="#none" title="마지막 페이지" onclick="goPage(<%=end_page %>)">
		<img alt="" src="playboard_resources/img/lastPage.png" style="width: 15px; height: 15px; vertical-align: middle;">
	</a>
	
</div>