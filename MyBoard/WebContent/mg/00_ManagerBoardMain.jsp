<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
	.temp{
		height: 40px;
	}
</style>
<style>
	body{
		background-color: black;
		color: white;
	}
</style>
</head>
<body>
	<!-- 관리자 게시판 메인 -->
	<div id="container" style="width: 800px; margin: 0 auto;">
		<div class="temp"></div>
		<h2 class="title" align="center">관리자 모드 게시판</h2>
		<div class="temp"></div>
		<div align="right">
			<input type="button" value="메인으로" onclick="location.href='/MyBoard/main.jsp'">
		</div>
		<div class="minicon" align="center">
			<table align="center">
				<tr height="50">
					<td align="center" width="200" bgcolor="powderblue">
						<font color="white" size="5">
							<a href="notice/noticeMain_mg.jsp" style="text-decoration: none">공지사항/이벤트</a>
						</font>
					</td>
					<td align="center" width="200" bgcolor="powderblue">
						<font color="white" size="5">
							<a href="oto/otoMain_mg.jsp" style="text-decoration: none">1:1 문의</a>
						</font>
					</td>
					<td align="center" width="200" bgcolor="powderblue">
						<font color="white" size="5">
							<a href="FAQ/faqMain_mg.jsp" style="text-decoration: none">FAQ</a>
						</font>
					</td>
					<td align="center" width="200" bgcolor="powderblue">
						<font color="white" size="5">
							<a href="free/freeMain_mg.jsp" style="text-decoration: none">자유게시판</a>
						</font>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>