<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert("업데이트 완료!");
		location.href="/MyBoard/faq_list.do?pageNum3=${sessionScope.pageNum3 }";
	</script>
</body>
</html>