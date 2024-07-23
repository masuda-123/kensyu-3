<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user_delete_confirm</title>
		<style>
			body {width: 650px; margin: 0 auto;}
			label {margin-right: 10px;}
			.btn_area{text-align: right; margin: 10px 20px;}
			.bottom_btn_area {text-align: right; margin: 10px 20px;}
			.user_form_area {margin-top: 20px;}
			.user_form {display: flex; margin-bottom: 10px;}
			.user_form label {width: 15%; display: block; text-align: right;}
			p {margin: 0;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<s:form class="user_form_area" action="user_delete_complete">
			<div  class="user_form">
				<label>ID: </label>
				<p><s:property value="%{user.getId()}"/></p>
				<s:hidden id="id" name="id" value="%{user.getId()}"/>
			</div>
			<div class="user_form">
				<label>ユーザー名: </label>
				<p><s:property value="%{user.getName()}"/></p>
			</div>
			<div class="user_form">
				<label>管理者権限: </label>
				<!-- 管理者の場合 -->
				<s:if test="%{user.getAdminFlag() == 1}">
					<p>あり</p>
				</s:if>
				<!-- 管理者ではない場合 -->
				<s:if test="%{user.getAdminFlag() == 0}">
					<p>なし</p>
				</s:if>
			</div>
			<div class="bottom_btn_area">
				<button type="button" onclick="history.back()">戻る</button>
				<s:submit value = "削除"/>
			</div>
		</s:form>
	</body>
</html>