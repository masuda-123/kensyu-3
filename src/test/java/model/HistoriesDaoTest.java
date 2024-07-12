package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

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

import kensyu3.model.HistoriesBean;
import kensyu3.model.HistoriesDao;
import kensyu3.model.QuestionsDao;

public class HistoriesDaoTest {
	
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
	//findByUserIdメソッドに、登録されていないuser_idを渡すと、履歴が取得できないこと
	public void notGetHistoryWhenNotRegisterUserId() throws Exception {
		HistoriesDao dao = new HistoriesDao();
		//登録されていないuser_idを渡し、履歴を取得
		ArrayList<HistoriesBean> hisList = dao.findByUserId(3);
		//履歴が取得できていないことを確認
		assertTrue(hisList.isEmpty());
	}

	@Test
	//findByUserIdメソッドに、登録されているuser_idを渡すと、履歴が作成日順に取得できること")
	public void getHistoryWhenRegisterUserId() throws Exception {
		HistoriesDao dao = new HistoriesDao();
		//登録されているuser_idを渡し、履歴を取得
		ArrayList<HistoriesBean> hisList = dao.findByUserId(1);
		//DBにあるuser_id = 1と紐づくデータと、取得したデータが一致しているか確認
		assertThat(hisList.get(0).getPoint(), is(10));
		assertThat(hisList.get(1).getPoint(), is(20));
	}
	
	@Test
	//registerメソッドで、履歴が登録できること
	public void registerHistory() throws Exception {
		HistoriesDao dao = new HistoriesDao();
		//user_id = 1と一致するデータを取得
		ArrayList<HistoriesBean> hisList1 = dao.findByUserId(1);
		//user_idと、pointを渡して、履歴を登録
		dao.register(1, 50);
		//user_id = 1と一致するデータを取得
		ArrayList<HistoriesBean> hisList2 = dao.findByUserId(1);
		//user_id = 1と紐づくデータが、1件増えたことを確認
		assertThat(hisList2.size() - hisList1.size(), is(1));
		//user_id = 1と紐づく最新のデータが、登録したデータと一致することを確認
		assertThat(hisList2.get(2).getPoint(), is(50));
	}

}
