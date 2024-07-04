package kensyu3.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import kensyu3.model.HistoriesBean;
import kensyu3.model.HistoriesDao;
import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;

public class HistoriesAction extends Base {
	//jspファイルから受け取る値の定義
	private ArrayList<HistoriesBean> hisList = new ArrayList<HistoriesBean>();
	private String userName;
	private String[] dateTime;
	
	//採点結果履歴画面を表示
	public String history() {
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//history画面に遷移
			return "success" ;
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	public ArrayList<HistoriesBean> getHisList() throws Exception{
		HistoriesDao hisDao = new HistoriesDao();
		hisList = hisDao.findByUserId((int)session.get("userId"));
		return hisList;
	}
	
	public String getUserName() throws Exception{
		UsersDao usersDao = new UsersDao();
		UsersBean user = usersDao.findById((int)session.get("userId"));
		userName = user.getName();
		return userName;
	}
	
	public String[] getDateTime() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateTime = new String[hisList.size()];
		for(int i = 0 ; i < dateTime.length; i++) {
			dateTime[i] = sdf.format(hisList.get(i).getCreatedAt());
		}
		return dateTime;
	}

}
