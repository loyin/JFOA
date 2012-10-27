package net.loyin.ftl;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import net.loyin.model.Employee;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 员工选择
 * @author loyin
 * 2012-9-28
 */
public class EmployeeSelectTarget extends BaseTarget {
	/**empSelect*/
	public static String targetName="empSelect";
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map args, TemplateModel[] arg2,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Employee> list= Employee.dao.find("select id,fullname,userno from employee ");
		Writer out= env.getOut();
		for(Employee o:list){
			out.append("<option value='"+o.getLong("id")+"'>");
			out.append(o.getStr("fullname")+"["+o.getStr("userno")+"]");
			out.append("</option>");
		}
	}
}
