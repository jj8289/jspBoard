<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body{
		background-color: black;
		color: white;
	}
</style>
<style>
	.temp{
		height: 40px;
	}
</style>
</head>
<body>
	<div id="container" style="width: 800px; margin: 0 auto;">
		<div class="temp"></div>
		<h2 class="title" align="center">관리자 공지사항/이벤트</h2>
		<div class="temp"></div>
		<div class="minicon" align="center">
			<input type="button" value="공지사항" onclick="location.href='/MyBoard/notice_list.do'">
			<input type="button" value="게시판 메인으로" onclick="location.href='/MyBoard/mg/00_ManagerBoardMain.jsp'">
		</div>	
	</div>
</body>
</html>