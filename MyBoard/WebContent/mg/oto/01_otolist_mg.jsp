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
		<div id="temp" style="height: 50px"></div>
		<h1>문의 전체 리스트</h1>
	 	<div id="temp" style="height: 50px"></div>
		<table border="1 solid black" style="width: 700" bgcolor="powderblue">
			<tr height="40">
				<td colspan="4" align="right">
					<button onclick="location.href='mg/oto/otoMain_mg.jsp'">관리자 1:1문의 메인</button>
				</td>
			</tr>
			<tr height="40" align="center">
				<td width="50">번호</td>
				<td width="100">작성자</td>
				<td width="300">문의 제목</td>
				<td width="100">문의한 날짜</td>
			</tr>
			<c:set var="number4" value="${number4 }"></c:set>
			<c:forEach var="bean4" items="${v4 }">
				<tr height="40" align="center">
					<td>${number4 }</td>
					<td>${bean4.writer }</td>
					<td><a href="/MyBoard/oto_info_mg.do?num4=${bean4.no }&pageNum4=${currentPage4 }">${bean4.subject }</a></td>
					<td>${bean4.writeDate }</td>
				</tr>
				<c:set var="number4" value="${number4 - 1}"></c:set>
			</c:forEach>
		</table>
		
		<p></p>
		
		<!-- 
			===== 페이지 카운터링 =====
			1. 한 페이지에 나타낼 페이지 수
			2. 총 페이지 수
			3. 한 페이지에서 첫번째 페이지
			4. 한 페이지에서 마지막 페이지
		 -->
		<c:set var="pageSize4" value="${3 }"></c:set>
		<c:set var="pageCount4" value="${(count4 / rowSize4) + (count4 % rowSize4 == 0 ? 0 : 1) }"></c:set>
		<c:set var="startPage4" value="1"></c:set>
			<fmt:parseNumber var="pageSize4" value="${pageSize4 }" integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="pageCount4" value="${pageCount4 }" integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="result4" value="${currentPage4/pageSize4 }" integerOnly="true"></fmt:parseNumber>
		<c:if test="${currentPage4 % pageSize4 ne 0 }">
			<c:set var="startPage4" value="${result4 * pageSize4 + 1 }"></c:set>
		</c:if>
		<c:if test="${currentPage4 % pageSize4 eq 0 }">
			<c:set var="startPage4" value="${(result4 - 1) * pageSize4 + 1 }"></c:set>
		</c:if>
		
		<c:set var="endPage4" value="${startPage4 + pageSize4 - 1 }"></c:set>
		<c:if test="${endPage4 > pageCount4 }">
			<c:set var="endPage4" value="${pageCount4 }"></c:set>
		</c:if>
		
		<div align="center">
		<c:if test="${pageCount4 > pageSize4 && startPage4 > 1 }">
			<a href="/MyBoard/oto_list_mg.do?pageNum4=${startPage4 - pageSize4 }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage4 }" end="${endPage4 }">
			<a href="/MyBoard/oto_list_mg.do?pageNum4=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pageCount4 > pageSize4 && endPage4 < pageCount4 }">
			<a href="/MyBoard/oto_list_mg.do?pageNum4=${startPage4 + pageSize4 }">[이후]</a>
		</c:if>
		</div>
		
		<p></p>
		
		<div align="center">
			게시글 총 개수 : ${count4 }<br>
			pageCount(총 페이지 수) : ${pageCount4 }<br>
			startPage : ${startPage4 }<br>
			endPage : ${endPage4 }<br>
			
		</div>
	</div>
</body>
</html>