package kensyu3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
		//users からidとpassword、name, admin_flagを取得（条件：idが一致すること、deleted_atが空であること）
		String sql = "SELECT id, name, password, admin_flag FROM users WHERE deleted_at is null and id = ? ";
		
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
				int admin_flag = rs.getInt("admin_flag");
				//UsersBean型のbeanに実行結果を格納
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(pass);
				bean.setAdminFlag(admin_flag);
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
	
	/**
	 * users テーブルを全件取得
	 */
	public ArrayList<UsersBean> findAll() throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//users からデータを取得
		String sql = "SELECT id, name, password, admin_flag FROM users";
		
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
			/** select文の結果をArrayListに格納 **/
			ArrayList<UsersBean> list = new ArrayList<UsersBean>();
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				int admin_flag = rs.getInt("admin_flag");
				//オブジェクトを作成し、コンストラクタに値を渡す
				UsersBean bean = new UsersBean(id, name, password, admin_flag);
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
	
	/**
	 * ユーザーを登録する
	 */
	public void register(String userName, String password, int auth) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//users にデータを追加
		String sql = "INSERT INTO users (name, password, admin_flag, created_at) VALUES (?, ?, ?,  CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setString(1, userName);
			st.setString(2, password);
			st.setInt(3, auth);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
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
	
	/**
	 * ユーザーを編集する
	 */
	public void update(int id, String password, int auth) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//users にデータを追加
		String sql = "UPDATE users set password = ?, admin_flag = ?, updated_at = CURRENT_TIMESTAMP() WHERE id = ?;";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//isnert_sqlの ? に値をセット
			st.setString(1, password);
			st.setInt(2, auth);
			st.setInt(3, id);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの更新に失敗しました");
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