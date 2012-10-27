package net.loyin.controller;

import java.util.HashMap;
import java.util.Map;

import net.loyin.memcache.MemcacheTool;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

/**
 * 基础Controller
 * @author 刘声凤
 *  2012-9-3 下午10:37:28
 */
public abstract class BaseController extends Controller {
	protected int pageSize=20;
	public static Gson gson=new Gson();
	public abstract void index();
	@Override
	public void render(String view) {
		this.setAttr("root","");
		super.render(view);
	}
	public Record getAdmin(){
		return (Record)MemcacheTool.mcc.get(getSession().getId());
	}
	/**
	 * 转换dwz json格式输出
	 * @param statusCode
	 * @param message
	 * @param navTabId
	 * @return
	 */
	public void toDwzJson(Integer statusCode,String message,String... navTabId){
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("statusCode", statusCode);
		if(message!=null)
		jsonMap.put("message",message);
		if(navTabId!=null)
		jsonMap.put("navTabId", navTabId);
		this.renderText(gson.toJson(jsonMap));
	}
}
