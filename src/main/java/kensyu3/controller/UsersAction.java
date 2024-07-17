package kensyu3.controller;

import java.util.ArrayList;

import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;
import kensyu3.other.PasswordEncrypter;


public class UsersAction extends Base{
	
	private String id;
	private String password;
	private ArrayList<UsersBean> userList = new ArrayList<UsersBean>();
	
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
	
	//user_lists画面を表示
	public String user_lists() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			return "success";
		}else {
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<UsersBean> getUserList() throws Exception{
		//userListに値が格納されていない場合
		if (userList.isEmpty()) {
			UsersDao userDao = new UsersDao();
			//問題データを全件取得
			userList = userDao.findAll();
		}
		return userList;
	}
}
