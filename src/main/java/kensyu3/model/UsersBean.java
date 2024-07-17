package kensyu3.model;

import java.sql.Timestamp;

public class UsersBean {
	private int id;
	private String name;
	private String password;
	private Timestamp created_at;
	private Timestamp updated_at;
	private byte deleteflag; 
	private Timestamp deleted_at;
	private int admin_flag;

	/**
	 * コンストラクタで、id, name, password, admin_flagに値をセット
	 */
	public UsersBean (int id, String name, String password, int admin_flag){
		this.id = id;
		this.name = name;
		this.password = password;
		this.admin_flag = admin_flag;
	}
	
	/** 引数無しのコンストラクタ **/
	public UsersBean() {
	}
	
	//メンバ変数のidを取得
	public int getId() {
		return this.id;
	}
	
	//メンバ変数のidに値をセット
	public void setId(int id) {
		this.id = id;
	}
	
	//メンバ変数のnameを取得
	public String getName() {
		return this.name;
	}
	
	//メンバ変数のnameに値をセット
	public void setName(String name) {
		this.name = name;
	}
	
	//メンバ変数のpasswordを取得
	public String getPassword() {
		return this.password;
	}
	
	//メンバ変数のpasswordに値をセット
	public void setPassword(String password) {
		this.password = password;
	}
	
	//メンバ変数のcreated_atを取得
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	
	//メンバ変数のcreated_atに値をセット
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	//メンバ変数のupdatedatを取得
	public Timestamp getUpdatedAt() {
		return this.updated_at;
	}
	
	//メンバ変数のupdated_atに値をセット
	public void setUpdatedAt(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
	//メンバ変数のdeleteflagを取得
	public byte getDeleteflag() {
		return this.deleteflag;
	}
	
	//メンバ変数のdeleteflagに値をセット
	public void setDeleteflag(byte deleteflag) {
		this.deleteflag = deleteflag;
	}
	
	//メンバ変数のadmin_flagを取得
	public int getAdminFlag() {
		return this.admin_flag;
	}
	
	//メンバ変数のadmin_flagに値をセット
	public void setAdminFlag(int admin_flag) {
		this.admin_flag = admin_flag;
	}
}
