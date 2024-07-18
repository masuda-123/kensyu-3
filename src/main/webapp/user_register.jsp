<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user_lists</title>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<s:form class="user_form_area" action="user_register_confirm">
			<div  class="user_form">
				<label>ユーザー名: </label>
				<s:textfield id="userName" name="userName"/>
			</div>
			<div  class="user_form">
				<label>PW: </label>
				<s:password id="password" name="password"/>
			</div>
			<div  class="user_form">
				<label>PW確認: </label>
				<s:password id="passwordConfirm" name="passwordConfirm"/>
			</div>
			<div  class="user_form">
				<label>管理者権限: </label>
				<s:set var="auth" value="auth"/>
				<s:checkbox id="auth" name="auth" fieldValue="1" value="false"/>
			</div>
			<div class="user_form_info">
				<li>ユーザー名は半角英数字で入力してください</li>
				<li>PWは半角英数字で8文字以上で入力してください</li>
			</div>
			<div class="bottom_btn_area">
				<button type="button" onclick="history.back()">戻る</button>
				<s:submit value = "確認"/>
			</div>
		</s:form>
	</body>
</html>