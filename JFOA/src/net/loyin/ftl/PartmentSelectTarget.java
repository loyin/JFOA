package net.loyin.ftl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import net.loyin.model.Organization;
import net.loyin.model.Partment;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 组织机构选择
 * @author loyin
 * 2012-9-28
 */
public class PartmentSelectTarget extends BaseTarget {
	/**partSelect*/
	public static String targetName="partSelect";
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map args, TemplateModel[] arg2,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Organization> orglist=Organization.dao.find("select id,name,num from Organization");
		List<Partment> list= Partment.dao.find("select id,name,num,orgid from Partment");
		Writer out= env.getOut();
		for(Organization org:orglist){
			out.append("<optgroup label='"+org.getStr("name")+"["+org.getStr("num")+"]'>");
			for(Partment o:list){
				if(org.getLong("id")==o.getLong("orgid")){
					out.append("<option value='"+o.getLong("id")+"'>");
					out.append(o.getStr("name")+"["+o.getStr("num")+"]");
					out.append("</option>");
				}
			}
			out.append("</optgroup>");
		}
	}
}
