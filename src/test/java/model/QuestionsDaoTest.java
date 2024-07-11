package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.util.ArrayList;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kensyu3.model.QuestionsBean;
import kensyu3.model.QuestionsDao;

public class QuestionsDaoTest {
	/** ロガー */
	private Logger logger = LoggerFactory.getLogger(QuestionsDao.class);
	/** DBUnitのテスター */
	private static IDatabaseTester databaseTester;
	
	//DBに事前データをセット
	@Before
	public void setUp() throws Exception {
		logger.info("前処理開始");
		
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/mydatabase?ServerTimezone=JST&allowPublicKeyRetrieval=true&useSSL=false", "root", "masuda99");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/java/dbUnit/testData.xml"));
		databaseTester.setDataSet(dataSet);
		// DELETE→INSERTで事前準備データを用意する
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		logger.info("前処理終了");
	}
	
	//DBの状態をリセット
	@After
	public void tearDown() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		databaseTester.onTearDown();
		logger.info("後処理終了");
	}
	
	@Test
	//findAllメソッドで、問題が全件取得できること
	public void getQuestionAll() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//問題を全件取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		//DBに登録されているデータと、取得したデータが一致しているか確認
		assertThat(queList.get(0).getQuestion(), is("test"));
		assertThat(queList.get(1).getQuestion(), is("test2"));
		assertThat(queList.get(2).getQuestion(), is("test3"));
	}
	
	@Test
	//registerメソッドで、問題が登録でき、idが取得できていること
	public void getQuestion() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//問題を全件取得
		ArrayList<QuestionsBean> queList1 = dao.findAll();
		//questionを渡し、問題を登録
		String question = "testtest";
		int questionId = dao.register(question);
		//問題を全件取得
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		//DBに登録されている答えが1件増えたことを確認
		assertThat(queList2.size() - queList1.size(), is(1));
		//DBの最新のデータが、登録したデータと一致しているか確認
		assertThat(queList2.get(3).getQuestion(), is(question));
		//questionIdが取得できていないことを確認
		assertThat(questionId, notNullValue());
	}
	
	@Test
	//findByIdメソッドに、登録されたidを渡すと、問題が取得できること
	public void getQuestionWhenRegisterId() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されているidを渡し、問題を取得
		QuestionsBean question = dao.findById(1);
		//id = 1に紐づく問題が取得できていることを確認
		assertThat(question.getQuestion(), is("test"));
	}
	
	@Test
	//findByIdメソッドに、登録されていないidを渡すと、問題が取得できないこと
	public void notGetQuestionWhenNotRegisterId() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されていないidを渡し、問題を取得
		QuestionsBean question = dao.findById(5);
		//問題が取得できていないことを確認
		assertThat(question.getId(), is(0));
	}
	
	@Test
	//deleteメソッドに、登録されているidを渡すと、問題が削除できること
	public void deleteQuestionWhenRegisterId() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//問題を全件取得
		ArrayList<QuestionsBean> queList1 = dao.findAll();
		//登録されているidを渡し、問題を削除
		dao.delete(1);
		//問題を全件取得
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		//DBに登録されているデータが1件減ったことを確認
		assertThat(queList1.size() - queList2.size(), is(1));
		//id = 1に紐づくデータがDBに存在しないことを確認
		for(QuestionsBean que : queList2) { 
			assertThat(que.getId(), is(not(1)));
		}
	}
	
	@Test
	//deleteメソッドに、登録されていないidを渡すと、問題が削除できないこと
	public void notDeleteQuestionWhenNotRegisterId() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//問題を全件取得
		ArrayList<QuestionsBean> queList1 = dao.findAll();
		//登録されていないidを渡して、問題を削除
		dao.delete(5);
		//問題を全件取得
		ArrayList<QuestionsBean> queList2 = dao.findAll();
		//DBに登録されている問題の数が減っていないことを確認
		assertThat(queList1.size() - queList2.size(), is(0));
	}
	
	@Test
	//updateメソッドに、登録されているidを渡すと、問題が更新できること
	public void updateQuestionWhenRegisterId() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されているidと、questionを渡し、問題を更新
		dao.update("testtest", 1);
		//問題を全件取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		//id = 1 に紐づく問題が、更新されていることを確認
		assertThat(queList.get(0).getQuestion(), is("testtest"));
	}
	
	@Test
	//updateメソッドに、登録されていないidを渡すと、問題が更新できないこと
	public void notUpdateQuestionWhenNotRegisterId() throws Exception {
		QuestionsDao dao = new QuestionsDao();
		//登録されていないidと、questionを渡し、問題を更新
		dao.update("testtest", 4);
		//問題を全件取得
		ArrayList<QuestionsBean> queList = dao.findAll();
		//取得したデータからquestionだけ取り出し、リスト化
		ArrayList<String> questions = new ArrayList<>();
		for(int i = 0; i < queList.size(); i++) {
			questions.add(queList.get(i).getQuestion());
		}
		//リストに、更新した内容が含まれていないことを確認
		assertThat(questions, hasItems(not("testtest")));
	}
}
