package kensyu3.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Base extends ActionSupport implements SessionAware{
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	private Map<String, Object> session;
	
	/**
	 * ログインチェック判定
	 */
	protected boolean isCheckLogin() {
		//セッションを取得
		//セッションが空だった場合もしくは、セッションにuserIdが格納されていない場合
		if (session == null || (session.get("userId")) == null){
			return false;
		}
		//セッションにuserIdがある場合、falseを返す
		return true;
	}

}
