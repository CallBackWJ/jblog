<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/views/includes/blog_header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/views/includes/blog_admin_menu.jsp">
					<c:param name="menu" value="basic"></c:param>
				</c:import>
				<form action="${pageContext.request.contextPath}/${authuser.id}/admin/basic" method="post" enctype="multipart/form-data">
					<table class="admin-config">
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title" value="${blog.title}"></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
							<td><img src="${pageContext.request.contextPath}/${blog.logo }" 
							style="width:100px" onerror="this.src='${pageContext.request.contextPath }/assets/images/spring-logo.jpg'"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" name="file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/views/includes/blog_footer.jsp" />
	</div>
</body>
</html>