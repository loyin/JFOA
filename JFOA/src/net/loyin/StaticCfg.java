package net.loyin;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 静态参数类
 * @author 龙影 loyin
 * 2012-10-30
 */
public class StaticCfg {
public static Map<String, Map<String,Object>> cfg=new HashMap<String, Map<String,Object>>();
 static{
	 load();
 }
 public static void load(){
	 try{
		 	String path = StaticCfg.class.getResource("/").getPath().replace("classes/", "platform.txt");
			InputStreamReader isr=new InputStreamReader(new FileInputStream(path.substring(1)), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			String line;
			StringBuffer json=new StringBuffer();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			reader.close();
			cfg=new Gson().fromJson(json.toString(),cfg.getClass());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
 }
 public static Map<String,Object> get(String key){
	 return cfg.get(key);
 }
}
