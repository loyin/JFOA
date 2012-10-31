package net.loyin.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.loyin.StaticCfg;
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
	public static Map<String, Map<String,Object>> p =StaticCfg.cfg;
	public void set() {
		try {
			StaticCfg.load();
			p =StaticCfg.cfg;
			List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
			for(int i=0;i<=p.keySet().size();i++){
				for(String key:p.keySet()){
					Map<String,Object> m=p.get(key);
					if(Integer.valueOf((String)m.get("paixu"))==i){
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
			Map<String,Object> pp=p.get(key);
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
			StaticCfg.load();
			toDwzJson( 200, "保存成功！");
		}catch(Exception e){
			toDwzJson( 300, "保存失败！");
		}
		
	}
}
