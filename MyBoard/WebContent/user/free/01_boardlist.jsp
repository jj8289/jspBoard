<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!-- jstl 쓰기 위해서 선언을 해줘야 함!! -->
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">전체 게시글</h1>
	<div align="center">
		<table width="700" border="1" bgcolor="powderblue">
			<tr height="40">
				<td colspan="5" align="right">
				<button onclick="location.href='/MyBoard/user/free/freeMain.jsp'">사용자 게시판 메인</button>
				<button onclick="location.href='/MyBoard/user/free/02_boardwrite.jsp'">글쓰기</button></td>
			</tr>
			<tr height="40" align="right">
				<td width="50" align="center"> 번호 </td>
				<td width="320" align="center"> 제목</td>
				<td width="100" align="center"> 작성자 </td>
				<td width="150" align="center"> 작성일 </td>
				<td width="80" align="center"> 조회수 </td>	
			</tr>
			<c:set var="number" value= "${number}"/>
			<c:forEach var="bean" items="${v}">
			 <tr height="40">
				<td width="50" align="center"> ${number} </td>
				<td width="320" align="left">
					<c:if test="${bean.reStep > 1 }">
					<c:forEach var="j" begin="1" end="${(bean.reStep -1) * 3}">
						&nbsp;
					</c:forEach>
					</c:if>
					<a href="/MyBoard/free_info.do?num=${bean.no }&pageNum=${currentPage}"> ${bean.subject }</a>
				</td>
				<td width="100" align="center">${bean.writer} </td>
				<td width="150" align="center"> ${bean.writeDate}  </td>
				<td width="80" align="center"> ${bean.readCount}  </td>	
			</tr>
			<c:set var="number" value="${number - 1}"/>
			</c:forEach>	
		</table>
		
		<p></p>
		
			<!-- 
			java 내용
			request.setAttribute("v", v);				//보여줄 게시글 담은 벡터
			request.setAttribute("number", numInweb);	//웹에서 보여줄 최대 번호
			request.setAttribute("rowSize", rowSize);	//보여줄 row 개수
			request.setAttribute("count", allcount);	//전체 게시글 개수
			request.setAttribute("currentPage", currentPage);// 현재 페이지 넘버
			 -->
		
		<!-- 페이지 카운터링 -->
		<!-- pageSize ==> 한 화면에 보여줄 페이지 수 -->
		<c:set var="pageSize" value="${3 }"></c:set>
		<!-- pageCount ==> 총 페이지 개수 -->
		<c:set var="pageCount" value="${count / rowSize + (count % rowSize == 0 ? 0 : 1) }"></c:set>
		<c:set var="startPage" value="1"></c:set>
			<!-- fmt:parseNumber => 숫자를 양식에 맞춰 문자열로 변환해준다.-->
			<fmt:parseNumber var="pageSize" value="${pageSize} " integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="pageCount" value="${pageCount} " integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="result" value="${currentPage/pageSize} " integerOnly="true"></fmt:parseNumber>
		
		<c:if test="${currentPage % pageSize ne 0 }">
			<c:set var="startPage" value="${(result) * pageSize + 1 }"></c:set> 
		</c:if>
		<c:if test="${currentPage % pageSize eq 0 }">
			<c:set var="startPage" value="${(result - 1) * pageSize + 1 }"></c:set>
		</c:if>
		<c:set var="endPage" value="${startPage + pageSize - 1 }"></c:set>
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }"></c:set>
		</c:if>
		
		<div align="center">
		<c:if test="${pageCount > pageSize && startPage > 1 }">
			<a href="/MyBoard/free_list.do?pageNum=${startPage - pageSize }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<a href="/MyBoard/free_list.do?pageNum=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pageCount > pageSize && endPage < pageCount }">
			<a href="/MyBoard/free_list.do?pageNum=${startPage + pageSize }">[이후]</a>
		</c:if>
		</div>
		
		<p></p>
		<!-- 변수 체크용 -->
		<div>
			totalPage 	: ${pageCount}<br>
			startPage 	: ${startPage }<br>
			endPage 	: ${endPage }<br>
			currentPage	: ${currentPage }<br>
		</div>
	</div>
</body>
</html>