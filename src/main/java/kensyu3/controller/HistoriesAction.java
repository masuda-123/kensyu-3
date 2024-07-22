package kensyu3.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

import kensyu3.model.HistoriesBean;
import kensyu3.model.HistoriesDao;
import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;

public class HistoriesAction extends Base {
	//jspファイルから受け取る値の定義
	private ArrayList<HistoriesBean> hisList = new ArrayList<HistoriesBean>();
	private String userName;
	private String[] dateTime;
	
	/*
	 *  アクションの定義
	 */
	
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
	
	/*
	 *  getterの定義
	 */
	
	//全ての履歴情報のgetter
	public ArrayList<HistoriesBean> getHisList() throws Exception{
		//hisListに値が格納されていない場合
		if (hisList.isEmpty()) {
			HistoriesDao hisDao = new HistoriesDao();
			//セッションに保存されているuserIdをもとに履歴のデータを取得
			hisList = hisDao.findByUserId((int)session.get("userId"));
			//履歴が採点日時の昇順に表示されるように、並び替えの条件を定義
			Comparator<HistoriesBean> compare = Comparator.comparing(HistoriesBean::getCreatedAt);
			//指定した条件で並び替える
			hisList.sort(compare);
		}
		return hisList;
	}
	
	//ユーザー名のgetter
	public String getUserName() throws Exception{
		//userNameに値が格納されていない場合
		if (userName == null) {
			UsersDao usersDao = new UsersDao();
			//セッションに保存されているuserIdをもとにユーザー名を取得
			UsersBean user = usersDao.findById((int)session.get("userId"));
			userName = user.getName();
		}
		return userName;
	}
	
	//現在時刻のgetter
	public String[] getDateTime() throws Exception{
		//フォーマットを作成
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateTime = new String[hisList.size()];
		//履歴データから作成日を取り出し、配列にする
		for(int i = 0 ; i < dateTime.length; i++) {
			dateTime[i] = sdf.format(hisList.get(i).getCreatedAt());
		}
		return dateTime;
	}

}
