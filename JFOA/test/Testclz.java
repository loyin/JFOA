
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class Testclz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		URL url=Testclz.class.getResource("/");
//		System.out.println(url.toString());
//		System.out.println(MD5.getMD5ofStr("123456").toLowerCase());
		/*
		try{
		Process p= Runtime.getRuntime().exec("ipconfig");
		BufferedReader reader=new BufferedReader(new InputStreamReader( p.getInputStream(),"GBK"));
			String s;
			while((s=reader.readLine())!=null){
				System.out.println(s);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}*/
		String classpath=Testclz.class.getResource("").getPath();
		String libDir=classpath.replace("classes", "lib");
		File libDirf=new File(libDir);
		StringBuffer cmd=new StringBuffer("rhc java -classpath \"");
		for(String f:libDirf.list()){
			if(f.endsWith(".jar")){
				cmd.append("../lib/");
				cmd.append(f);
				cmd.append(";");
			}
		}
		cmd.append("\" Server");
		try{
			OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(classpath+"/run.bat"),"UTF-8");
			out.write(cmd.toString());
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
