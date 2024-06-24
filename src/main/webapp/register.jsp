<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>register</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
			<s:form action="postRegister">
			<div class="question_form_area">
				<label for="question">問題:</label>
				<s:textarea name="question"/>
			</div>
			<div class="answer_forms_area">
				<label for="answer">答え:</label>
				<div class="answer_forms">
					<div class="answer_form" id="answerform1">
						<s:textfield name = "answer"/>
					</div>
				</div>
			</div>
			<div class="bottom_btn_area">
				<s:submit value = "確認"/>
			</div>
		</s:form>
	</body>
</html>