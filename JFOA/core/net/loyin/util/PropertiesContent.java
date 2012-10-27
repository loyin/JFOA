package net.loyin.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import net.loyin.util.safe.CipherUtil;

import org.apache.log4j.Logger;

/**
 * 配置文件config.properties所有配置
 * 
 * @author 刘声凤 2012-6-21 下午2:05:06
 */
public class PropertiesContent {
	public static Logger log = Logger.getLogger(PropertiesContent.class);
	public static Map<String, Object> config = new HashMap<String, Object>();
	public static Properties properties=new Properties();
	static {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		Enumeration<String> cfgs = rb.getKeys();
		while (cfgs.hasMoreElements()) {
			String key = cfgs.nextElement();
			String val=rb.getString(key);
			if (key.contains("mail.password")==true) {
				try {
					config.put(key, CipherUtil.decryptData(val));
				} catch (Exception e) {
					log.error("对参数解密异常",e);
				}
			}else{
				config.put(key,val);
			}
		}
		 properties.putAll(config);
	}
	public static String get(String key){
		return (String)config.get(key);
	}
	public static Object getObj(String key){
		return config.get(key);
	}
	public static Boolean getToBool(String key,Boolean def){
		try{
			return Boolean.valueOf((String)config.get(key));
		}catch(Exception e){
			return def;
		}
	}
	public static Integer getToInteger(String key,Integer def){
		try{
			return Integer.valueOf((String)config.get(key));
		}catch(Exception e){
			return def;
		}
	}
	public static Long getToLong(String key,Long def){
		try{
			return Long.valueOf((String)config.get(key));
		}catch(Exception e){
			return def;
		}
	}
}
