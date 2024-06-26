<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Struts2のタグライブラリを使用可能にする -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>register</title>
		<style type="text/css">
			body {width: 650px; margin: 0 auto;}
			p {margin: 0;}
			.btn_area, .bottom_menu_area {width: 100%; text-align: right;}
			.bottom_btn_area {text-align: right; margin-top: 10px;}
			label {margin-right: 10px;}
			.question_form_area {margin-top: 20px; display: flex;}
			.question_form_area textarea {width: 76.7%; height: 100px;}
			.question_form_area label {width: 7%;}
			.answer_forms_area {margin-top: 20px; display: flex;}
			.answer_forms_area label {width: 7%;;}
			.answer_forms {width: 90%;}
			.answer_form {display: flex; margin-bottom: 10px; height: 26px;}
			.answer_form input {display: block; width: 85%;}
			.answer_form button {display: block; margin-left: 10px;}
		</style>
		<script type="text/javascript">
			var i = 1;
			function addForm() {
				//答えのフォームとボタンを含むdivタグを作成
				var answer_form = document.createElement('div');
				answer_form.className = "answer_form"
				answer_form.id = "answer_form" + ++i;
				//答えの入力フォームを作成
				var input = document.createElement('input');
				input.type = 'text';
				input.name = 'answers';
				//削除ボタンを作成
				var delete_btn = document.createElement('button');
				delete_btn.type = 'button';
				delete_btn.setAttribute('onclick', `deleteForm(${answer_form.id})`);
				var text = document.createTextNode('削除');
				//答えのフォームリストを含むタグを取得
				var parent = document.querySelector('.answer_forms');
				//フォームリストの中にdivタグを追加
				parent.appendChild(answer_form);
				//divタグの中に答えの入力フォームを追加
				answer_form.appendChild(input);
				//divタグの中に削除ボタンを追加
				answer_form.appendChild(delete_btn);
				//削除ボタンにテキストを追加
				delete_btn.appendChild(text);
			};
		</script>
	</head>
	<body>
		<div class="btn_area">
			<a href="<s:url action='top'/>"><button>top</button></a>
			<a href="<s:url action='logout'/>"><button>logout</button></a>
		</div>
			<s:form action="register_confirm">
			<div class="question_form_area">
				<label for="question">問題:</label>
				<s:textarea name="question"/>
			</div>
			<div class="answer_forms_area">
				<label for="answer">答え:</label>
				<div class="answer_forms">
					<div class="answer_form" id="answer_form1">
						<s:textfield name = "answers"/>
					</div>
				</div>
			</div>
			<div class="bottom_btn_area">
				<s:submit value = "確認"/>
				<button type="button" onclick="addForm()">追加</button>
			</div>
		</s:form>
	</body>
</html>