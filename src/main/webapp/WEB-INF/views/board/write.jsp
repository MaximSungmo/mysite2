<!-- JSTL 사용을 위한 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 스프링에서 제공하는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- 폼을 만들어주는 태그 라이브러리 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board">
				<form:form
					modelAttribute="boardVo"
					method="post" 
					action="${pageContext.servletContext.contextPath}/board/reply">			

					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
							<td><input type="hidden" name="user_no" value="${authUser.no }"></td>
							<td><input type="hidden" name="group_no" value="${boardVo.group_no }"></td>
							<td><input type="hidden" name="order_no" value="${boardVo.order_no }"></td>
							<td><input type="hidden" name="depth" value="${boardVo.depth }"></td>
							<td><input type="hidden" name="hit" value="0"></td>
							
						</tr>
						
						<tr>
							<td class="label">제목</td>
							<td><form:input path="title"/></td>
						</tr>
						
						<tr>
							<td class="label">내용</td>
							<td><form:textarea path="contents" rows="30" cols="30"/>
							</td>
						</tr>
						
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board">취소</a>
						<input type="submit" value="등록">
					</div>
				</form:form>				
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>