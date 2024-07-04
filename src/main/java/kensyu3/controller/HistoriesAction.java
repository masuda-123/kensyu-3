package kensyu3.controller;

public class HistoriesAction extends Base {
	
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

}
