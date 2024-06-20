package kensyu3.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ConnectionDao {
	////接続に必要な変数を宣言し、値を格納
	final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	final String DB_NAME = "mydatabase";
	final String DB_USER = "root";
	final String DB_PASSWORD = "masuda99";
	
	public Connection con;
	
	//コンストラクタ
	public ConnectionDao() throws Exception {
		setConnection();
	}
	
	//DBに接続する処理
	public void setConnection() throws Exception{
		try {
			String url = "jdbc:mysql://localhost:3306/" + DB_NAME + "?ServerTimezone=JST&allowPublicKeyRetrieval=true&useSSL=false";
			//JDBCドライバをロード
			Class.forName(DRIVER_NAME).newInstance();
			//DBに接続する
			con = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
			
		//DB接続に失敗した場合
		} catch(SQLException e) {
			e.printStackTrace();
			throw new Exception();
		//その他のエラーが発生した場合
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	//現在日時を取得
	public String getStringCurrentTimestamp() {
		//現在の日時をtimestampに格納
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		//日時のフィーマットを指定
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String strTimestamp = sdf.format(timestamp);
		//yyyy/MM/dd 形式の現在日時を返す
		return strTimestamp;
	}
	
	public void close() throws SQLException{
		//接続先の情報がある場合には、接続をクローズし、変数を空にする
		if(con != null) {
			con.close();
			con = null;
		}
	}
}


