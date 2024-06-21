package kensyu3.controller;

public class HomeAction extends Base{

	//top画面を表示
	public String top() {
		//Baseクラスでログインしているかどうかを確認
		if (super.isCheckLogin()) {
			return "success" ;
		}else {
			return "failure";
		}
	}

}
