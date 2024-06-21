package kensyu3.controller;

import java.util.ArrayList;

import kensyu3.model.AnswersBean;
import kensyu3.model.AnswersDao;
import kensyu3.model.QuestionsBean;
import kensyu3.model.QuestionsDao;

public class QuestionsAnswersAction extends Base{
	//jspファイルから受け取る値の定義
	private ArrayList<QuestionsBean> queList = new ArrayList<QuestionsBean>();
	private ArrayList<ArrayList<AnswersBean>> ansList = new ArrayList<>();
	
	//list画面を表示
	public String list() throws Exception{
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			QuestionsDao queDao = new QuestionsDao();
			AnswersDao ansDao = new AnswersDao();
			//登録されている問題を取得
			queList = queDao.findAll();
			//それぞれの問題に紐づく答えを取得し、リスト化する
			for(QuestionsBean que: queList) {
				ArrayList<AnswersBean> ans = ansDao.findByQuestionId(que.getId());
				ansList.add(ans);
		}
			//list画面に遷移
			return "success" ;
		}else {
			//login画面に遷移
			return "failure";
		}
	}
	
	public ArrayList<QuestionsBean> getQueList() {
		return queList;
	}
	
	public ArrayList<ArrayList<AnswersBean>> getAnsList() {
		return ansList;
	}
}
