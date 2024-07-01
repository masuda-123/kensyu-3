package kensyu3.controller;

import java.util.ArrayList;

import kensyu3.model.AnswersBean;
import kensyu3.model.AnswersDao;
import kensyu3.model.QuestionsBean;
import kensyu3.model.QuestionsDao;
import kensyu3.other.Validation;

public class QuestionsAnswersAction extends Base{
	//jspファイルから受け取る値の定義
	private String inputQuestion;
	private String[] inputAnswers;
	private ArrayList<QuestionsBean> queList = new ArrayList<QuestionsBean>();
	private ArrayList<ArrayList<AnswersBean>> ansList = new ArrayList<>();
	private String errorMessage;
	private int questionId;
	private String question;
	private String[] answers;
	
	//list画面を表示
	public String list() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//list画面に遷移
			return "success" ;
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//登録画面を表示
	public String register() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//register画面に遷移
			return "success" ;
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//登録確認画面を表示
	public String register_confirm() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			Validation val = new Validation();
			//問題や答えにエラーがないか確認し、エラーメッセージに値を格納
			errorMessage = val.validate(inputQuestion, inputAnswers);
			//register_confirm画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//登録処理
	public String register_complete() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			QuestionsDao queDao = new QuestionsDao();
			AnswersDao ansDao = new AnswersDao(); 
			//問題を登録
			int questionId = queDao.register(inputQuestion);
			//答えを登録
			ansDao.register(questionId, inputAnswers);
			//list画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//削除確認画面を表示
	public String delete_confirm() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//delete_confirm画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//削除処理
	public String delete_complete() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			QuestionsDao queDao = new QuestionsDao();
			AnswersDao ansDao = new AnswersDao();
			//問題と答えを削除
			queDao.delete(questionId);
			ansDao.delete(questionId);
			//list画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//編集画面を表示
	public String edit(){
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//edit画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//編集確認画面を表示
	public String edit_confirm(){
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			Validation val = new Validation();
			//問題や答えにエラーがないか確認し、エラーメッセージに値を格納
			errorMessage = val.validate(inputQuestion, inputAnswers);
			//edit画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	public ArrayList<QuestionsBean> getQueList() throws Exception {
		//queListが空だった場合
		if (queList.isEmpty()) {
			QuestionsDao queDao = new QuestionsDao();
			//問題データを全件取得
			queList = queDao.findAll();
		}
		return queList;
	}
	
	public ArrayList<ArrayList<AnswersBean>> getAnsList() throws Exception{
		//ansListが空だった場合
		if (ansList.isEmpty()) {
			AnswersDao ansDao = new AnswersDao();
			//答えデータを全件取得
			for(QuestionsBean que: queList) {
				ArrayList<AnswersBean> ans = ansDao.findByQuestionId(que.getId());
				ansList.add(ans);
			}
		}
		return ansList;
	}
	
	public String getInputQuestion() {
		return inputQuestion;
	}
	
	public void setInputQuestion(String inputQuestion) {
		this.inputQuestion = inputQuestion;
	}
	
	
	public String[] getInputAnswers() {
		return inputAnswers;
	}
	
	public void setInputAnswers(String inputAnswers) {
		this.inputAnswers = inputAnswers.split(", ");
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public int getQuestionId() {
		return questionId;
	}
	
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getQuestion() throws Exception{
		//questionがnullだった場合
		if (question == null) {
			QuestionsDao queDao = new QuestionsDao();
			//questionIdから問題文データを取得
			QuestionsBean que = queDao.findById(questionId);
			//問題データから問題文を取得
			question = que.getQuestion();
		}
		return question;
	}
	
	public String[] getAnswers() throws Exception {
		//answersがnullだった場合
		if (answers == null) {
			AnswersDao ansDao = new AnswersDao();
			//questionIdから答えのデータを取得
			ArrayList<AnswersBean> ans = ansDao.findByQuestionId(questionId);
			//答えを一時的に入れる配列
			String[] tempAnswers = new String[ans.size()];
			for(int i = 0; i < ans.size(); i++) {
				//答えデータから答えを取得し、配列に格納
				tempAnswers[i] = ans.get(i).getAnswer();
			}
			//aanswersに配列になっている答えを入れ直す
			answers = tempAnswers;
		}
		return answers;
	}
}
