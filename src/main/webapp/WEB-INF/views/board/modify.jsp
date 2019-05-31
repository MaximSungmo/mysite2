<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath}/board/modify?no=${no}">
					<input type="hidden" name="no" value="${no }">					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value="${title }"></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content"
									style="width: 100%; border: 1; overflow: visible; text-overflow: ellipsis;" rows=30>							
									${content}
								</textarea>
							</td>
						</tr>
					</table>
					
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board?p=1">취소</a> 
						<input type="submit" value="수정">
					</div>
				</form>
			</div>
		</div>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>

		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>