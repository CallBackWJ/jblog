<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	var FormValidator = {
		$imageCheck : null,
		$buttonCheckEmail : null,
		$inputTextId : null,

		init : function() {
			this.$imageCheck = $("#img-checkemail");
			this.$buttonCheckEmail = $("#btn-checkemail");
			this.$inputTextId = $("#blog-id");

			this.$inputTextId
					.change(this.onEmailInputTextChanged.bind(this));
			this.$buttonCheckEmail.click(this.onCheckEmailButtonClicked
					.bind(this));
			$("#join-form").submit(this.onFormSubmit.bind(this));
		},
		onEmailInputTextChanged : function() {
			this.$imageCheck.hide();
			this.$buttonCheckEmail.show();
		},
		onCheckEmailButtonClicked : function(event) {
			console.log(event.currentTarget);

			var id = this.$inputTextId.val();
			if (id === "") {
				return;
			}

			//ajax 통신
			$
					.ajax({
						url : "${pageContext.request.contextPath }/user/checkid?id="
								+ id,
						type : "get",
						dataType : "json",
						data : "",
						success : this.onCheckEmailAjaxSuccess.bind(this),
						error : this.onCheckEmailAjaxError.bind(this)
					});
		},
		onCheckEmailAjaxSuccess : function(response) {
			if (response.result == "fail") {
				console.error(response.message);
				return;
			}

			if (response.data == true) {
				alert("이미 존재하는 ID 입니다. 다른 ID를 사용해 주세요.");
				// email 입력 창 비우고 포커싱
				this.$inputTextId.val("").focus();
			} else {
				this.$imageCheck.show();
				this.$buttonCheckEmail.hide();
			}
		},
		onCheckEmailAjaxError : function(jqXHR, status, error) {
			console.error(status + " : " + error);
		},
		onFormSubmit : function() {
			//1. 이름
			var $inputTextName = $("#name");
			if ($inputTextName.val() === "") {
				alert("이름은 필수 항목입니다.");
				$inputTextName.focus();
				return false;
			}
			var $email = $("#blog-id");
			if (this.$inputTextId.val() === "") {
				alert("ID는 필수 항목입니다.");
				this.$inputTextId.focus();
				return false;
			}

			//3. 이메일 중복 체크 여부
			if (this.$imageCheck.is(":visible") === false) {
				alert("ID 중복 체크를 해 주세요.");
				return false;
			}

			//4. 비밀번호
			var $inputPassword = $("#password");
			if ($inputPassword.val() === "") {
				alert("비밀번호는 필수 항목입니다.");
				$inputPassword.focus();
				return false;
			}

			//5. 약관동의
			var $inputCheckBoxAgree = $("#agree-prov");
			if ($inputCheckBoxAgree.is(":checked") === false) {
				alert("가입 약관에 동의 하셔야 합니다.");
				$inputCheckBoxAgree.focus();
				return false;
			}
			// valid!
			return true;
		}
	}
	$(function() {
		FormValidator.init();
	});
</script>
</head>
<body>
	<div class="center-content">
	<c:import url="/views/includes/header.jsp" />
		<form class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
