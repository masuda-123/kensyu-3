package model;

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

public class QuestionsDaoTest {
	/** ロガー */
	private Logger logger = LoggerFactory.getLogger(QuestionsDao.class);
	/** DBUnitのテスター */
	private static IDatabaseTester databaseTester;
	
	/**
	 * [前処理]<br>
	 * DBに事前データを準備する。<br>
	 * <br>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger.info("前処理開始");
		
		// --------------------------------------
		// 事前準備データのINSERT
		// --------------------------------------
		// 事前準備データのINSERTにはスキーマも合わせて指定する
		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/mydatabase?ServerTimezone=JST&allowPublicKeyRetrieval=true&useSSL=false", "root", "masuda99");
		// --------------------------------------
		// テストデータ投入
		// --------------------------------------
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/java/dbUnit/testData.xml"));
		databaseTester.setDataSet(dataSet);
		// DELETE→INSERTで事前準備データを用意する
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.onSetup();
		logger.info("前処理終了");
	}
	
	/**
	 * [後処理]<br>
	 * テスト後の後処理を行う。<br>
	 * DBUnitの後片付けを行う。<br>
	 * <br>
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		databaseTester.onTearDown();
	}
	
	@Test
	public void test() {
		assertNotEquals(2, 3);
	}

}
