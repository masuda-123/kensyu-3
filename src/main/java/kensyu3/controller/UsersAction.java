package kensyu3.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;


public class UsersAction extends ActionSupport implements SessionAware{
	
	private int id;
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
		UsersBean user = usersDao.search_id(id);
		//フォームから渡されたIDと一致するレコードがあり、そのレコードのパスワードが、入力されたパスワードと一致する場合
		if(user.getId() != 0 && user.getPassword().equals(password)) {
			//セッションの取得
			if (session != null) { //既にセッションが存在する場合
				//セッションを破棄
				session.clear();
			}
			//セッションに、userIdを格納
			session.put("userId", id);
			
			//top画面に遷移
			return "success";
			
		} else { //IDに一致するレコードがなかった場合もしくは、パスワードが一致しなかった場合
			return "failure";
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {//passwordのsetter
		this.password = password;
	}
}
