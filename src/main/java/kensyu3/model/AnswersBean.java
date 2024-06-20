package kensyu3.model;

import java.sql.Timestamp;

public class AnswersBean {
	private int id;
	private int questions_id;
	private String answer;
	private Timestamp created_at;
	private Timestamp updated_at;

	/**
	 * コンストラクタで、id、question_id、answerに値をセット
	 */
	public AnswersBean (int id, int questions_id, String answer){
		this.id = id;
		this.questions_id = questions_id;
		this.answer = answer;
	}
	
	/** 引数無しのコンストラクタ **/
	public AnswersBean() {
	}
	
	//メンバ変数のidを取得
	public int getId() {
		return this.id;
	}
	
	//メンバ変数のidに値をセット
	public void setId(int id) {
		this.id = id;
	}
	
	//メンバ変数のquestions_Idを取得
	public int getQuestionsId() {
		return this.questions_id;
	}
	
	//メンバ変数のquestion_idに値をセット
	public void setQuestionsId(int questions_id) {
		this.questions_id = questions_id;
	}
	
	//メンバ変数のanswerを取得
	public String getAnswer() {
		return this.answer;
	}
	
	//メンバ変数のanswerに値をセット
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	//メンバ変数のcreated_atを取得
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	
	//メンバ変数のcreated_atに値をセット
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	//メンバ変数のupdated_atを取得
	public Timestamp getUpdatedAt() {
		return this.updated_at;
	}
	
	//メンバ変数のupdated_atに値をセット
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}
