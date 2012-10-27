package net.loyin.jFinal.plugin.sqlXml;

import com.jfinal.plugin.IPlugin;
/**
 *绝望的八皮 
 * @author 绝望的八皮
 * 2012-9-29
 */
public class SqlInXmlPlugin implements IPlugin {
	@Override
	public boolean start() {
		try {
			SqlManager.parseSqlXml();
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return true;
	}
	@Override
	public boolean stop() {
		SqlManager.clearSqlMap();
		return true;
	}
}
