package net.loyin.activiti;

import net.loyin.util.PropertiesContent;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * activiti服务类
 * 
 * @author loyin
 *
 * 2012-10-23
 */
public class ActivitiServer {
	public static StandaloneProcessEngineConfiguration sprocesscfg = new StandaloneProcessEngineConfiguration();
	public static ProcessEngine processEngine;
	static {
		try {
			Thread.sleep(500);
			sprocesscfg.setJdbcDriver(PropertiesContent.get("jdbc.driver"));
			sprocesscfg.setJdbcPassword(PropertiesContent.get("jdbc.password"));
			sprocesscfg.setJdbcUrl(PropertiesContent.get("jdbc.url"));
			sprocesscfg.setJdbcUsername(PropertiesContent.get("jdbc.username"));
			sprocesscfg.setDatabaseSchemaUpdate("true");
			sprocesscfg.setDatabaseType(PropertiesContent.get("jdbc.dbType"));
			sprocesscfg.setJobExecutorActivate(false);
			processEngine=ActivitiServer.sprocesscfg.buildProcessEngine();//触发执行相关表的创建
//			processEngine.getRepositoryService().createDeployment().addZipInputStream(
//					new ZipInputStream(new FileInputStream("E:\\work\\BIP\\resource\\leave.zip"))).deploy();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
