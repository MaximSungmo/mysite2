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
<link
	href="${pageContext.servletContext.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
<!-- 제이쿼리 라이브러리 호출  -->
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
	$(function() {
		$('#email').change(function() {
			$('#check-button').show();
			$('#check-image').hide();
		});

		$('#check-button')
				.click(
						function() {
							var email = $('#email').val();
							if (email == '') {
								return;
							}

							/* ajax 통신 */
							/* post 방식은 data칸에 ? 뒤에 항목 적고 get방식은 url에서 처리*/
							$
									.ajax({
										url : "${pageContext.servletContext.contextPath}/user/api/checkemail?email="
												+ email,
										type : "get",
										dataType : "json",
										data : "",
										success : function(response) {
											if (response.result != "success") {
												console.log(response);
												return;
											}

											if (response.data == true) {
												alert('이미 존재하는 Email 입니다.\n 다른 이메일을 사용하세요.');
												$("#email").focus();
												$("#email").val("");
												return;
											}

											$('#check-button').hide();
											$('#check-image').show();

										},
										error : function(xhr, error) {
											console.error("error" + error);
										}
									})

							console.log(email);

						});
	})
</script>

</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form:form 
					modelAttribute="userVo"
					id="join-form" 
					name="joinForm" 
					method="post"
					action="${pageContext.servletContext.contextPath}/user/join">
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value="">
					<!--
					UserVo에 설정한 Valid에서 bindError 발생 시 메세지를 다음과 같이 출력 
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
        					<strong>${errors.getFieldError( 'name' ).defaultMessage }</strong>
 						</c:if>
					</spring:hasBindErrors>
					-->
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p
								style="font-weight: bold; color: red; text-align: left; padding:0; margin:0;">
								<strong style="color: red"> 
									<spring:message
											code="${errors.getFieldError( 'name' ).codes[0] }"
											text="${errors.getFieldError( 'name' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>

					<label class="block-label" for="email">이메일</label> 
						<form:input path="email"/>
						<input type="button" id="check-button" value="체크"> 
						<img style="display:none" id="check-image"	src="${pageContext.servletContext.contextPath}/assets/images/check.png" />
						<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0;">
							<form:errors path="email"/>
						</p>
					
					<!--  
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('email')}">
							<p style="font-weight: bold; color: red; text-align: left; padding=0;">
								<strong style="color: red"> <spring:message
										code="${errors.getFieldError( 'email' ).codes[0] }"
										text="${errors.getFieldError( 'email' ).defaultMessage }" />
								</strong>
							</p>
						</c:if>
					</spring:hasBindErrors>
 					-->
 					
 					
					<label class="block-label">패스워드</label> 
					<form:password path="password"/>

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <form:radiobutton path="gender" value="female" checked="checked"/> 
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>