<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>top</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<button onclick="location.href='./Logout'">logout</button>
		</div>
		<div class="menu_area">
		     <s:form action="getList">
		     	<s:submit value="問題と答えを確認・登録する >" />
		     </s:form>
		     <s:form action="getTest">
		     	<s:submit value="テストをする >" />
		     </s:form>
		     <s:form action="getHistory">
		     	<s:submit value="過去の採点結果をみる >" />
		     </s:form>
		</div>
	</body>
</html>