package kensyu3.controller;

public class HomeAction extends Base{

	//top画面を表示
	public String top() {
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			//top画面に遷移
			return "success" ;
		}else {
			//login画面に遷移
			return "failure";
		}
	}

}
