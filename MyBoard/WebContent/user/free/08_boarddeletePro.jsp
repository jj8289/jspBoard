<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${istrue eq 'true' }">
		<script>
			alert("정상적으로 삭제되었습니다.");
			location.href="/MyBoard/free_list.do?pageNum=${sessionScope.pageNum}";
		</script>
	</c:if>
	<c:if test="${istrue eq 'false' }">
		<script>
			alert("비밀번호가 맞지 않습니다.");
			location.href="/MyBoard/free_list.do?pageNum=${sessionScope.pageNum}";
		</script>
	</c:if>
</body>
</html>