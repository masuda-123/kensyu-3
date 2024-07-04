<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>history</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			p {margin: 0;}
			.btn_area {width: 100%; text-align: right; margin-top: 10px;}
			label {margin-right: 10px;}
			.new_btn {text-decoration: none; position: relative; width: 10%; padding: 12px 3px; display: block; margin: 10px auto;}
			.new_btn button {position: absolute; inset: 0;}
			.list_area {margin-bottom: 10px; display: flex;}
			.list {width: 84.5%;}
			.list_question_area {display: flex; margin-bottom: 5px;}
			.list_question_area p {width: 80%; overflow: hidden; text-overflow: ellipsis;white-space: nowrap;}
			.list_answers_area {margin-left: 20px;}
			.list_answers_area p {overflow: hidden; text-overflow: ellipsis; white-space: nowrap;}
			.list_btn_area {display: flex; width: 14%; justify-content: space-between;}
			.list_btn_area a {text-decoration: none; position: relative; display: block;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<h2>履歴</h2>
		<!-- 履歴が登録されていない場合  -->
		<s:set var="hisList" value="hisList"/>
		<s:if test="%{#hisList.isEmpty()}">
			<p>履歴が登録されていません</p>
		</s:if>
		<!-- 履歴が登録されている場合  -->
		<s:else>
			<table>
				 <tr>
				 	<th>氏名</th>
				 	<th>得点</th>
				 	<th>採点時間</th>
				 </tr>
				 <s:iterator value="%{hisList}">
					 <tr>
					 	<!-- ユーザー名を表示  -->
					 	<td><s:property value="userName"/></td>
					 	<td><s:property value="point"/>点</td>
					 	<td><s:property value="sdf.format(createdAt())"/></td>
					 </tr>
				</s:iterator>
			</table>
		<s:else>
	</body>
</html>