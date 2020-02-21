<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.temp{
		height: 50px;
	}
	.temp2{
		height: 100px;
	}
	.title{
		width: 100%;
		background-color: powderblue;
		margin: 0;
		height: 45px;
		font-size: 30px;
		text-align: center;
	}
	
</style>
</head>
<body>
	<div id="container" align="center">
		<div class="temp"></div>
		<h2 class="title">게시판</h2>
		<div class="temp2"></div>
		<form action="/MyBoard/free_write.do" method="post">
			<table border="1">
				<tr height="40">
					<td align="center" width="200px">작성자</td>
					<td width="450px">&nbsp; <input type="text" name="writer" size="60"></td>
				</tr>
				<tr height="40">
					<td align="center" width="200px">제목</td>
					<td width="450px">&nbsp; <input type="text" name="subject" size="60"></td>
				</tr>
				<tr height="40">
					<td align="center" width="200px">비밀번호</td>
					<td width="450px">&nbsp; <input type="text" name="password" size="60"></td>
				</tr>
				<tr height="40">
					<td align="center" width="200px">글내용</td>
					<td width="450px">&nbsp; <textarea rows="10" cols="60" name="content"></textarea></td>
				</tr>
				<tr height="40">
					<td colspan=2 align="center">
						<input type="submit" value="글쓰기"> &nbsp;&nbsp;
						<input type="reset" value="리셋"> &nbsp;&nbsp;
						<input type="button" value="전체 게시글보기" onclick="location.href='/MyBoard/free_list.do'">
					</td>	
				</tr>
			</table>
		</form>
	</div>
</body>
</html>