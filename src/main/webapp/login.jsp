<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>login</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			label {margin-right: 10px;}
			.form {display: flex; justify-content: center; margin-top: 15px;}
			.form label { width: 6%;}
		</style>
	</head>
	<body>
		<s:form action="postLogin">
			<div class="form">
				<label for="userId">ID:</label>
				<s:textfield name = "id"/>
			</div>
			<div class="form">
				<label for="password">pw:</label>
				<s:password name = "password"/>
			</div>
			<div class="form">
				<s:submit class="login_btn" value = "login"/>
			</div>
		</s:form>
	</body>
</html>
