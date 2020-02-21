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
		<h2 class="title">게시글 수정</h2>
		<div class="temp2"></div>
		<form action="/MyBoard/notice_update_mgPro.do" method="post">
			<table border="1">
				<tr height="40">
					<td align="center" width="120"> 글번호 </td>
					<td align="center" width="180"> ${bean.no} </td>
					<td align="center" width="120"> 조회수 </td>
					<td align="center" width="180"> ${bean.readCount}</td>
				</tr>
				<tr height="40">
					<td align="center" width="120"> 작성자 </td>
					<td align="center" width="180"> 관리자 </td>
					<td align="center" width="120"> 작성일 </td>
					<td align="center" width="180"> ${bean.writeDate}</td>
				</tr>
				<tr height="40">
					<td align="center" width="120"> 제목 </td>
					<td align="center" colspan="3">
						<input type="text" name="subject" value="${bean.subject }" size="65">
					</td>
				</tr>
				<tr height="80">
					<td align="center" width="120"> 글 내용 </td>
					<td align="center" colspan="3">
						<textarea rows="10" cols="60" name="content">${bean.content }</textarea>	
					</td>
				</tr>
				<tr height="40">
					<td colspan=4 align="center">
						<input type="hidden" name="no" value="${bean.no }">
						<input type="submit" value="수정하기"> &nbsp;&nbsp;
						<input type="reset" value="리셋"> &nbsp;&nbsp;
						<input type="button" value="전체 게시글보기" onclick="location.href='/MyBoard/notice_list.do?pageNum2=${sessionScope.pageNum2 }'">
					</td>	
				</tr>
			</table>
		</form>
	</div>
</body>
</html>