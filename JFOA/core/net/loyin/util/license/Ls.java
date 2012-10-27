package net.loyin.util.license;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.sun.org.apache.xml.internal.security.utils.Base64;
/***
 * license验证
 * 4abcd+加密后的字符串
 * abcd为key 4表示截取除4外的前面4个字符为key
 * @author loyin
 * 2012-10-2
 */
public class Ls {
	public static String so;
	static {
		try {
			String path=Ls.class.getResource("/").getFile();
			StringBuffer cmd = new StringBuffer();
			String f = Ls.class.getResource("").getFile();
			cmd.append(f);
			cmd.append("c");
			Process p = Runtime.getRuntime().exec(cmd.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
			so = reader.readLine();
			reader.close();
			File file=new File(path.replace("WEB-INF/classes/","")+so);
			if(file.exists()==false){
				/**将机器码放置到根目录*/
				file.createNewFile();
			}
			BufferedReader fr=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			StringBuffer licensestr=new StringBuffer();
			String lstr=null;
			while((lstr=fr.readLine())!=null){
				licensestr.append(lstr);
			}
			fr.close();
			String temp=new String(Base64.decode(licensestr.toString()));
			int li=Integer.parseInt(temp.substring(0,1));
			String key=temp.substring(1,li+1);
			if(!licensestr.toString().equals(cd(so,key).replaceAll("\\n", ""))){
				System.exit(0);
			}
		} catch (Exception e) {
			System.exit(0);
		}
	}
	/**
	 * 创建序列号
	 * @param so 机器码
	 * @param key
	 * @return
	 */
	private static String cd(String so,String key){
		int tsize=key.length();
		char[] cs=so.toCharArray();
		StringBuffer ss=new StringBuffer();
		for(int j=0;j<=tsize;j++){
			int i=0;
			for(char c:cs){
				ss.append(c);
				i++;
				ss.append(key.charAt(i%tsize));
			}
		}
		String t=tsize+key+"kljsaldjflag34是ol阿三维      lla9230!@4)5&*"+Base64.encode((tsize+"fdsa2309-=12=+~速度5&*"+key+Base64.encode(ss.toString().getBytes())).getBytes());
		return Base64.encode(t.getBytes());
	}
	public static void main(String[] args){
		System.out.print(Ls.cd("EMKEMEMM333736C8I3133423","s是2哦啊#8%"));
	}
}
