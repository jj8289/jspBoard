<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<script>
		alert("해당 게시물이 삭제되었습니다.");
		location.href="/MyBoard/notice_list.do?pageNum2=${sessionScope.pageNum2}";
	</script>
</body>
</html>