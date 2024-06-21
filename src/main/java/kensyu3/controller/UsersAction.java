package kensyu3.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;


public class UsersAction extends ActionSupport implements SessionAware{
	
	private String id;
	private String password;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	private Map<String, Object> session;
	
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
		
		UsersBean user = usersDao.search_id(userId);
		//フォームから渡されたIDと一致するレコードがあり、そのレコードのパスワードが、入力されたパスワードと一致する場合
		if(user.getId() != 0 && user.getPassword().equals(password)) {
			//セッションに、userIdを格納
			session.put("userId", userId);
			
			//top画面に遷移
			return "success";
			
		} else { //IDに一致するレコードがなかった場合もしくは、パスワードが一致しなかった場合
			return "failure";
		}
	}
	
	//logout処理
	public String logout() throws Exception{
		session.clear();
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
}
