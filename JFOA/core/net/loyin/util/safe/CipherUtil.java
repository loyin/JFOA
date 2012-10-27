package net.loyin.util.safe;

import java.util.ResourceBundle;


public class CipherUtil {
	public static String key;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("config");
		key=rb.getString("webkey");
	}
	/**
	 * @param decryptdata
	 *            要解密的暗码
	 * @param decryptkey
	 *            解密的密钥
	 * @return 解密后的明码
	 * @throws Exception
	 */
	public static String decryptData(String decryptdata, String decryptkey)
			throws Exception {
		DESPlus desPlus = new DESPlus(decryptkey);
		return desPlus.decrypt(decryptdata);
	}
	public static String decryptData(String str) throws Exception {
		return decryptData(str,key);
	}
	/**
	 * @param encryptdata
	 *            要解密的暗码
	 * @param decryptkey
	 *            解密的密钥
	 * @return 解密后的明码
	 * @throws Exception
	 */
	public static String encryptData(String encryptdata, String decryptkey)
			throws Exception {
		DESPlus desPlus = new DESPlus(decryptkey);
		return desPlus.encrypt(encryptdata);
	}
	public static String encryptData(String encryptdata)
			throws Exception {
		return encryptData(encryptdata,key);
	}
	public static void main(String[] args) throws Exception{
		String key="loyin";
		System.out.println(CipherUtil.encryptData("",key));
	}
	
}
