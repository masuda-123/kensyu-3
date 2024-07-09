package kensyu3.model;

import java.sql.Timestamp;

public class HistoriesBean {
	private int id;
	private int user_id;
	private int point;
	private Timestamp created_at;
	private byte deleteflag; 
	private Timestamp deleted_at;

	/**
	 * コンストラクタで、id、user_id、point、created_atに値をセット
	 */
	public HistoriesBean (int id, int user_id, int point, Timestamp created_at){
		this.id = id;
		this.user_id = user_id;
		this.point = point;
		this.created_at = created_at;
	}
	
	/** 引数無しのコンストラクタ **/
	public HistoriesBean() {
	}
	
	//メンバ変数のidを取得
	public int getId() {
		return this.id;
	}
	
	//メンバ変数のidに値をセット
	public void setId(int id) {
		this.id = id;
	}
	
	//メンバ変数のuser_idを取得
	public int getUserId() {
		return this.user_id;
	}
	
	//メンバ変数のuser_idに値をセット
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
	//メンバ変数のpointを取得
	public int getPoint() {
		return this.point;
	}
	
	//メンバ変数のpointに値をセット
	public void setPoint(int point) {
		this.point = point;
	}
	
	//メンバ変数のcreated_atを取得
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	
	//メンバ変数のcreated_atに値をセット
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	//メンバ変数のdeleteflagを取得
	public byte getDeleteflag() {
		return this.deleteflag;
	}
	
	//メンバ変数のdeleteflagに値をセット
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}
}
