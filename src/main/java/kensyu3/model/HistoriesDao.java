package kensyu3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class HistoriesDao extends ConnectionDao {
	
	/**
	 * コンストラクタ（戻り値のない）
	 */
	public HistoriesDao() throws Exception {
		//DBと接続する
		setConnection();
	}
	
	/**
	 * 指定useridのレコードを取得する
	 */
	public ArrayList<HistoriesBean> findByUserId(int currentUserId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//histories からidとuser_id, point, created_atを取得（条件：deleted_atが空で、user_idが一致するもの）
		String sql = "SELECT id, user_id, point, created_at FROM histories WHERE deleted_at is null and user_id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {	
			//sqlの ? に値をセット
			st.setInt(1, currentUserId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<HistoriesBean> list = new ArrayList<HistoriesBean>();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int point = rs.getInt("point");
				Timestamp createdAt = rs.getTimestamp("created_at");
				//オブジェクトを作成し、コンストラクタに値を渡す
				HistoriesBean bean = new HistoriesBean(id, userId, point, createdAt);
				//listにオブジェクトを追加
				list.add(bean);
			}
			//listを返す（実行結果があればオブジェクトが格納されているが、実行結果がなければ空）
			return list;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 * 履歴を登録する
	 */
	public void register(int userId, int point) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//histories にデータを追加
		String sql = "INSERT INTO histories (user_id, point, created_at) VALUES (?, ?,  CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, userId);
			st.setInt(2, point);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
		}
	}

}
