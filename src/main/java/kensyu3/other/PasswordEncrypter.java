package kensyu3.other;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncrypter {
	// 暗号化＆復号化で使用する鍵
	private static final String ENCRYPT_KEY = "0123012301230123";
	// 初期ベクトル
	private static final String INIT_VECTOR= "1234567890123456";
	
	//パスワードの暗号化
	public String encypt(String password){
		String encryptedPassword = null;
		try {
			// 暗号化キーと初期化ベクトルをバイト配列へ変換し、オブジェクト生成
			SecretKeySpec key = new SecretKeySpec(ENCRYPT_KEY.getBytes("UTF-8"), "AES");
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			// Cipherオブジェクト生成
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// Cipherオブジェクトの初期化
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			// パスワードをバイトに変換し、暗号化する
			byte[] byteResult = cipher.doFinal(password.toString().getBytes("UTF-8"));
			// Base64でエンコード
			encryptedPassword = Base64.getEncoder().encodeToString(byteResult);
		} catch (Exception e) {
			
		}
		return encryptedPassword;
	}
	
	//復号化
	public String decrypt(String password) {
		String decryptedPassword = null;
		try {
			// 暗号化キーと初期化ベクトルのオブジェクト生成
			SecretKeySpec key = new SecretKeySpec(ENCRYPT_KEY.getBytes("UTF-8"), "AES");
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
			// Cipherオブジェクト生成
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// Cipherオブジェクトの初期化
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			// Base64でデコードして、復号化する
			byte[] byteResult = cipher.doFinal(Base64.getDecoder().decode(password));
			// バイト配列を文字列へ変換
			decryptedPassword = new String(byteResult, "UTF-8");
		} catch (Exception e) {
			
		}
		return decryptedPassword;
	}

}
