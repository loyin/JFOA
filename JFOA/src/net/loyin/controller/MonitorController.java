package net.loyin.controller;

import java.util.Set;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.memcache.MemcacheTool;

import com.jfinal.aop.Before;
/**
 * 系统监控
 * @author loyin
 *
 * 2012-10-14
 */
@RouteBind(path = "/monitor")
@Before({ ManagerPowerInterceptor.class })
public class MonitorController extends BaseController {
	@SuppressWarnings("unused")
	private static String navTabId="monitor";
	@SuppressWarnings("unchecked")
	@Override
	public void index() {
		Set<String> clientSet=(Set<String>)MemcacheTool.mcc.get("clientSet");
		this.setAttr("clientSet",clientSet.size());
		this.setAttr("teSet",MemcacheTool.mcc.get("teSet"));
		render("index.html");
	}

}
