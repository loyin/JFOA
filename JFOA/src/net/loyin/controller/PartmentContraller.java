package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.ftl.EmployeeSelectTarget;
import net.loyin.ftl.OrgSelectTarget;
import net.loyin.ftl.PartmentSelectTarget;
import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Partment;

import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
/**
 * 部门管理
 * @author loyin
 */
@RouteBind(path = "/partment")
@Before({ ManagerPowerInterceptor.class })
public class PartmentContraller extends BaseController {
	public static Logger log=Logger.getLogger(PartmentContraller.class);
	private static String navTabId="partment";
	@Override
	public void index() {
		
		StringBuffer whee=new StringBuffer();
		List<Object> param=new ArrayList<Object>();
		String name=getPara("name");
		if(name!=null&&!"".equals(name.trim())){
			whee.append(" and p.name like ?");
			param.add("%"+name+"%");
		}
		setAttr("name", name);
		String orgid=getPara("orgid");
		if(orgid!=null&&!"".equals(orgid.trim())){
			whee.append(" and p.orgid = ?");
			param.add(Long.parseLong(orgid));
		}
		setAttr("orgid", orgid);
		
		String pid=getPara("pid");
		if(pid!=null&&!"".equals(pid.trim())){
			whee.append(" and p.pid = ?");
			param.add(Long.parseLong(pid));
		}
		setAttr("pid", pid);
		setAttr(OrgSelectTarget.targetName,new OrgSelectTarget());
		setAttr(PartmentSelectTarget.targetName,new PartmentSelectTarget());
		setAttr("page", Db.paginate(getParaToInt("pageNum", 1),getParaToInt("numPerPage", 20),
				"select p.id,concat(o.name,'[',o.num,']') 所属机构, concat(p.name,'[',p.num,']') 部门名称,e.fullname 部门主管,p.remark 备注 ",
				" from  partment p  join organization o on o.id=p.orgid join employee e on e.id=p.picid "+whee.toString(),param.toArray()));
		setAttr("collist", new String[]{"所属机构","部门名称","部门主管","备注"});
		render("index.html");
	}
	public void add() {
		Long id = getParaToLong(0, 0L);
		Long pid = getParaToLong(1, 0L);
		Partment partment=new Partment();
		if(id!=null&&id!=0){
			partment=Partment.dao.findById(id);
		}
		partment.set("pid",pid);
		setAttr("pojo",partment);
		setAttr(OrgSelectTarget.targetName,new OrgSelectTarget());
		setAttr(PartmentSelectTarget.targetName,new PartmentSelectTarget());
		setAttr(EmployeeSelectTarget.targetName,new EmployeeSelectTarget());
		render("add.html");
	}
	public void save() {
		try{
			Partment m=getModel(Partment.class);
			if(m.getLong("id")!=null){
				m.update();
			}else{
				m.save();
			}
			toDwzJson( 200, "保存成功！", navTabId);
		}catch(Exception e){
			log.error("保存部门异常",e);
			toDwzJson(300, "保存异常！");
		}
	}
	
	public void del() {
		Partment.dao.deleteById(getParaToLong(0, 0L));
		toDwzJson( 200, "删除成功！", navTabId);
	}
}
