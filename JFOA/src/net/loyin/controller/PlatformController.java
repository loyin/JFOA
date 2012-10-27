package net.loyin.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.PowerBind;
import net.loyin.jFinal.anatation.RouteBind;

import com.google.gson.Gson;
import com.jfinal.aop.Before;

/***
 * 平台相关设置及管理
 * @author loyin
 * 2012-09-20
 */
@RouteBind(path = "/platform")
@Before({ ManagerPowerInterceptor.class })
@PowerBind
public class PlatformController extends BaseController {

	@Override
	public void index() {

	}
	public static Map<String, Map<String,String>> p = new HashMap<String, Map<String,String>>();
	@SuppressWarnings("unchecked")
	public void set() {
		try {
			String path = this.getClass().getResource("/").getPath().replace("classes/", "platform.txt");
			System.out.println("配置文件："+path.substring(1));
			InputStreamReader isr=new InputStreamReader(new FileInputStream(path.substring(1)), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			String line;
			StringBuffer json=new StringBuffer();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
			reader.close();
			p=new Gson().fromJson(json.toString(),p.getClass());
			List<Map<String,String>>list=new ArrayList<Map<String,String>>();
			for(int i=0;i<p.keySet().size();i++){
				for(String key:p.keySet()){
					Map<String,String> m=p.get(key);
					if(Integer.valueOf(m.get("paixu"))==i){
						list.add(m);
					}
				}
			}
			setAttr("p",list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		
		for(String key:p.keySet()){
			Map<String,String> pp=p.get(key);
			String value=this.getPara(key);
			pp.put("value",value);
			p.put(key, pp);
		}
			Gson gson=new Gson();
			String json=gson.toJson(p);
			String path = this.getClass().getResource("/").getPath().replace("classes/", "platform.txt");
		try{
			OutputStreamWriter w=new OutputStreamWriter(new FileOutputStream(path),"UTF-8");
			w.write(json);
			w.close();
			toDwzJson( 200, "保存成功！");
		}catch(Exception e){
			toDwzJson( 300, "保存失败！");
		}
		
	}
}
