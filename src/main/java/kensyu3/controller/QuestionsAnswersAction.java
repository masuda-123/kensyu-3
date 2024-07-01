package kensyu3.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

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
	private int[] answersId;
	
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
	
	//編集処理
	public String edit_complete() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			QuestionsDao queDao = new QuestionsDao();
			AnswersDao ansDao = new AnswersDao();
			//問題を編集
			queDao.update(inputQuestion,questionId);
			
			//問題idからDBに登録されている答えデータを取得
			ArrayList<AnswersBean> aList = ansDao.findByQuestionId(questionId);
			
			//入力された答えの数だけ処理を繰り返す
			for(int i = 0; i < inputAnswers.length; i++) {
				if( i < answersId.length) { //入力された答えの中に、idを持つものがあった場合（更新された答えがあった場合）
					//idをもとにDBに登録されている答えデータを取得
					AnswersBean tmp_answer = ansDao.findById(answersId[i]);
					//入力された答えと、DBに登録されている答えの内容が一致していなかった場合
					if(!(inputAnswers[i].equals(tmp_answer.getAnswer()))) {
						ansDao.update_answer(answersId[i], inputAnswers[i]); //答えを更新
					}
				} else { //idを持たない答えがあった場合（新たに追加された答えがあった場合）
					ansDao.register_answer(questionId, inputAnswers[i]); //答えを登録
				}
			}
			if(aList.size() > answersId.length) { //既存の答えの数の方が、フォームから渡されたidの数より多かった場合（削除された答えがあった場合）
				for(AnswersBean ans : aList) { //既存の答えの数だけ、処理を繰り返す
					if(!(Arrays.stream(answersId).anyMatch(x -> x == ans.getId()))){ //既存の答えにしかないidがあった場合
						ansDao.delete_answer(ans.getId()); //答えを削除
					}
				}
			}
			//list画面に遷移
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
			String[] tmpAnswers = new String[ans.size()];
			for(int i = 0; i < ans.size(); i++) {
				//答えデータから答えを取得し、配列に格納
				tmpAnswers[i] = ans.get(i).getAnswer();
			}
			//aanswersに配列になっている答えを入れ直す
			answers = tmpAnswers;
		}
		return answers;
	}
	
	public int[] getAnswersId() throws Exception{
		//answersIdが0だった場合
		if (answersId == null) {
			AnswersDao ansDao = new AnswersDao();
			//questionIdから答えデータを取得
			ArrayList<AnswersBean> ans = ansDao.findByQuestionId(questionId);
			//答えidを一時的に入れる配列
			int[] tmpAnswersId = new int[ans.size()];
			for(int i = 0; i < ans.size(); i++) {
				//答えデータから答えを取得し、配列に格納
				tmpAnswersId[i] = ans.get(i).getId();
			}
			//aanswersに配列になっている答えを入れ直す
			answersId = tmpAnswersId;
		}
		return answersId;
	}
	
	public void setAnswersId(String answersId) {
		this.answersId = Stream.of(answersId.split(", ")).mapToInt(Integer::parseInt).toArray();
	}
}
