<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2> 게시글 보기 </h2>
		<table border="1">
			<tr height="40">
				<td align="center" width="120"> 글번호 </td>
				<td align="center" width="180"> ${bean.no} </td>
				<td align="center" width="120"> 조회수 </td>
				<td align="center" width="180"> ${bean.readCount}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120"> 작성자 </td>
				<td align="center" width="180">  ${bean.writer} </td>
				<td align="center" width="120"> 작성일 </td>
				<td align="center" width="180"> ${bean.writeDate}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120"> 제목 </td>
				<td align="center" colspan="3">  ${bean.subject} </td>
			</tr>
			<tr height="80">
				<td align="center" width="120"> 글 내용 </td>
				<td align="center" colspan="3">  ${bean.content}</td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4">
					
					<input type="button" value="답글쓰기" onclick="location.href='/MyBoard/free_rewrite.do?no=${bean.no}'">
					<input type="button" value="수정하기" onclick="location.href='/MyBoard/free_update.do?no=${bean.no}'" >
					<!-- id가 유저일 때 -->
					<input type="button" value="삭제하기" onclick="location.href='/MyBoard/free_delete.do?no=${bean.no}'" >
					<!-- 
						id가 관리자일 때
						<input type="button" value="삭제하기" onclick="location.href='/MyBoard/free_delete_mg.do?no=${bean.no}'" >	
					 -->
					<input type="button" value="목록보기" onclick="location.href='/MyBoard/free_list.do?pageNum=${sessionScope.pageNum}'" >
				</td>
			</tr>
		</table>
	</div>
</body>
</html>