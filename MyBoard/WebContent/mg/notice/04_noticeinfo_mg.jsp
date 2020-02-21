<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        
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
				<td align="center" width="180"> ${bean2.no} </td>
				<td align="center" width="120"> 조회수 </td>
				<td align="center" width="180"> ${bean2.readCount}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120"> 작성자 </td>
				<td align="center" width="180"> 관리자 </td>
				<td align="center" width="120"> 작성일 </td>
				<td align="center" width="180"> ${bean2.writeDate}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120"> 제목 </td>
				<td align="center" colspan="3">  ${bean2.subject} </td>
			</tr>
			<tr height="80">
				<td align="center" width="120"> 글 내용 </td>
				<td align="center" colspan="3">  ${bean2.content}</td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4">
					<input type="button" value="수정하기" onclick="location.href='/MyBoard/notice_update_mg.do?no=${bean2.no}'" >
					<input type="button" value="삭제하기" onclick="location.href='/MyBoard/notice_delete_mg.do?no=${bean2.no}'" >
					<input type="button" value="목록보기" onclick="location.href='/MyBoard/notice_list.do?pageNum2=${sessionScope.pageNum2}'" >
				</td>
			</tr>
		</table>
	</div>
</body>
</html>