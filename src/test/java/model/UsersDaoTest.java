package model;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.io.File;

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

import kensyu3.model.QuestionsDao;
import kensyu3.model.UsersBean;
import kensyu3.model.UsersDao;

public class UsersDaoTest {
	
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
	//findByIdメソッドに、登録されていないuserIdを引数として渡した場合、ユーザーが取得できないこと
	public void notGetUser() throws Exception {
		UsersDao dao = new UsersDao();
		//登録されていないidを渡して、ユーザーを取得しようとする
		UsersBean user = dao.findById(4);
		
		//答えが取得できないことを確認
		assertThat(user.getId(), is(0));
		assertNull(user.getPassword());
		assertNull(user.getName());
	}
	
	@Test
	//search_idメソッドに、登録されているuserIdを引数として渡した場合、ユーザーを取得できること
	public void getUser() throws Exception {
		UsersDao dao = new UsersDao();
		//登録されているidを渡して、ユーザーを取得
		UsersBean user = dao.findById(1);
		
		//ユーザーが取得できることを確認
		assertThat(user.getId(), is(1));
		assertThat(user.getName(), is("testuser1"));
		assertThat(user.getPassword(), is("testtest1"));
	}

}
