<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>result</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			.btn_area, .bottom_menu_area {width: 100%; text-align: right; margin-top: 10px;}
			.menu_area a {text-decoration: none; position: relative; width: 40%; padding: 15px; display: block; margin: 10px auto;}
			.menu_area button {position: absolute; inset: 0;}
			.bottom_menu_area a {color: red; text-decoration: none;}
			h2 {text-align: center;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<s:set var="queCnt" value="questionsId.length"/>
		<h2>テスト結果</h2>
		<p><s:property value="userName"/>さん</p>
		<p>${queCnt}問中<s:property value="correctCnt"/>問正解です。</p>
		<p><s:property value="point"/>点でした。</p>
		<div class="bottom_menu_area">
			<p><s:property value="currentDateTime"/></p>
			<a href="<s:url action='history'/>">採点結果履歴へ</a>
		</div>
	</body>
</html>