<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>top</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			.btn_area {text-align: right; margin: 10px 20px;}
			.menu_area a {text-decoration: none; position: relative; width: 40%; padding: 15px; display: block; margin: 10px auto;}
			.menu_area button {position: absolute; inset: 0;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<div class="menu_area">
			<s:set var="auth" value="auth"/>
			<s:if test="%{#auth == 1}">
				<a href="<s:url action='list'/>"><button>問題と答えを確認・登録する ></button></a>
			</s:if>
			<a href="<s:url action='test'/>"><button>テストをする > </button></a>
			<a href="<s:url action='history'/>"><button>過去の採点結果をみる ></button></a>
			<s:set var="auth" value="auth"/>
			<s:if test="%{#auth == 1}">
				<a href="<s:url action='user_lists'/>"><button>ユーザーを追加・編集する ></button></a>
			</s:if>
		</div>
	</body>
</html>