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
		<h1>나의 1:1 문의 리스트</h1>
	 	<div id="temp" style="height: 50px"></div>
		<table border="1 solid black" style="width: 700" bgcolor="powderblue">
			<tr height="40">
				<td colspan="4" align="right">
					<button onclick="location.href='user/oto/02_otowrite.jsp'">문의하기</button>
					<button onclick="location.href='user/oto/otoMain.jsp'">1:1문의 메인</button>
				</td>
			</tr>
			<tr height="40" align="center">
				<td width="50">번호</td>
				<td width="100">답변유무</td>
				<td width="300">문의 제목</td>
				<td width="100">문의한 날짜</td>
			</tr>
			<c:set var="number5" value="${number5 }"></c:set>
			<c:forEach var="bean5" items="${v5 }">
				<tr height="40" align="center">
					<td>${number5 }</td>
					<td>
						<c:if test="${bean5.complete eq true }">완료</c:if>
						<c:if test="${bean5.complete ne true }">미완료</c:if>
					</td>
					<td><a href="/MyBoard/oto_info.do?num5=${bean5.no }&pageNum5=${currentPage5 }">${bean5.subject }</a></td>
					<td>${bean5.writeDate }</td>
				</tr>
				<c:set var="number5" value="${number5 - 1}"></c:set>
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
		<c:set var="pageSize5" value="${3 }"></c:set>
		<c:set var="pageCount5" value="${(count5 / rowSize5) + (count5 % rowSize5 == 0 ? 0 : 1) }"></c:set>
		<c:set var="startPage5" value="1"></c:set>
			<fmt:parseNumber var="pageSize5" value="${pageSize5 }" integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="pageCount5" value="${pageCount5 }" integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="result5" value="${currentPage5/pageSize5 }" integerOnly="true"></fmt:parseNumber>
		<c:if test="${currentPage5 % pageSize5 ne 0 }">
			<c:set var="startPage5" value="${result5 * pageSize5 + 1 }"></c:set>
		</c:if>
		<c:if test="${currentPage5 % pageSize5 eq 0 }">
			<c:set var="startPage5" value="${(result5 - 1) * pageSize5 + 1 }"></c:set>
		</c:if>
		
		<c:set var="endPage5" value="${startPage5 + pageSize5 - 1 }"></c:set>
		<c:if test="${endPage5 > pageCount5 }">
			<c:set var="endPage5" value="${pageCount5 }"></c:set>
		</c:if>
		
		<div align="center">
		<c:if test="${pageCount5 > pageSize5 && startPage5 > 1 }">
			<a href="/MyBoard/oto_list.do?pageNum5=${startPage5 - pageSize5 }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage5 }" end="${endPage5 }">
			<a href="/MyBoard/oto_list.do?pageNum5=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pageCount5 > pageSize5 && endPage5 < pageCount5 }">
			<a href="/MyBoard/oto_list.do?pageNum5=${startPage5 + pageSize5 }">[이후]</a>
		</c:if>
		</div>
		
		<p></p>
		
		<div align="center">
			게시글 총 개수 : ${count5 }<br>
			pageCount(총 페이지 수) : ${pageCount5 }<br>
			startPage : ${startPage5 }<br>
			endPage : ${endPage5 }<br>
			
		</div>
	</div>
</body>
</html>