package kensyu3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AnswersDao extends ConnectionDao {
	/**
	 * コンストラクタ（戻り値のない）
	 */
	public AnswersDao() throws Exception {
		//DBと接続する
		setConnection();
	}

	/**
	 * correct_answers テーブルを全件取得
	 */
	public ArrayList<AnswersBean> findAll() throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		// correct_answers からidとquestions_id、answerを取得
		String sql = "SELECT id, questions_id, answer FROM correct_answers";
		
		/** PreparedStatement オブジェクトの取得とsqlの実行**/
		try(PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
			/** select文の結果をArrayListに格納 **/
			ArrayList<AnswersBean> list = new ArrayList<AnswersBean>();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				//オブジェクトを作成し、コンストラクタに値を渡す
				AnswersBean bean = new AnswersBean(id, questions_id, answer);
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
	 *  指定questions_idのレコードを取得する
	 */
	public ArrayList<AnswersBean> findByQuestionId(int questionId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers からidとquestions_id、answerを取得（条件：question_idが一致すること）
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの?に値をセット
			st.setInt(1, questionId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<AnswersBean> list = new ArrayList<AnswersBean>();
			
			//実行結果を一行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				//オブジェクトを作成し、コンストラクタに値を渡す
				AnswersBean bean = new AnswersBean(id, questions_id, answer);
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
	 *  指定answerのレコードを取得する
	 */
	public ArrayList<AnswersBean> findByAnswer(String input_answer) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers からidとquestions_id、answerを取得（条件：answerが一致するもの）
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE answer = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setString(1, input_answer);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/ 
			ArrayList<AnswersBean> list = new ArrayList<AnswersBean>();
			
			//実行結果を1行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				//オブジェクトを作成し、コンストラクタに値を渡す
				AnswersBean bean = new AnswersBean(id, questions_id, answer);
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
	 *  指定idのレコードを取得する
	 */
	public AnswersBean findById(int answerId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers からidとquestions_id、answerを取得（条件：idが一致するもの）
		String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE id = ? ";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, answerId);
			/** SQL 実行 **/
			ResultSet rs = st.executeQuery();
			/** select文の結果をオブジェクトに格納 **/ 
			AnswersBean bean = new AnswersBean();
			//実行結果を1行ずつ読み込む
			while (rs.next()) {
				//実行結果からデータを取得し、各フィールドに格納
				int id = rs.getInt("id");
				String answer = rs.getString("answer");
				int questions_id = rs.getInt("questions_id");
				//オブジェクトに実行結果を格納
				bean.setId(id);
				bean.setAnswer(answer);
				bean.setQuestionsId(questions_id);
			}
			//オブジェクトを返す
			return bean;
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの取得に失敗しました");
		}
	}
	
	/**
	 * 答えを登録する
	*/
	public void register(int questionId, String[] answers) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			// answersの要素数の分、SQLを実行
			for(String answer : answers) {
				//sqlの ? に値をセット
				st.setInt(1, questionId);
				st.setString(2, answer);
				/** SQL 実行 **/
				st.executeUpdate();
			}
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
	
	/**
	 * 答えを削除する
	*/
	public void delete(int questionId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers のデータを削除（条件：question_idが一致するもの）
		String sql = "DELETE FROM correct_answers WHERE questions_id = ?";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			insert_st.setInt(1, questionId);
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
	 * 答えを１件更新する
	*/
	public void update_answer(int answerId, String answer) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers のデータを更新（条件：idが一致するもの）
		String sql = "UPDATE correct_answers SET answer = ?, updated_at = CURRENT_TIMESTAMP() WHERE id = ?;";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setString(1, answer);
			st.setInt(2, answerId);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの更新に失敗しました");
		}	
	}
	
	/**
	 * 答えを１件登録する
	*/
	public void register_answer(int questionId, String answer) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers にデータを追加
		String sql = "INSERT INTO correct_answers (questions_id, answer, created_at) VALUES (?, ?, CURRENT_TIMESTAMP());";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			st.setInt(1, questionId);
			st.setString(2, answer);
			/** SQL 実行 **/
			st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの登録に失敗しました");
		}	
	}
	
	/**
	 * 答えを１件削除する
	*/
	public void delete_answer(int answerId) throws Exception {
		//DBと接続がない場合、接続
		if (con == null) {
			setConnection();
		}
		//correct_answers のデータを削除（条件：idが一致するもの）
		String sql = "DELETE FROM correct_answers WHERE id = ?";
		
		/** PreparedStatement オブジェクトの取得**/
		try(PreparedStatement insert_st = con.prepareStatement(sql)) {
			//sqlの ? に値をセット
			insert_st.setInt(1, answerId);
			/** SQL 実行 **/
			insert_st.executeUpdate();
		} catch (Exception e) {
			//スタックトレースを出力
			e.printStackTrace();
			//例外を投げる
			throw new DAOException("レコードの削除に失敗しました");
		}
	}
}

