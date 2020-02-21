<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="container" style="margin: 0 auto" align="center">
		<div id="temp" style="height: 50px"></div>
		<h1>1:1 문의 - 답변하기</h1>
		<div id="temp" style="height: 50px"></div>
		<form action="/MyBoard/oto_rewrite_mgPro.do" method="post">
			<table border="1" style="width: 700">
				<tr height="50" align="center">
					<td width="150">질문 제목</td>
					<td width="350">${bean4.subject }</td>
				</tr>
				<tr height="50" align="center">
					<td width="150">질문 내용</td>
					<td width="350">${bean4.content }</td>
				</tr>
				<tr height="50" align="center">
					<td width="150">답변 내용</td>
					<td width="350"><textarea name="answer" rows="15" cols="80"> 안녕하세요~ 관리자입니다. 문의 내용 잘 살펴보았습니다. </textarea></td>
				</tr>
				<tr height="50" align="center">
					<td colspan="2">
						<input type="hidden" name="no" value="${bean4.no }">
						<input type="submit" value="답변하기" size="60">
						<input type="reset" value="리셋" size="60">
						<input type="button" value="목록으로" onclick="location.href='/MyBoard/oto_list_mg.do?pageNum4=${sessionScope.pageNum4}'" size="60">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>