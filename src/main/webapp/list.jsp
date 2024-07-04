<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>list</title>
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
		<a class="new_btn" href="<s:url action='register'/>"><button>新規登録</button></a>
		<s:iterator value="queList" status="queSt">
			<div class="list_area">
				<div class="list">
					<div class="list_question_area">
						<label>問題:<s:property value="id"/></label>
						<p><s:property value="question"/></p>
					</div>
					<div class="list_answers_area">
						<s:iterator value="ansList.get(#queSt.index)" status="ansSt">
							<p>答え<s:property value="#ansSt.count"/> <s:property value="answer"/></p>
						</s:iterator>
					</div>
				</div>
				<div class="list_btn_area">
					<!-- パラメータにquestionIdを設定 -->
					<a href="<s:url action='edit'><s:param name="questionId" value="queList.get(#queSt.index).id" /></s:url>">
						<button>編集</button>
					</a>
					<a href="<s:url action='delete_confirm'><s:param name="questionId" value="queList.get(#queSt.index).id" /></s:url>">
						<button>削除</button>
					</a>
				</div>
			</div>
		</s:iterator>
	</body>
</html>
