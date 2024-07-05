<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
			.new_btn button {position: absolute; inset: 0;}
			table {border-collapse: collapse;}
			td, th {padding: 7px; width: 210px; border: solid 1px;}
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
				 <s:iterator value="%{hisList}" status="hisSt">
					 <tr>
					 	<!-- ユーザー名を表示  -->
					 	<td><s:property value="userName"/></td>
					 	<td><s:property value="point"/>点</td>
					 	<td><s:property value="dateTime[#hisSt.index]"/></td>
					 </tr>
				</s:iterator>
			</table>
		</s:else>
	</body>
</html>