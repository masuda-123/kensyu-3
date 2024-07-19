<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user_register_confirm</title>
		<style>
			body {width: 650px; margin: 0 auto;}
			label {margin-right: 10px;}
			.btn_area{text-align: right; margin: 10px 20px;}
			.bottom_btn_area {text-align: right; margin: 10px 20px;}
			.user_form_area {margin-top: 20px;}
			.user_form {display: flex; margin-bottom: 10px;}
			.user_form label {width: 15%; display: block; text-align: right;}
			.user_form_info li{margin-left: 20%;}
			.user_form input[type="text"], .user_form input[type="password"] {width: 80%;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>	
	</body>
</html>