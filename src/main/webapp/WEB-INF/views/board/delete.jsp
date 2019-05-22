<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/includes/header.jsp'/>
		<div id="content">
			<div id="board" class="delete-form">
				<form method="post" action="${pageContext.servletContext.contextPath }/board/delete">
					<input type='hidden' name="no" value="${no }">
					<label>비밀번호</label>
					<input type="password" name="password">
					<input type="hidden" name="user_password" value="${authUser.password }">
					<input type="submit" value="확인">
				</form>
				<a href="${pageContext.servletContext.contextPath}/board">게시글 리스트</a>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'/>
		<c:import url='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>