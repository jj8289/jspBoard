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
		<h2 class="title" align="center">사용자 1:1 문의</h2>
		<div class="temp"></div>
		<div class="minicon" align="center">
			<input type="button" value="문의하기" onclick="location.href='02_otowrite.jsp'">
			<input type="button" value="내 문의 리스트" onclick="location.href='/MyBoard/oto_list.do'">
			<input type="button" value="게시판 메인으로" onclick="location.href='/MyBoard/user/00_UserBoardMain.jsp'">
			<!-- 
				location.href 뒤에 ?userID=달러{유저아이디} 추가하기
				 또는 유저 java 파일에서 session만들어서 보내서 oto_info.do에서 session으로 받기
				 <input type="button" value="문의한 내용" onclick="location.href='/MyBoard/oto_info.do'">
			 -->
		
		</div>	 
	</div>
</body>
</html>