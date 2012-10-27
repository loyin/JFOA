package net.loyin.ftl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import net.loyin.model.Organization;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 系统参数选择
 * @author loyin
 * 2012-9-28
 */
public class SysParaSelectTarget extends BaseTarget {
	/**orgSelect*/
	public static String targetName="sysParaSelect";
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map args, TemplateModel[] arg2,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Organization> list= Organization.dao.find("select id,name,val from systemparam order by sortnum asc");
		Writer out= env.getOut();
		for(Organization o:list){
			out.append("<option value='"+o.getStr("val")+"'>");
			out.append(o.getStr("name")+"["+o.getStr("val")+"]");
			out.append("</option>");
		}
	}
}
