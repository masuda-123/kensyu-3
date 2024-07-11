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

import kensyu3.model.AnswersBean;
import kensyu3.model.AnswersDao;
import kensyu3.model.QuestionsDao;

public class AnswersDaoTest {
	
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
	//findAllメソッドで、答えが全件取得できること
	public void getAnswerAll() throws Exception {
		AnswersDao dao = new AnswersDao();
		//答えを全件取得
		ArrayList<AnswersBean> ansList = dao.findAll();
		//DBに登録されているデータと、取得したデータが一致しているか確認
		assertThat(ansList.get(0).getAnswer(), is("test"));
		assertThat(ansList.get(1).getAnswer(), is("test2"));
		assertThat(ansList.get(2).getAnswer(), is("test3"));
	}
	
	@Test
	//findByQuestionIdメソッドに、登録されたquestions_idを渡すと、答えが取得できること
	public void getAnswerWhenRegisterQuestionId() throws Exception {
		AnswersDao dao = new AnswersDao();
		//登録されているquestions_idを渡し、一致するレコードを取得
		ArrayList<AnswersBean> ansList = dao.findByQuestionId(1);
		//DBにあるquestions_id = 1と紐づくデータと、取得したデータが一致しているか確認
		assertThat(ansList.get(0).getAnswer(), is("test"));
		assertThat(ansList.get(1).getAnswer(), is("test2"));
	}
	
	@Test
	//findByQuestionIdメソッドに、登録されていないquestions_idを渡すと、答えが取得できないこと
	public void notGetAnswerWhenRegisterNotQuestionId() throws Exception {
		AnswersDao dao = new AnswersDao();
		//登録されていないquestions_idを渡し、一致するレコードを取得
		ArrayList<AnswersBean> ansList = dao.findByQuestionId(2);
		//データが取得できていないことを確認
		assertThat(ansList.isEmpty(), is(true));
	}
	
	@Test
	//registerメソッドで、questionIdを渡すと、答えが登録できること
	public void registerAnswerWhenQuestionId() throws Exception {
		AnswersDao dao = new AnswersDao();
		//答えを全件取得
		ArrayList<AnswersBean> ansList1 = dao.findAll();
		//answerとquestions_idを渡し、答えを登録
		String[] answer = {"testtest"};
		dao.register(1, answer);
		//答えを全件取得
		ArrayList<AnswersBean> ansList2 = dao.findAll();
		//DBに登録されている答えが1件増えたことを確認
		assertThat(ansList2.size() - ansList1.size(), is(1));
		//最新の答えが、登録した答えと一致しているか確認
		assertThat(ansList2.get(3).getAnswer(), is(answer[0]));
	}
	
	@Test
	//deleteByQuestionIdメソッドに、登録されていないquestions_idを渡すと、答えが削除できないこと
	public void notDeleteAnswerWhenNotRegisterQuestionId() throws Exception {
		AnswersDao dao = new AnswersDao();
		//答えを全件取得
		ArrayList<AnswersBean> ansList1 = dao.findAll();
		//登録されていないquestions_idを渡し、答えを削除
		dao.delete(2);
		//答えを全件取得
		ArrayList<AnswersBean> ansList2 = dao.findAll();
		//DBに登録されている答えが減っていないことを確認
		assertThat(ansList1.size() - ansList2.size(), is(0));
	}
}
