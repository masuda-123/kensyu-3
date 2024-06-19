package kensyu3.controller;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import kensyu3.model.QuestionsBean;
import kensyu3.model.QuestionsDao;

public class QuestionsAnswersAction extends ActionSupport{
	
	private ArrayList<QuestionsBean> queList = new ArrayList<QuestionsBean>();
	
    public String getList() throws Exception{
		QuestionsDao queDao = new QuestionsDao();
		//findAllメソッドを呼び出し、登録されている問題を取得
		queList = queDao.findAll();
        return "success";
    }
    
    public ArrayList<QuestionsBean> getQueList() {
    	return queList;
    }

}
