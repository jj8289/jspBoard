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
	<h1 align="center">공지사항/이벤트</h1>
	<div align="center">
		<table width="700" border="1" bgcolor="powderblue">
			<tr height="40">
				<td colspan="6" align="right">
					<button onclick="location.href='mg/notice/noticeMain_mg.jsp'">홈으로</button>
					<button onclick="location.href='mg/notice/02_noticewrite.jsp'">글쓰기</button>
				</td>
			</tr>
			<tr height="40" align="right">
				<td width="50" align="center"> 번호 </td>
				<td width="100" align="center"> 종류 </td>
				<td width="100" align="center"> 제목 </td>
				<td width="150" align="center"> 작성자 </td>
				<td width="150" align="center"> 작성일 </td>
				<td width="80" align="center"> 조회수 </td>	
			</tr>
			<c:set var="number2" value= "${number2}"/>
			<c:forEach var="bean" items="${v2}">
			<tr height="40">
				<td width="50" align="center"> ${number2} </td>
				<td width="100" align="center">
					<c:if test="${bean.kind == 1 }">
						공지사항
					</c:if>
					<c:if test="${bean.kind == 2 }">
						이벤트
					</c:if>
				</td>	
				<td width="320" align="center">
					<a href="/MyBoard/notice_info.do?num2=${bean.no }&pageNum2=${currentPage2}"> ${bean.subject }</a>
				</td>
				<td width="150" align="center"> 관리자  </td>
				<td width="150" align="center"> ${bean.writeDate}  </td>
				<td width="80" align="center"> ${bean.readCount}  </td>	
			</tr>
			<c:set var="number2" value="${number2 - 1}"/>
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
		<c:set var="pageSize2" value="${3 }"></c:set>
		<!-- pageCount ==> 총 페이지 개수 -->
		<c:set var="pageCount2" value="${count2 / rowSize2 + (count2 % rowSize2 == 0 ? 0 : 1) }"></c:set>
		<c:set var="startPage2" value="1"></c:set>
			<!-- fmt:parseNumber => 숫자를 양식에 맞춰 문자열로 변환해준다.-->
			<fmt:parseNumber var="pageSize2" value="${pageSize2} " integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="pageCount2" value="${pageCount2} " integerOnly="true"></fmt:parseNumber>
			<fmt:parseNumber var="result2" value="${currentPage2/pageSize2} " integerOnly="true"></fmt:parseNumber>
		
		<c:if test="${currentPage2 % pageSize2 ne 0 }">
			<c:set var="startPage2" value="${(result2) * pageSize2 + 1 }"></c:set> 
		</c:if>
		<c:if test="${currentPage2 % pageSize2 eq 0 }">
			<c:set var="startPage2" value="${(result2 - 1) * pageSize2 + 1 }"></c:set>
		</c:if>
		<c:set var="endPage2" value="${startPage2 + pageSize2 - 1 }"></c:set>
		<c:if test="${endPage2 > pageCount2 }">
			<c:set var="endPage2" value="${pageCount2 }"></c:set>
		</c:if>
		
		<div align="center">
		<c:if test="${pageCount2 > pageSize2 && startPage2 > 1 }">
			<a href="/MyBoard/notice_list.do?pageNum2=${startPage2 - pageSize2 }">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage2 }" end="${endPage2 }">
			<a href="/MyBoard/notice_list.do?pageNum2=${i }">[${i }]</a>
		</c:forEach>
		<c:if test="${pageCount2 > pageSize2 && endPage2 < pageCount2 }">
			<a href="/MyBoard/notice_list.do?pageNum2=${startPage2 + pageSize2 }">[이후]</a>
		</c:if>
		</div>
		
		<p></p>
		<!-- 변수 체크용 -->
		<div>
			totalPage 	: ${pageCount2}<br>
			startPage 	: ${startPage2 }<br>
			endPage 	: ${endPage2 }<br>
			currentPage	: ${currentPage2 }<br>
		</div>
	</div>
</body>
</html>