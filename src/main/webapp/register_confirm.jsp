<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>register_confirm</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
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
				<s:submit value = "登録"/>
			</div>
		</s:form>
	</body>
</html>