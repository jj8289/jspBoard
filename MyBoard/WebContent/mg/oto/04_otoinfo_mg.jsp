<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container" align="center">
		<div id="temp" style="height: 50px"></div>
		<h1>글 정보</h1>
		<div id="temp" style="height: 50px"></div>
		<table border="1" style="width: 700">
			<tr height="50" align="center">
				<td width="100">글 번호</td>
				<td width="200">${bean4.no }</td>
				<td width="100">문의한 날짜</td>
				<td width="200">${bean4.writeDate }</td>
			</tr>
			<tr height="50" align="center">
				<td colspan="1">질문</td>
				<td colspan="3">${bean4.subject }</td>
			</tr>
			<tr height="50" align="center">
				<td colspan="1">유저 id</td>
				<td colspan="3">${bean4.writer }</td>
			</tr>
			<tr height="50" align="center">
				<td colspan="1">문의 내용</td>
				<td colspan="3">${bean4.content }</td>
			</tr>
			<tr height="50" align="center">
				<td colspan="4">
					<input type="button" value="답변하기" onclick="location.href='/MyBoard/oto_rewrite_mg.do?no=${bean4.no }'">
					<input type="button" value="목록으로" onclick="location.href='/MyBoard/oto_list_mg.do?pageNum4=${sessionScope.pageNum4 }'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>