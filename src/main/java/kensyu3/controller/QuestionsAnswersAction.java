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
	
	//register画面を表示
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
	
	//register_confirm画面を表示
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
			//register_questionメソッドを呼び出して、問題を登録し、questionIdを取得
			int questionId = queDao.register(inputQuestion);
			//register_answersメソッドを呼び出して、答えを登録
			ansDao.register(questionId, inputAnswers);
			//list画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//delete_confirm画面を表示
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
	
	public ArrayList<QuestionsBean> getQueList() throws Exception {
		if (queList.isEmpty()) {
			QuestionsDao queDao = new QuestionsDao();
			//問題を全件取得
			queList = queDao.findAll();
		}
		return queList;
	}
	
	public ArrayList<ArrayList<AnswersBean>> getAnsList() throws Exception{
		//答えが空だった場合
		if (ansList.isEmpty()) {
			AnswersDao ansDao = new AnswersDao();
			//答えを全件取得
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
}
