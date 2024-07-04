package kensyu3.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import kensyu3.model.AnswersBean;
import kensyu3.model.AnswersDao;
import kensyu3.model.QuestionsBean;
import kensyu3.model.QuestionsDao;
import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;
import kensyu3.other.Validation;

public class QuestionsAnswersAction extends Base{
	
	//jspファイルから受け取る値の定義
	private ArrayList<QuestionsBean> queList = new ArrayList<QuestionsBean>();
	private ArrayList<ArrayList<AnswersBean>> ansList = new ArrayList<>();
	private ArrayList<QuestionsBean> randomQueList = new ArrayList<QuestionsBean>();
	private String question;
	private String[] answers;
	private int questionId;
	private int[] answersId;
	private int[] questionsId;
	private String inputQuestion;
	private String[] inputAnswers;
	private String userName;
	private String errorMessage;
	private int score;
	private int correctCnt;
	private String currentDateTime;
	
	/*
	 *  アクションの定義
	 */
	
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
					AnswersBean tmpAnswer = ansDao.findById(answersId[i]);
					//入力された答えと、DBに登録されている答えの内容が一致していなかった場合
					if(!(inputAnswers[i].equals(tmpAnswer.getAnswer()))) {
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
	
	//テスト画面を表示
	public String test() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//test画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//採点結果画面を表示
	public String result() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//reult画面に遷移
			return "success";
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	
	/*
	 *  getter、setterの定義
	 */
	
	//全問題のgetter
	public ArrayList<QuestionsBean> getQueList() throws Exception {
		//queListが空だった場合
		if (queList.isEmpty()) {
			QuestionsDao queDao = new QuestionsDao();
			//問題データを全件取得
			queList = queDao.findAll();
		}
		return queList;
	}
	
	//全答えのgetter
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
	
	//ランダムな問題のgetter
	public ArrayList<QuestionsBean> getRandomQueList() throws Exception {
		//randomQueListが空だった場合
		if (randomQueList.isEmpty()) {
			QuestionsDao queDao = new QuestionsDao();
			//問題データを全件取得
			randomQueList = queDao.findAll();
			//ランダムに並び替える
			Collections.shuffle(randomQueList);
		}
		return randomQueList;
	}
	
	//問題のgetter
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
	
	//答えのgetter
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
			//answersに配列となった答えを入れ直す
			answers = tmpAnswers;
		}
		return answers;
	}
	
	//問題idのgetter
	public int getQuestionId() {
		return questionId;
	}
	
	//問題idのsetter
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	//答えidのgetter
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
	
	//答えidのsetter
	public void setAnswersId(String answersId) {
		//answersIdを配列にして、int型に変換
		this.answersId = Stream.of(answersId.split(", ")).mapToInt(Integer::parseInt).toArray();
	}
	
	//問題id（複数）のsetter
	public void setQuestionsId(String questionsId) {
		this.questionsId = Stream.of(questionsId.split(", ")).mapToInt(Integer::parseInt).toArray();
	}
	
	//問題id（複数）のgetter
	public int[] getQuestionsId() {
		return questionsId;
	}
	
	
	//フォームから入力した問題のgetter
	public String getInputQuestion() {
		return inputQuestion;
	}
	
	//フォームから入力した問題のsetter
	public void setInputQuestion(String inputQuestion) {
		this.inputQuestion = inputQuestion;
	}
	
	//フォームから入力した答えのgetter
	public String[] getInputAnswers() {
		return inputAnswers;
	}
	
	//フォームから入力した答えのsetter
	public void setInputAnswers(String inputAnswers) {
		this.inputAnswers = inputAnswers.split(", ");
	}
	
	//エラーメッセージのgetter
	public String getErrorMessage() {
		return errorMessage;
	}
	
	//現在ログインしているユーザー名のgetter
	public String getUserName() throws Exception{
		UsersDao usersDao = new UsersDao();
		UsersBean user = usersDao.findById((int)session.get("userId"));
		userName = user.getName();
		return userName;
	}
	
	//点数のgetter
	public int getScore() {
		//点数を計算
		score = Math.round(100 * correctCnt / questionsId.length);
		return score;
	}
	
	//正答数のgetter
	public int getCorrectCnt() throws Exception {
		//入力された答えの数だけ処理を繰り返す
		for(int i = 0; i < inputAnswers.length; i++) {
			AnswersDao ansDao = new AnswersDao();
			//入力された答えと一致するレコードを取得
			ArrayList<AnswersBean> aList = ansDao.findByAnswer(inputAnswers[i]);
			//取得したレコードの数だけ繰り返す
			for(AnswersBean ans : aList ) {
				//答えに紐づく問題idが一致する場合
				if(ans.getQuestionsId() == questionsId[i]) {
					//正解の問題数をカウントアップ
					correctCnt ++;
					//繰り返し処理を抜ける
					break;
				}
			}
		}
		return correctCnt;
	}
	
	//現在日時のgetter
	public String getCurrentDateTime() {
		//現在の日時をtimestampに格納
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//日時のフィーマットを指定
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		currentDateTime = sdf.format(timestamp);
		//yyyy/MM/dd 形式の現在日時を返す
		return currentDateTime;
	}

}
