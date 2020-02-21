<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
		<h2 class="title">공지사항 / 이벤트</h2>
		<div class="temp2"></div>
		<form action="/MyBoard/notice_write_mg.do" method="post">
			<table border="1">
				<tr height="40">
					<td align="center" width="200px">작성자</td>
					<td width="450px">&nbsp; <input type="text" readonly value="관리자" size="60"></td>
				</tr>
				<tr height="40">
					<td align="center" width="200px">종류</td>
					<td>&nbsp;
						<select name="kind" size="1" style="width: 100px; height: 30px">
							<option value="1">공지사항</option>
							<option value="2">이벤트</option>
						</select>
					</td>	
				</tr>
				<tr height="40">
					<td align="center" width="200px">제목</td>
					<td width="450px">&nbsp; <input type="text" name="subject" size="60"></td>
				</tr>
				<tr height="40">
					<td align="center" width="200px">글내용</td>
					<td width="450px">&nbsp; <textarea rows="10" cols="60" name="content"></textarea></td>
				</tr>
				<tr height="40">
					<td colspan=2 align="center">
						<input type="submit" value="글쓰기"> &nbsp;&nbsp;
						<input type="reset" value="리셋"> &nbsp;&nbsp;
						<input type="button" value="전체 공지글보기" onclick="location.href='/MyBoard/notice_list.do'">
					</td>	
				</tr>
			</table>
		</form>
	</div>
</body>
</html>