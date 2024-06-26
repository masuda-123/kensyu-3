<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>register_confirm</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			p {margin: 0;}
			.btn_area, .bottom_menu_area {width: 100%; text-align: right;}
			.bottom_btn_area {text-align: right; margin-top: 10px;}
			label {margin-right: 10px;}
			.question_area label, .answers_area label {width: 7%; text-align: right;}
			.edit_question_area, .question_area, .test_question_area {margin-top: 10px; display: flex;}
			.edit_question_area p, .question_area p {width: 87%; overflow-wrap: break-word;}
			.edit_answers_area, .answers_area, .test_answers_area {margin-top: 10px; display: flex;}
			.answers {width: 87%;}
			.answers p {overflow-wrap: break-word;}
			.error {padding-top:10px;color: red;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<s:form action="register_complete">
			<div class="question_area">
				<label>問題:</label>
				<p><s:property value="question"/></p>
				<s:hidden name="question" value="%{question}"/>
			</div>
			<div class="answers_area">
				<label>答え:</label>
				<div class="answers">
					<s:iterator value="answers">
						<p><s:property /></p>
					</s:iterator>
					<s:hidden name="answers" value="%{answers}"/>
				</div>
			</div>
			<div class="bottom_btn_area">
				<button type="button" onclick="history.back()">戻る</button>
				<s:submit value = "登録"/>
			</div>
		</s:form>
	</body>
</html>