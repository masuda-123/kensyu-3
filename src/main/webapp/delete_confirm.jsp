<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Delete_confirm</title>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<s:form action="delete_complete">
			<div class="question_area">
				<label>問題:</label>
				<p><s:property value="question"/></p>
				<s:hidden name="questionId" value="%{questionId}"/>
			</div>
			<div class="answers_area">
				<label>答え:</label>
				<div class="answers">
					<s:iterator value="answers">
						<p><s:property /></p>
					</s:iterator>
				</div>
			</div>
			<div class="bottom_btn_area">
				<button type="button" onclick="history.back()">戻る</button>
				<s:submit value = "削除"/>
			</div>
		</s:form>
	</body>
</html>