<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>list</title>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
		<a class="new_btn" href="<s:url action='getRegister'/>"><button>新規登録</button></a>
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
				<div>
					<a href="<s:url action='edit'/>"><button>編集</button></a>
					<a href="<s:url action='delete'/>"><button>削除</button></a>
				</div>
			</div>
		</s:iterator>
	</body>
</html>
