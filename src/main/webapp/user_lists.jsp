<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user_lists</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			p {margin: 0;}
			.btn_area {text-align: right; margin: 10px 20px;}
			.user_id_area {width: 10%; text-align: left;}
			.user_auth_area {width: 10%; text-align: left;}
			.user_name_area {width: 66%; text-align: left;}
			.user_new_btn {text-decoration: none; position: relative; width: 10%; padding: 12px 3px; display: block; margin: 10px 88.7%;}
			.user_new_btn button {position: absolute; inset: 0;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<a class="user_new_btn" href="<s:url action='user_register'/>"><button>新規登録</button></a>
		<table class="user_table">
			<tr>
				<th class="user_id_area">ID</th>
				<th class="user_auth_area">権限</th>
				<th class="user_name_area">ユーザー名</th>
			</tr>
			<!-- ユーザーの数だけ処理を繰り返す  -->
			<s:iterator value="userList" status="st">
				<tr>
					<td  class="user_id_area"><s:property value="id"/></td>
					<s:set var="flag" value="userList.get(#st.index).getAdminFlag()"/>
					<s:if test="%{#flag == 0}">
						<td  class="user_auth_area">一般</td>
					</s:if>
					<s:if test="%{#flag == 1}">
						<td  class="user_auth_area">管理者</td>
					</s:if>
					<td  class="user_name_area"><s:property value="name"/></td>
					<td>
						<a href="<s:url action='edit'><s:param name="id" value="userList.get(#st.index).getId()" /></s:url>">
							<button>編集</button>
						</a>
					</td>
					<td>
						<a href="<s:url action='delete_confirm'><s:param name="id" value="userList.get(#st.index).getId()" /></s:url>">
							<button>削除</button>
						</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>