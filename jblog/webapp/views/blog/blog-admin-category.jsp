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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

function categoryDelete(no){

	$.ajax({
		url:"/jblog/1111/admin/category/api/delete?no="+no,
		type:"get",
		dataType:"json",
		data:"",
		success: function(response){
			if(response.result == "fail"){
				console.warn(response.result);
				return;
			}
			if(response.data==false){
				alert("포스트가 존재하는 카테고리는 삭제할수 없습니다.");
				return;
			}
			$("[data-no='"+no+"']").remove();
		},
		error: function(xhr, status, e){
		
			console.error(status + ":" + e);
			
			return;
		}
	})
	
}


var render = function(vo,mode){
	// 현업에 가면 이렇게 안한다. -> js template library 를 사용
	// ex) ejs, underscore, mustache
	var htmls = "<tr data-no='"+vo.no+"'><td>"+vo.no+"</td><td>"+ vo.name +"</td>"
	+"<td>"+ vo.post_count +"</td>"
	+"<td>"+ vo.description +"</td>"
	+"<td><a href='javascript:void(0);' onclick='categoryDelete("+vo.no+");'>"
	+"<img src='/jblog/assets/images/delete.jpg'>"
	+"</a></td></tr>";
	
	if( mode == true ) {
		$("#category-list").prepend(htmls);		
	} else {
		$("#category-list").append(htmls);		
	}	
	
};
$(function(){
	
	$("#add-form").submit(function(event){
		event.preventDefault();
		
		var name=$("#name").val();
		var description=$("#description").val();
		var user_no=$("#user_no").val();
		var user_id=$("#user_id").val();
		if(name==""){
			alert("카테고리명은 필수 입력 항목입니다.");
			return;
		}else if(description==""){
			alert("설명은 필수 입력 항목입니다.");
			return;
		}else if(user_no==""){
			alert("올치않은 접근입니다.");
			return;
		}
		
		$.ajax({
			url:"/jblog/"+user_id+"/admin/category/api?name="+name+"&description="+description+"&user_no="+user_no,
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
				$("#name").val("");
				$("#description").val("");
			},
			error: function(xhr, status, e){
			
				console.error(status + ":" + e);
				
				return;
			}
		})
		
	})

	
	
	
	
});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/views/includes/blog_header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/views/includes/blog_admin_menu.jsp">
					<c:param name="menu" value="category"></c:param>
				</c:import>
				<table class="admin-cat" id="category-list">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
	
					<c:forEach items="${category}" var="vo" varStatus="status">
					<tr data-no='${vo.no}'>
						<td>${vo.no}</td>
						<td>${vo.name }</td>
						<td>${vo.post_count }</td>
						<td>${vo.description }</td>
						<td><a href="javascript:void(0);" onclick="categoryDelete(${vo.no});">
						<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						</a></td>
					</tr>
					</c:forEach>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form id="add-form" action="" method="post" >
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="description" name="description"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;
						<input type="hidden" id="user_no" name="user_no" value="${authuser.no }" >
						<input type="hidden" id="user_id" value="${authuser.id }" >
						</td>
						<td><input type="submit" value="카테고리 추가"></td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		<c:import url="/views/includes/blog_footer.jsp" />
	</div>
</body>
</html>