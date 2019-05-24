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
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
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

					<c:set var='count' value='${fn:length(list) }' />
					<c:set var='totalContentCount' value='${pageVo.totalContentCount }' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>[${totalContentCount-(pageVo.pageNo*5)+5-status.index}]</td>
							<td style="text-align:left; padding-left:${20 * vo.depth }px">
								<c:if test="${vo.depth > 0 }">
									<img
										src='${pageContext.servletContext.contextPath }/assets/images/reply.png'>
								</c:if> <a
								href="${pageContext.servletContext.contextPath}/board/view?no=${vo.no}">${vo.title}</a>
							</td>
							<td>${vo.user_name}</td>
							<td>${vo.hit}</td>
							<td>${vo.reg_date}</td>
							<c:if test="${authUser != null }">
								<td><a
									href="${pageContext.servletContext.contextPath}/board/delete?no=${vo.no }"
									class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<fmt:parseNumber value="${((pageVo.pageNo-1)/5) }" var="pageView"
						integerOnly="true" />

					<ul>
						<c:if test="${pageVo.pageNo==1}">
							<li>◀</li>
						</c:if>
						<c:if test="${pageVo.pageNo!=1 }">
							<li><a
								href="${pageContext.servletContext.contextPath}/board?p=${pageVo.pageNo-1}">◀</a></li>
						</c:if>
						<c:forEach var="i" begin="1" end="5" step="1">
							<!-- 현재 페이지 셀렉티드 표시 -->
							<c:choose>
								<c:when test="${(pageView*5)+i > pageVo.totalPageNo}">
										<li>${(pageView*5)+i}</li>
								</c:when>
								<c:when test="${pageVo.pageNo eq ((pageView*5)+i)}">
									<li class="selected"><a
										href="${pageContext.servletContext.contextPath}/board?p=${(pageView*5)+i}">${(pageView*5)+i}</a></li>
								</c:when>
								<c:when test="${pageVo.pageNo ne ((pageView*5)+i)}">
									<li><a
										href="${pageContext.servletContext.contextPath}/board?p=${(pageView*5)+i}">${(pageView*5)+i}</a></li>
								</c:when>	
							</c:choose>
							
						</c:forEach>
						<c:if test="${pageVo.totalPageNo <= pageVo.pageNo}">
							<li>▶</li>
						</c:if>
						<c:if test="${pageVo.totalPageNo > pageVo.pageNo}">
							<li><a
								href="${pageContext.servletContext.contextPath}/board?p=${pageVo.pageNo+1}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath}/board/write"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>

		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>

		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>