package kensyu3.model;

import java.sql.Timestamp;

public class QuestionsBean {
	private int id;
	private String question;
	private Timestamp created_at;
	private Timestamp updated_at;

	/**
	 * コンストラクタで、id、questionに値をセット
	 */
	public QuestionsBean (int id, String question){
		this.id = id;
		this.question = question;
	}
	
	/** 引数無しのコンストラクタ **/
	public QuestionsBean() {
	}
	
	//メンバ変数のidを取得
	public int getId() {
		return this.id;
	}
	
	//メンバ変数のidに値をセット
	public void setId(int id) {
		this.id = id;
	}
	
	//メンバ変数のquestionを取得
	public String getQuestion() {
		return this.question;
	}
	
	//メンバ変数のquestionに値をセット
	public void setQuestion(String question) {
		this.question = question;
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
