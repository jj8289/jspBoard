<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.temp{
		height: 40px;
	}
</style>
</head>
<body>
	<div id="container" style="width: 800px; margin: 0 auto;">
		<div class="temp"></div>
		<h2 class="title" align="center">사용자 자주 묻는 질문</h2>
		<div class="temp"></div>
		<div class="minicon" align="center">
			<h3 align="center">버튼 버전</h3>
			<input type="button" value="전체 질문" onclick="location.href='/MyBoard/faq_list.do'">
			<input type="button" value="게시판 메인으로" onclick="location.href='/MyBoard/user/00_UserBoardMain.jsp'">
			
			<p></p>
			<!-- 
			<h3 align="center">글씨 버전</h3>
			<p><a href="#">전체 질문</a></p>
			<p><a href="/MyBoard/user/00_UserBoardMain.jsp">게시판 메인으로</a></p>
			 -->
		</div>	
	</div>
</body>
</html>