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
				<td align="center" width="180"> ${bean3.no} </td>
				<td align="center" width="120"> 조회수 </td>
				<td align="center" width="180"> ${bean3.readCount}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120"> 작성자 </td>
				<td align="center" width="180"> 관리자 </td>
				<td align="center" width="120"> 작성일 </td>
				<td align="center" width="180"> ${bean3.writeDate}</td>
			</tr>
			<tr height="40">
				<td align="center" width="120"> 질문 </td>
				<td align="center" colspan="3">  ${bean3.subject} </td>
			</tr>
			<tr height="80">
				<td align="center" width="120"> 답변 </td>
				<td align="center" colspan="3">  ${bean3.answer}</td>
			</tr>
			<tr height="40">
				<td align="center" colspan="4">
					<!-- 수정, 삭제 버튼은 관리자 vs 유저에 따라 없애고 생기게 하기 -->
					<!-- 
						유저일 때
						<input type="button" value="목록보기" onclick="location.href='/MyBoard/faq_list.do?pageNum3=${sessionScope.pageNum3}'" >
					 -->
					<!-- 관리자일 때 -->
					<input type="button" value="수정하기" onclick="location.href='/MyBoard/faq_update_mg.do?no=${bean3.no}'" >
					<input type="button" value="삭제하기" onclick="location.href='/MyBoard/faq_delete_mg.do?no=${bean3.no}'" >
					<input type="button" value="목록보기" onclick="location.href='/MyBoard/faq_list.do?pageNum3=${sessionScope.pageNum3}'" >
				</td>
			</tr>
		</table>
	</div>
</body>
</html>