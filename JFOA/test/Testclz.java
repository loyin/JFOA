
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;


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
		/*String classpath=Testclz.class.getResource("").getPath();
		String libDir=classpath.replace("classes", "lib");
		File libDirf=new File(libDir);
		StringBuffer cmd=new StringBuffer("java -classpath \"");
		for(String f:libDirf.list()){
			if(f.endsWith(".jar")){
				cmd.append("../lib/");
				cmd.append(f);
				cmd.append(";");
			}
		}
		cmd.append("\" Server");
		System.out.print(cmd);
		while(true){
			try{
				Thread th=new Thread();
				th.sleep(99999L);
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
		
		Gson gson=new Gson();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("webname", "sfdafa");
		map.put("validcode",false);
		String jsonstr=gson.toJson(map);
		System.out.println(jsonstr);
		Map<String,Object> map2= gson.fromJson(jsonstr, map.getClass());
		System.out.println("=================");
		System.out.println(gson.toJson(map2));
		
	}

}
