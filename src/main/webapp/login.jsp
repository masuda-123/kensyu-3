<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>login</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
		<!-- formタグで入力されたデータを./Loginにpostで送信する  -->
		<form action="./Login" method="post">
			<div class="form">
				<label for="userId">ID:</label>
				<!-- userIdを入力する欄  -->
				<input type="number" id="userId" name="userId">
			</div>
			<div class="form">
				<label for="password">pw:</label>
				<!-- パスワードを入力する欄  -->
				<input type="password" id="password" name="password">
			</div>
			<div class="form">
				<!-- フォームデータを送信するボタン -->
		    	<input type="submit" value="login" class="login_btn">
		    </div>
		</form>
	</body>
</html>
