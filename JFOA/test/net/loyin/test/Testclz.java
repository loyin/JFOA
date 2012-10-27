package net.loyin.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Testclz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
//		URL url=Testclz.class.getResource("/");
//		System.out.println(url.toString());
//		System.out.println(MD5.getMD5ofStr("123456").toLowerCase());
		Process p= Runtime.getRuntime().exec("ipconfig");
		BufferedReader reader=new BufferedReader(new InputStreamReader( p.getInputStream(),"GBK"));
			String s;
			while((s=reader.readLine())!=null){
				System.out.println(s);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
