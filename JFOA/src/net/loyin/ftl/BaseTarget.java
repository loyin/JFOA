package net.loyin.ftl;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveModel;

/**引用方法为将此标签类实例设置到request中,然后直接使用<@tage
 * 自定义freemarker 标签
 * @author 刘声凤 2012-9-5 下午9:07:41
 */
public abstract class BaseTarget implements TemplateDirectiveModel {
	public static Logger log = Logger.getLogger(BaseTarget.class);
	public static Configuration freemarker_cfg = new Configuration();
	static{
		try{
			URL url=BaseTarget.class.getResource("/");
			freemarker_cfg.setDirectoryForTemplateLoading(new File(url.getFile().replace("classes", "tagftl")));
			freemarker_cfg.setDefaultEncoding("UTF-8");
		}catch(Exception e){}
	}
}
