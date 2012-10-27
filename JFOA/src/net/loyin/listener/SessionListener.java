package net.loyin.listener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.loyin.memcache.MemcacheTool;

/**
 * 
 * @author loyin 2012-10-14
 */
public class SessionListener implements HttpSessionListener,
		ServletRequestListener, HttpSessionActivationListener {
	/** 客户端sessionid集合,多个浏览器也是可以同一终端上运行 */
	public static final String clientSetkey = "clientSet";
	/** 终端ip集合 */
	public static final String teSetkey = "teSet";
	public static final Long timelong=43200000L;
	HttpServletRequest request;

	@SuppressWarnings("unchecked")
	@Override
	public void sessionCreated(HttpSessionEvent ev) {
		System.out.print("session创建");
		String sessionid = ev.getSession().getId();
		String ip = request.getRemoteAddr();
		Set<String> clientSet = (Set<String>) MemcacheTool.mcc.get(clientSetkey);
		if (clientSet == null) {
			clientSet = new HashSet<String>();
			MemcacheTool.mcc.set(clientSetkey, clientSet,new Date(new Date().getTime()+timelong));
		}
		clientSet.add(sessionid);
		MemcacheTool.mcc.set(clientSetkey, clientSet,new Date(new Date().getTime()+timelong));
		Set<String> teSet = (Set<String>) MemcacheTool.mcc.get(teSetkey);
		if (teSet == null) {
			teSet = new HashSet<String>();
			MemcacheTool.mcc.set(teSetkey, teSet,new Date(new Date().getTime()+timelong));
		}
		teSet.add(ip);
		MemcacheTool.mcc.set(teSetkey, teSet,new Date(new Date().getTime()+timelong));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent ev) {
		System.out.print("session失效");
		String sessionid = ev.getSession().getId();
		Set<String> clientSet = (Set<String>) MemcacheTool.mcc.get(clientSetkey);
		if (clientSet != null && clientSet.isEmpty() == false) {
			clientSet.remove(sessionid);
			MemcacheTool.mcc.set(clientSetkey, clientSet,new Date(new Date().getTime()+timelong));
		}
		Set<String> teSet = (Set<String>) MemcacheTool.mcc.get(teSetkey);
		if (teSet != null && teSet.isEmpty() == false) {
			String ip = request.getRemoteAddr();
			teSet.remove(ip);
			MemcacheTool.mcc.set(teSetkey, teSet,new Date(new Date().getTime()+timelong));
		}
		MemcacheTool.mcc.delete(sessionid);
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent ev) {

	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent ev) {

	}

	@Override
	public void requestDestroyed(ServletRequestEvent ev) {

	}

	@Override
	public void requestInitialized(ServletRequestEvent ev) {
		request = (HttpServletRequest) ev.getServletRequest();
	}

}
