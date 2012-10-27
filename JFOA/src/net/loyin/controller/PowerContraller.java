package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Employee;
import net.loyin.model.Organization;
import net.loyin.model.Partment;
import net.loyin.model.Role;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 权限设置
 * @author loyin
 */
@RouteBind(path = "/power")
@Before({ ManagerPowerInterceptor.class })
public class PowerContraller extends BaseController {
	private boolean f;
	@Override
	public void index() {
		f=true;
		list();
		setAttr("org",Organization.dao.find("select id, name,num from Organization order by id"));
		setAttr("part",Partment.dao.find("select id, name,num from Partment order by id"));
		setAttr("rolelist",Db.find("select id, name from role order by id"));
		render("index.html");
	}public void list(){
		Long pid = getParaToLong(1, 0L);
		Long orgid = getParaToLong(0, 0L);
		StringBuffer whee=new StringBuffer();
		List<Object> param=new ArrayList<Object>();
		String fullname=getPara("fullname");
		if(fullname!=null&&!"".equals(fullname.trim())){
			whee.append(" and fullname like ?");
			param.add("%"+fullname+"%");
		}
		String userno=getPara("userno");
		if(userno!=null&&!"".equals(userno.trim())){
			whee.append(" and userno like ?");
			param.add("%"+userno+"%");
		}
		if(pid!=0){
			whee.append(" and p.id = ?");
			param.add(pid);
			setAttr("partname",Partment.dao.findById(pid).get("name"));
		}
		setAttr("fullname", fullname);
		setAttr("userno", userno);
		setAttr("partid",pid);
		if(orgid!=0){
			whee.append(" and p.orgid = ?");
			param.add(orgid);
			setAttr("orgname",Organization.dao.findById(orgid).get("name"));
		}
		setAttr("orgid", orgid);
		setAttr("pid", pid);
		
		setAttr("page", Db.paginate(getParaToInt("pageNum", 1),
				getParaToInt("numPerPage", 20),
				"select e.id,userno 编号,fullname 姓名,e.gender 性别,phone 手机电话,stat 状态 ",
				" from  Employee e join partment p on p.id=e.partmentid "+whee.toString()+" order by e.id ",param.toArray()));
		setAttr("collist", new String[]{"编号","姓名","性别","手机电话","状态"});
		if(f==false)
		render("list.html");
	}
	public void add() {
		Long objid=this.getParaToLong(0);
		int c=this.getParaToInt(1,0);
		List<Record> powerlist=Db.find("select menuid from powermenu where objid=? and type=?", objid,c);
		switch(c){
		case 0:
			setAttr("objname",Employee.dao.findById(objid).get("fullname"));
			break;
		case 1:
			setAttr("objname",Role.dao.findById(objid).get("name"));
			break;
		case 2:
			setAttr("objname",Partment.dao.findById(objid).get("name"));
			break;
		case 3:
			setAttr("objname",Organization.dao.findById(objid).get("name"));
			break;
		}
		this.setAttr("list", Db.find("select id,pid,name 名称,type 类别,menulevel 级别,ordernum 排序,icon 图标  from  menu  order by menulevel asc,ordernum asc"));
		this.setAttr("collist", new String[]{"名称","类别","排序","图标"});
		setAttr("powerlist",powerlist);
		setAttr("objid",objid);
		setAttr("type",c);
		render("add.html");
	}
	public void save() {
		try {
			Long objid=this.getParaToLong(0);
			int c=this.getParaToInt(1,0);
			String[] menus=this.getParaValues("menus");
			if(menus!=null&&menus.length>0){
				String[][] objs=new String[menus.length][1];
				int i=0;
				for(String m:menus){
					objs[i++][0]=m;
				}
				Db.update("delete from powermenu where objid=? and type=?",objid,c);
				Db.batch("insert into powermenu (objid,type,menuid) values("+objid+","+c+",?)",objs, 20);
				Db.update("insert into powermenu (objid,type,menuid)"
						+ "select distinct "
						+ objid
						+ ","
						+ c
						+ ",m.id from menu m, menu m1 where m.id=m1.pid and m1.id in(select menuid from powermenu where objid="
						+ objid
						+ " and type="
						+ c
						+ ") and m.id not in(select menuid from powermenu where objid="
						+ objid + " and type=" + c + ")");
			}
			toDwzJson(200, "保存成功！");
		} catch (Exception e) {
			toDwzJson(300, "保存异常！");
		}
	}
}
