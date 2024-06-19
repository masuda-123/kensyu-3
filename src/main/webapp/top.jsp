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
			<a href="<s:url action='getLogout'/>"><button>logout</button></a>
		</div>
		<div class="menu_area">
			<a href="<s:url action='getList'/>"><button>問題と答えを確認・登録する ></button></a>
			<a href="<s:url action='getTest'/>"><button>テストをする > </button></a>
			<a href="<s:url action='getHistory'/>"><button>過去の採点結果をみる ></button></a>
		</div>
	</body>
</html>