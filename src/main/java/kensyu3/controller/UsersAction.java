package kensyu3.controller;

import java.util.ArrayList;

import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;
import kensyu3.other.PasswordEncrypter;


public class UsersAction extends Base{
	
	/*
	 *  jspファイルから受け取る値の定義
	 */
	private int currentUserAuth = 2; //初期値として、0と1以外の値を設定
	private int auth;
	private String id;
	private String password;
	private String userName;
	private ArrayList<UsersBean> userList = new ArrayList<UsersBean>();
	private UsersBean user;
	
	
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
		PasswordEncrypter passwordEncrypter = new PasswordEncrypter();
		
		//フォームから渡されたIDと一致するレコードがあり、そのレコードのパスワードが、入力されたパスワードを暗号化したものと一致する場合
		if(user.getId() != 0 && user.getPassword().equals(passwordEncrypter.encrypt(password))) {
			//セッションに、userIdを格納
			session.put("userId", userId);
			//top画面に遷移
			return "success";
			
		} else { //IDに一致するレコードがなかった場合もしくは、パスワードが一致しなかった場合
			//login画面に遷移
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
	
	//user_lists画面を表示
	public String user_lists() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//ユーザーの権限があるか確認
			if (getCurrentUserAuth() == 1) {
				//user_lists画面に遷移
				return "user_lists";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//user_register画面を表示
	public String user_register() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//ユーザーの権限があるか確認
			if (getCurrentUserAuth() == 1) {
				//user_register画面に遷移
				return "user_register";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//user_register_confirm画面を表示
	public String user_register_confirm() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//入力した内容があるかどうか
			if(userName != null) {
				//user_register_confirm画面に遷移
				return "user_register_confirm";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//登録処理
	public String user_register_complete() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//入力した内容があるかどうか
			if(userName != null) {
				UsersDao usersDao = new UsersDao();
				//パスワードを暗号化
				PasswordEncrypter passwordEncrypter = new PasswordEncrypter();
				String encyptPassword = passwordEncrypter.encrypt(password);
				//ユーザーを登録
				usersDao.register(userName, encyptPassword, auth);
				//user_lists画面に遷移
				return "user_lists";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//user_edit画面を表示
	public String user_edit() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//ユーザー権限があり、かつパラメータで指定したidが存在するか
			if(getCurrentUserAuth() == 1 && getUser().getId() != 0) {
				//user_edit画面に遷移
				return "user_edit";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//user_edit_confirm画面を表示
	public String user_edit_confirm() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//入力した内容があるかどうか
			if(password != null) {
				//user_edit_confirm画面に遷移
				return "user_edit_confirm";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//編集処理
	public String user_edit_complete() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//入力した内容があるかどうか
			if(password != null) {
				UsersDao usersDao = new UsersDao();
				//パスワードを暗号化
				PasswordEncrypter passwordEncrypter = new PasswordEncrypter();
				String encyptPassword = passwordEncrypter.encrypt(password);
				//ユーザーを更新
				usersDao.update(Integer.parseInt(id), encyptPassword, auth);
				//ユーザー権限があるかどうか確認
				if(getCurrentUserAuth() == 1) {
					//user_lists画面に遷移
					return "user_lists";
				}else {
					return "top";
				}
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//delete_confirm画面
	public String user_delete_confirm() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//ユーザー権限があり、かつパラメータで指定したidが存在するか
			if(getCurrentUserAuth() == 1 && getUser().getId() != 0) {
				//user_delete_confirm画面に遷移
				return "user_delete_confirm";
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
	}
	
	//削除処理
	public String user_delete_complete() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//フォームから送られてきたidがあるかどうか
			if(id != null) {
				UsersDao usersDao = new UsersDao();
				//ユーザーを論理削除
				usersDao.delete(Integer.parseInt(id));
				//削除したユーザーが現在ログインしているユーザーかどうか
				if((int)session.get("userId")== Integer.parseInt(id)) {
					//セッションを削除
					session.clear();
					//ログイン画面に初期値が設定されないように、idをnullにする
					id = null;
					//login画面に遷移
					return "login";
				}else {
					//user_lists画面に遷移
					return "user_lists";
				}
			}else {
				//error画面に遷移
				return "error";
			}
		}else {
			//ログイン画面に遷移
			return "failure";
		}
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
		//パスワードが空だった場合、user_edit画面で使用するパスワードを格納
		if(password == null) {
			//パスワードを復号化
			PasswordEncrypter passwordEncrypter = new PasswordEncrypter();
			password = passwordEncrypter.decrypt(user.getPassword());
		}
		return password;
	}
	
	//passwordのsetter
	public void setPassword(String password) {
		this.password = password;
	}
	
	//userNameのgetter
	public String getUserName() {
		return userName;
	}
	
	//userNameのsetter
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//authのgetter
	public int getAuth() {
		return auth;
	}
	
	//authのsetter
	public void setAuth(int[] auth) {
		//authが2つ送られてきた場合、2つ目に送られた値をauthに格納
		if(auth.length > 1) {
			this.auth = auth[1];
		//authが1つだけ送られてきた場合は、その値をauthに格納
		}else {
			this.auth = auth[0];
		}
	}
	
	//全てのユーザー情報のgetter
	public ArrayList<UsersBean> getUserList() throws Exception{
		//userListに値が格納されていない場合
		if (userList.isEmpty()) {
			UsersDao usersDao = new UsersDao();
			//ユーザーデータを全件取得
			userList = usersDao.findAll();
		}
		return userList;
	}
	
	//ユーザー情報のgetter
	public UsersBean getUser() throws Exception{
		if(user == null) {
			user = new UsersBean();
			UsersDao usersDao = new UsersDao();
			user = usersDao.findById(Integer.parseInt(id));
		}
		return user;
	}
	
	//現在ログインしているユーザー権限のgetter
	public int getCurrentUserAuth() throws Exception{
		//userにユーザー情報が格納されていない場合
		if (currentUserAuth == 2) {
			UsersDao usersDao = new UsersDao();
			//現在ログインしているユーザー情報を取得
			UsersBean user = usersDao.findById((int)session.get("userId"));
			currentUserAuth = user.getAdminFlag();
		}
		return currentUserAuth;
	}
}
