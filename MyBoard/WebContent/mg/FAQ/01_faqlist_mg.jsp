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
	<h1 align="center">자주 묻는 질문(FAQ)</h1>
	<div align="center">
		<table width="700" border="1" bgcolor="powderblue">
			<tr height="40">
				<td colspan="6" align="right">
				<button onclick="location.href='mg/FAQ/faqMain_mg.jsp'">홈으로</button> 
				<button onclick="location.href='mg/FAQ/02_faqwrite.jsp'">글쓰기</button></td>
			</tr>
			<tr height="40" align="right">
				<td width="50" align="center"> 번호 </td>
				<td width="100" align="center"> 질문 </td>
				<td width="100" align="center"> 작성자 </td>
				<td width="100" align="center"> 작성일 </td>
				<td width="150" align="center"> 조회수 </td>
			</tr>
			<c:set var="number3" value= "${number3}"/>
			<c:forEach var="bean" items="${v3}">
				<tr height="40">
					<td width="50" align="center"> ${number3} </td>
					<td width="350" align="center">
						<a href="/MyBoard/faq_info.do?num3=${bean.no }&pageNum3=${currentPage3}"> ${bean.subject }</a>
					</td>
					<td width="150" align="center"> 관리자  </td>
					<td width="150" align="center"> ${bean.writeDate}  </td>
					<td width="80" align="center"> ${bean.readCount}  </td>	
				</tr>
				<c:set var="number3" value="${number3 - 1}"/>
			</c:forEach>	
		</table>
		
		<p></p>
		
		<!-- 페이지 카운터링 -->
		<!-- pageSize ==> 한 화면에 보여줄 페이지 수 -->
		<c:set var="pageSize3" value="${3 }"></c:set>
		<!-- pageCount ==> 총 페이지 개수 -->
		<c:set var="pageCount3" value="${count3 / rowSize3 + (count3 % rowSize3 == 0 ? 0 : 1) }"></c:set>
		<c:set var="startPage3" value="1"></c:set>
			<!-- fmt:parseNumber => 숫자를 양식에 맞춰 문자열로 변환해준다.-->
			<fmt:parseNumber var="pageSize3" value="${pageSize3} " integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="pageCount3" value="${pageCount3} " integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="result3" value="${currentPage3/pageSize3} " integerOnly="true"></fmt:parseNumber>
		
		<c:if test="${currentPage3 % pageSize3 ne 0 }">
			<c:set var="startPage3" value="${(result3) * pageSize3 + 1 }"></c:set> 
		</c:if>
		<c:if test="${currentPage3 % pageSize3 eq 0 }">
			<c:set var="startPage3" value="${(result3 - 1) * pageSize3 + 1 }"></c:set>
		</c:if>
		<c:set var="endPage3" value="${startPage3 + pageSize3 - 1 }"></c:set>
		<c:if test="${endPage3 > pageCount3 }">
			<c:set var="endPage3" value="${pageCount3 }"></c:set>
		</c:if>
		
		<div align="center">
		<c:if test="${pageCount3 > pageSize3 && startPage3 > 1 }">
			<a href="/MyBoard/faq_list.do?pageNum3=${startPage3 - pageSize3 }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage3 }" end="${endPage3 }">
			<a href="/MyBoard/faq_list.do?pageNum3=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pageCount3 > pageSize3 && endPage3 < pageCount3 }">
			<a href="/MyBoard/faq_list.do?pageNum2=${startPage3 + pageSize3 }">[이후]</a>
		</c:if>
		</div>
		
		<p></p>
		<!-- 변수 체크용 -->
		<div>
			totalPage 	: ${pageCount3}<br>
			startPage 	: ${startPage3 }<br>
			endPage 	: ${endPage3 }<br>
			currentPage	: ${currentPage3 }<br>
		</div>
	</div>
</body>
</html>