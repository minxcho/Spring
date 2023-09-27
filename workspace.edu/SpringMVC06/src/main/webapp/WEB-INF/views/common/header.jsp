<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- contextPath 값을 내장객체 변수로 저장 -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- Spring Security에서 제공하는 태그 라이브러리 -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Spring Security 에서 제공하는 계정정보 (SecurityContext안에 계정정보 가져오기) -->

<!-- 로그인한 계정정보 -->
<c:set var="mvo" value="${SPRING_SECURITY_CONTEXT.authentication.principal}" />
<!-- 권한 정보 -->
<c:set var="auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>

</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${contextPath}/">스프링</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${contextPath}/">메인</a></li>
					<li><a href="boardMain.do">게시판</a></li>
				</ul>

				<security:authorize access="isAnonymous()">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${contextPath}/loginForm.do"><span class="glyphicon glyphicon-log-in"> 로그인</span></a></li>
						<li><a href="${contextPath}/joinForm.do"><span class="glyphicon glyphicon-user"> 회원가입</span></a></li>
					</ul>
				</security:authorize>
					
				<security:authorize access="isAuthenticated()">
					<ul class="nav navbar-nav navbar-right">
						<li>
							<c:if test="${mvo.member.memProfile ne ''}">
								<img src="${contextPath}/resources/upload/${mvo.member.memProfile}" style="width: 50px; height: 50px;" class="img-circle">
							</c:if>
							<c:if test="${mvo.member.memProfile eq ''}">
								<img src="${contextPath}/resources/images/default.png" style="width: 50px; height: 50px;" class="img-circle">
							</c:if>
							
							<span style="color: gray;">${mvo.member.memName}님 welcome!</span>
							
							[
								<security:authorize access="hasRole('ROLE_USER')">
									U
								</security:authorize>
								<security:authorize access="hasRole('ROLE_MANAGER')">
									M
								</security:authorize>
								<security:authorize access="hasRole('ROLE_ADMIN')">
									A
								</security:authorize>
							
								<!-- 권한 정보 띄우기 -->
								<!-- 회원이 가진 권한의 리스트만큼 반복 돌면서 꺼내기 -->
								<%--<c:forEach items="${mvo.authList}" var="auth">
									<c:choose>
										<c:when test="${auth.auth eq 'ROLE_USER'}">
											U
										</c:when>
										<c:when test="${auth.auth eq 'ROLE_MANAGER'}">
											M
										</c:when>
										<c:when test="${auth.auth eq 'ROLE_ADMIN'}">
											A
										</c:when>
									</c:choose>
								</c:forEach> --%>
							]
							
						</li>
						<li><a href="${contextPath}/updateForm.do"><span class="glyphicon glyphicon-edit"> 회원정보수정</span></a></li>
						<li><a href="${contextPath}/imageForm.do"><span class="glyphicon glyphicon-picture"> 프로필사진등록</span></a></li>
						<li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out"> 로그아웃</span></a></li>
					</ul>
				</security:authorize>

 			</div>
		</div>
	</nav>
	
	
	<script type="text/javascript">
		
		// CSRF 토큰 값 가져오기
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
		
		function logout() {
			$.ajax({
				url : "${contextPath}/logout",
				type : "post",
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				success : function() {
					location.href = "${contextPath}/";
				},
				error : function() {
					alert("error");
				}
			});
		}
	
	
	
	
	</script>
	
	
</body>
</html>