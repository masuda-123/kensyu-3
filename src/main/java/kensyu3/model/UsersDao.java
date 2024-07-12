package kensyu3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDao extends ConnectionDao {

	/**
	 * コンストラクタ（戻り値のない）
	 */
	public UsersDao() throws Exception {
		//DBと接続する
		setConnection();
	}
	
	/**
	 * 指定IDのレコードを取得する
	 */
	public UsersBean findById(int userId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//users からidとpassword、nameを取得（条件：idが一致すること、deleted_atが空であること）
		String sql = "SELECT id, name, password FROM users WHERE deleted_at is null and id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, userId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			UsersBean bean = new UsersBean();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				//UsersBean型のbeanに実行結果を格納
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(pass);
			}
			//beanオブジェクトを返す
			return bean;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			//リソースの開放
			try {
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}
}