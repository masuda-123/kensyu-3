package kensyu3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionsDao extends ConnectionDao {

	/**
	 * コンストラクタ（戻り値のない）
	 */
	public QuestionsDao() throws Exception {
		//DBと接続する
		setConnection();
	}
	
	/**
	 * questions テーブルを全件取得
	 */
	public ArrayList<QuestionsBean> findAll() throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//questions からidとquestionを取得
		String sql = "SELECT id, question FROM questions";
		
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
			/** select文の結果をArrayListに格納 **/
			ArrayList<QuestionsBean> list = new ArrayList<QuestionsBean>();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String question = rs.getString("question");
				//オブジェクトを作成し、コンストラクタに値を渡す
				QuestionsBean bean = new QuestionsBean(id, question);
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
	 * 指定IDのレコードを取得する
	 */
	public QuestionsBean findById(int questionId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//questions からidとquestionを取得（条件：idが一致するもの）
		String sql = "SELECT id, question FROM questions WHERE id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, questionId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			QuestionsBean bean = new QuestionsBean();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String question = rs.getString("question");
				//QuestionsBean型のbeanに実行結果を格納
				bean.setId(id);
				bean.setQuestion(question);
			}
			//beanオブジェクトを返す
			return bean;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 * 問題を登録する
	*/
	public int register(String question) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//questions にデータを追加
		String insert_sql = "INSERT INTO questions (question, created_at) VALUES (?, CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(insert_sql)) {
			//isnert_sqlの ? に値をセット
			insert_st.setString(1, question);
			/** SQL 実行 **/
			insert_st.executeUpdate();
			//questions の id の最大値を取得
			String select_sql = "SELECT MAX(id) from questions;";
			
			/** PreparedStatement オブジェクトの取得とSQLの実行**/
			try(PreparedStatement select_st = con.prepareStatement(select_sql); ResultSet rs = select_st.executeQuery()) {
				/** id の最大値を変数に格納 **/ 
				int questions_id = 0;
				while (rs.next()) {
					questions_id = rs.getInt("MAX(id)");
				}
				//id の最大値を返す
				return questions_id;
			} catch (Exception e) {
				//スタックトレースを出力
				e.printStackTrace();
				//例外を投げる
				throw new DAOException("レコードの取得に失敗しました");
			}
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
		}
	}
	
	/**
	 * 問題の削除
	*/
	public void delete(int id) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//questions からデータを削除（条件：idが一致するもの）
		String sql = "DELETE FROM questions WHERE id = ?";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			insert_st.setInt(1, id);
			/** SQL 実行 **/
			insert_st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの削除に失敗しました");
		}
	}
	
	/**
	 * 問題を更新する
	*/
	public void update(String question, int questionId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//questions のデータを更新（条件：idが一致するもの）
		String sql = "UPDATE questions SET question = ?, updated_at = CURRENT_TIMESTAMP() WHERE id = ?;";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setString(1, question);
			st.setInt(2, questionId);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの更新に失敗しました");
		}
	}
}