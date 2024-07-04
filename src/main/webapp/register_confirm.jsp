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
			.btn_area {width: 100%; text-align: right; margin-top: 10px;}
			.bottom_btn_area {text-align: right; margin-top: 10px;}
			label {margin-right: 10px;}
			.question_area label, .answers_area label {width: 7%; text-align: right;}
			.question_area {margin-top: 10px; display: flex;}
			.question_area p {width: 87%; overflow-wrap: break-word;}
			.answers_area {margin-top: 10px; display: flex;}
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
		<!-- エラーメッセージがある場合、エラーメッセージを表示  -->
		<s:set var="errorMessage" value="errorMessage"/>
		<s:if test="%{!#errorMessage.isEmpty()}">
			<p class="error"><s:property escapeHtml="false" value="errorMessage"/></p>
		</s:if>
		<s:form action="register_complete">
			<div class="question_area">
				<label>問題:</label>
				<p><s:property value="inputQuestion"/></p>
				<s:hidden name="inputQuestion" value="%{inputQuestion}"/>
			</div>
			<div class="answers_area">
				<label>答え:</label>
				<div class="answers">
					<s:iterator value="inputAnswers">
						<p><s:property /></p>
					</s:iterator>
					<s:hidden name="inputAnswers" value="%{inputAnswers}"/>
				</div>
			</div>
			<div class="bottom_btn_area">
				<button type="button" onclick="history.back()">戻る</button>
				<!-- エラーメッセージがない場合、登録ボタンを表示  -->
				<s:if test="%{#errorMessage.isEmpty()}">
					<s:submit value = "登録"/>
				</s:if>
			</div>
		</s:form>
	</body>
</html>