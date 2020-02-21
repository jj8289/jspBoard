<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container" align="center" style="margin: 0 atuo;">
		<div class="temp"></div>
		<h2 class="title">게시판</h2>
		<div class="temp"></div>
		<div class="minicon">
			<input type="button" value="사용자 모드" onclick="location.href='user/00_UserBoardMain.jsp'">
			<input type="button" value="관리자 모드" onclick="location.href='mg/00_ManagerBoardMain.jsp'">
			<input type="button" value="전체 게시글" onclick="location.href='/MyBoard/free_list.do'">
		</div>
	</div>
</body>
</html>	
