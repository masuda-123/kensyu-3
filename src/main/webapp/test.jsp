<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>test</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			p {margin: 0;}
			label {margin-right: 10px;}
			.test_question_area p {width: 91%; overflow-wrap: break-word;}
			.test_question_area label, .test_answers_area label {width: 7%; text-align: center;}
			.test_question_area {margin-top: 10px; display: flex;}
			.test_answers_area {margin-top: 10px; display: flex;}
			.test_answer_form {width: 100%;}
			.test_answer_form input {width: 95.5%; height: 20px;}
			.btn_area {text-align: right; margin: 10px 20px;}
			.bottom_btn_area {text-align: right; margin: 10px 20px;}
		</style>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<!-- 問題が登録されていない場合  -->
		<s:set var="queList" value="queList"/>
		<s:if test="%{#queList.isEmpty()}">
			<p>問題が登録されていません</p>
		</s:if>
		<!-- 問題が登録されている場合  -->
		<s:else>
			<s:form action="result">
				<s:iterator value="%{randomQueList}" status="queSt">
					<div class="test_question_area">
						<label>${queSt.count}</label>
						<p><s:property value="question"/></p>
						<s:hidden name="questionsId" value="%{randomQueList.get(#queSt.index).getId()}"/>
					</div>
					<div class="test_answers_area">
						<label for="answer">回答</label>
						<div class="test_answer_form">
							<s:textfield name="inputAnswers"/>
						</div>
					</div>
				</s:iterator>
				<div class="bottom_btn_area">
					<s:submit value = "採点"/>
				</div>
			</s:form>
		</s:else>
	</body>
</html>