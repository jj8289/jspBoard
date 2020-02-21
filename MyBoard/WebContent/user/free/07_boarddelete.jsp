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
		<div class="temp"></div>
		<h2 class="title">게시글 삭제</h2>
		<div class="temp2"></div>
		<form action="/MyBoard/free_deletePro.do" method="post">
			<table border="1">
				<tr height="40">
					<td align="center" width="120"> 글번호 </td>
					<td align="center" width="180"> ${board.no} </td>
					<td align="center" width="120"> 조회수 </td>
					<td align="center" width="180"> ${board.readCount}</td>
				</tr>
				<tr height="40">
					<td align="center" width="120"> 작성자 </td>
					<td align="center" width="180">  ${board.writer} </td>
					<td align="center" width="120"> 작성일 </td>
					<td align="center" width="180"> ${board.writeDate}</td>
				</tr>
				<tr height="40">
					<td align="center" width="120"> 제목 </td>
					<td align="center" colspan="3">
						<input type="text" name="subject" value="${board.subject }" size="65">
					</td>
				</tr>
				<tr height="40">
					<td width="100" align="center" colspan="1">비밀번호</td>
					<td width="100" align="center" colspan="3">
						<input type="text" name="password" size="65">
					</td>
				</tr>
				<tr height="80">
					<td align="center" width="120"> 글 내용 </td>
					<td align="center" colspan="3">
						<textarea rows="10" cols="60" name="content">${board.content }</textarea>	
					</td>
				</tr>
				<tr height="40">
					<td colspan=4 align="center">
						<input type="hidden" name="no" value="${board.no }">
						<input type="submit" value="삭제하기"> &nbsp;&nbsp;
						<input type="reset" value="리셋"> &nbsp;&nbsp;
						<input type="button" value="전체 게시글보기" onclick="location.href='/MyBoard/free_list.do?pageNum=${sessionScope.pageNum}'">
					</td>	
				</tr>
			</table>
		</form>
	</div>
</body>
</html>