package net.loyin.memcache;

import net.loyin.util.PropertiesContent;

import com.meetup.memcached.MemcachedClient;
import com.meetup.memcached.SockIOPool;

public class MemcacheTool {
	public static MemcachedClient mcc;
	static {
		String[] servers ;
		String c= PropertiesContent.get("memcacheserver");
		if (c == null||"".equals(c)) {
			servers = new String[] {"127.0.0.1:11211"};
		}else{
			servers=c.split(";");
		}
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);
		pool.setFailover(true);
		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
		mcc = new MemcachedClient();
	}
}
