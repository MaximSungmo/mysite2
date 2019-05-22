<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					
					<c:set var='count' value='${fn:length(list) }'/>
					<c:forEach items ='${list }' var='vo' varStatus='status'	>
					<tr>
						<td>[${count - status.index}]</td>
						<td style="text-align:left; padding-left:${20 * vo.depth }px">
							<c:if test="${vo.depth > 0 }">	
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'>
							</c:if>
							<a href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}">${vo.title}</a></td>
						<td>${vo.user_name}</td>
						<td>${vo.hit}</td>
						<td>${vo.reg_date}</td>
						<c:if test="${authUser != null }">
							<td><a href="${pageContext.servletContext.contextPath}/board/delete?no=${vo.no }" class="del">삭제</a></td>
						</c:if>
					</tr>
					</c:forEach>					
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						
						
						<li class="selected"><a href="/board/list?p=1">1</a></li>
						<li><a href="/board/list?p=2">2</a></li>
						<li><a href="/board/list?p=3">3</a></li>
						<li><a href="/board/list?p=4">4</a></li>
						<li><a href="/board/list?p=5">5</a></li>
						
						<li><a href="/board/list?p=6">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->				
				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="main"/>
		</c:import>
		
		<c:import url='/WEB-INF/views/includes/footer.jsp'/>
	</div>
</body>
</html>