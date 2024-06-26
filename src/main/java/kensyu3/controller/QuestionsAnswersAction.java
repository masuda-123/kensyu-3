package kensyu3.controller;

import java.util.ArrayList;

import kensyu3.model.AnswersBean;
import kensyu3.model.AnswersDao;
import kensyu3.model.QuestionsBean;
import kensyu3.model.QuestionsDao;
import kensyu3.other.Validation;

public class QuestionsAnswersAction extends Base{
	//jspファイルから受け取る値の定義
	private String question;
	private String[] answers;
	private ArrayList<QuestionsBean> queList = new ArrayList<QuestionsBean>();
	private ArrayList<ArrayList<AnswersBean>> ansList = new ArrayList<>();
	private String errorMessage;
	
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
	public String register_confirm()  throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			Validation val = new Validation();
			//問題や答えにエラーがないか確認し、エラーメッセージに値を格納
			errorMessage = val.validate(question, answers);
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
			int questionId = queDao.register(question);
			//register_answersメソッドを呼び出して、答えを登録
			ansDao.register(questionId, answers);
			//list画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	public ArrayList<QuestionsBean> getQueList() throws Exception {
	    QuestionsDao queDao = new QuestionsDao();
	    //問題を全件取得
	    queList = queDao.findAll();
		return queList;
	}
	
	public ArrayList<ArrayList<AnswersBean>> getAnsList() throws Exception{
	      AnswersDao ansDao = new AnswersDao();
	      //答えを全件取得
	      for(QuestionsBean que: queList) {
	        ArrayList<AnswersBean> ans = ansDao.findByQuestionId(que.getId());
	        ansList.add(ans);
	      }
		return ansList;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public String[] getAnswers() {
		return answers;
	}
	
	public void setAnswers(String answer) {
		this.answers = answer.split(", ");
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
