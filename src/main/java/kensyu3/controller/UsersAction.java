package kensyu3.controller;

import java.util.ArrayList;

import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;
import kensyu3.other.PasswordEncrypter;


public class UsersAction extends Base{
	
	/*
	 *  jspファイルから受け取る値の定義
	 */
	private int auth = 2; //初期値として、0と1以外の値を設定
	private String id;
	private String password;
	private ArrayList<UsersBean> userList = new ArrayList<UsersBean>();
	
	
	/*
	 *  アクションの定義
	 */
	
	//login画面を表示
	public String login() throws Exception{
		return "success";
	}
	
	//login処理
	public String postLogin() throws Exception{
		UsersDao usersDao = new UsersDao();
		int userId = 0;
		try {
			//String型のidをint型に変換
			userId = Integer.parseInt(id);
		//int型に変換できない場合は、userIdに値を格納しない
		} catch(NumberFormatException nfex) {
			
		}
		
		//idをもとにユーザーを取得
		UsersBean user = usersDao.findById(userId);
		PasswordEncrypter passwordEncyoter = new PasswordEncrypter();
		
		//フォームから渡されたIDと一致するレコードがあり、そのレコードのパスワードが、入力されたパスワードを暗号化したものと一致する場合
		if(user.getId() != 0 && user.getPassword().equals(passwordEncyoter.encypt(password))) {
			//セッションに、userIdを格納
			session.put("userId", userId);
			//top画面に遷移
			return "success";
			
		} else { //IDに一致するレコードがなかった場合もしくは、パスワードが一致しなかった場合
			//login画面に遷移
			return "failure";
		}
	}
	
	//top画面を表示
	public String top() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//top画面に遷移
			return "success" ;
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	//user_lists画面を表示
	public String user_lists() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//ユーザーの権限があるか確認
			if (getAuth() == 1) {
				//user_lists画面に遷移
				return "user_lists";
			}else {
				//エラー画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//logout処理
	public String logout() throws Exception{
		//セッションを削除
		session.clear();
		//login画面に遷移
		return "success";
	}
	
	/*
	 *  getter、setterの定義
	 */
	
	//userIdのgetter
	public String getId() {
		return id;
	}
	
	//userIdのsetter
	public void setId(String id) {
		this.id = id;
	}
	
	//passwordのgetter
	public String getPassword() {
		return password;
	}
	
	//passwordのsetter
	public void setPassword(String password) {
		this.password = password;
	}
	
	//全てのユーザー情報のgetter
	public ArrayList<UsersBean> getUserList() throws Exception{
		//userListに値が格納されていない場合
		if (userList.isEmpty()) {
			UsersDao userDao = new UsersDao();
			//ユーザーデータを全件取得
			userList = userDao.findAll();
		}
		return userList;
	}
	
	//現在ログインしているユーザーの権限のgetter
	public int getAuth() throws Exception{
		//userにユーザー情報が格納されていない場合
		if (auth == 2) {
			UsersDao usersDao = new UsersDao();
			//現在ログインしているユーザー情報を取得
			UsersBean user = usersDao.findById((int)session.get("userId"));
			auth = user.getAdminFlag();
		}
		return auth;
	}
}
