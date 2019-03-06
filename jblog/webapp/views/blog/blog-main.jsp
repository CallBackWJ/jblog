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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
.blog-content {
	height: 300px;
	overflow: scroll;
	border:2px solid #aaa;
	border-top:0;
	border-bottom:0;
	border-radius: 2px;
}

.container {
	overflow: scroll;
	height: 100px;
	border: 2px solid #aaa;
	border-radius: 2px;
}

input[type="text"] {
	width: 100%;
	border: 2px solid #aaa;
	border-radius: 4px;
	margin: 8px 0;
	outline: none;
	padding: 8px;
	box-sizing: border-box;
	transition: 0.3s;
}

input[type="text"]:focus {
	border-color: dodgerBlue;
	box-shadow: 0 0 8px 0 dodgerBlue;
}

.tip {
	width: 0px;
	height: 0px;
	position: absolute;
	background: transparent;
	border: 10px solid #ccc;
}

.tip-left {
	top: 10px;
	left: -25px;
	border-top-color: transparent;
	border-left-color: transparent;
	border-bottom-color: transparent;
}

.dialogbox .body {
	position: relative;
	max-width: 300px;
	height: auto;
	margin: 20px 10px;
	padding: 5px;
	background-color: #DADADA;
	border-radius: 3px;
	border: 5px solid #ccc;
}

.body .message {
	min-height: 30px;
	border-radius: 3px;
	font-family: Arial;
	font-size: 14px;
	line-height: 1.5;
	color: #797979;
}
</style>
<script type="text/javascript">
var render = function(vo,mode){
	var htmls = "<div class='dialogbox'><div class='body'><span class='tip tip-left'></span><div class='message'><span>"
	+vo.content+"</span></div></div></div>";
	
	if( mode == true ) {
		$("#comment-list").prepend(htmls);		
	} else {
		$("#comment-list").append(htmls);		
	}	
	
};
	function Enter_Check(no) {
		// 엔터키의 코드는 13입니다.
		if (event.keyCode == 13) {
			var comment=$("#comment").val();
			if(comment==""){
				alert("comment를 작성해주세요!!");
				return;
			}
			$.ajax({
				url:"/jblog/1111/admin/comment/api?post_no="+no+"&content="+comment,
				type:"get",
				dataType:"json",
				data:"",
				success: function(response){
					if(response.result == "fail"){
						console.warn(response.result);
						return;
					}
					render(response.data,false);
					console.log(response.result);
					$("#comment").val("");
				},
				error: function(xhr, status, e){
				
					console.error(status + ":" + e);
					
					return;
				}
			})
		}
	}
</script>
</head>
<body>
	<div id="container">
		<c:import url="/views/includes/blog_header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>${post.content}
					<p>
				</div>

				<div class="container" id="comment-list">
					<c:forEach items="${comment}" var="vo">
						<div class="dialogbox">
							<div class="body">
								<span class="tip tip-left"></span>
								<div class="message">
									<span>${vo.content }</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<input type="text" placeholder="Comment" id="comment"
					onkeydown="JavaScript:Enter_Check(${post.no});">


				<ul class="blog-list">
					<c:forEach items="${postlist}" var="vo" varStatus="status">
						<li><a
							href="${pageContext.request.contextPath}/${id}/${vo.category_no}/${vo.no}">${vo.title }</a>
							<span>${vo.reg_date }</span></li>
						<hr>

					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="sidebar">
			<div id="extra">
				<div class="blog-logo">
					<img src="${pageContext.request.contextPath}/${blog.logo }"
						style="width: 100px"
						onerror="this.src='${pageContext.request.contextPath }/assets/images/spring-logo.jpg'">
				</div>
			</div>

			<div id="navigation">
				<h2>카테고리</h2>
				<ul>
					<c:forEach items="${category}" var="vo" varStatus="status">
						<li><a
							href="${pageContext.request.contextPath}/${id}/${vo.no}">${vo.name }(${vo.post_count})</a></li>
						<hr>
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/views/includes/blog_footer.jsp" />
	</div>
</body>
</html>