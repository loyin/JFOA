package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.ftl.OrgSelectTarget;
import net.loyin.ftl.PartmentSelectTarget;
import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.PowerBind;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Employee;
import net.loyin.model.Organization;
import net.loyin.model.Partment;
import net.loyin.model.Role;
import net.loyin.util.safe.MD5;

import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 网站管理用户
 * 
 * @author 刘声凤 2012-9-6 下午9:06:42
 */
@RouteBind(path = "/employee")
@Before({ ManagerPowerInterceptor.class })
public class EmployeeController extends BaseController {
	public static Logger log = Logger.getLogger(EmployeeController.class);
	private static String navTabId = "employee";
	private boolean f;

	public void index() {
		f = true;
		list();
		setAttr("org",Organization.dao.find("select id, name,num from Organization order by id"));
		setAttr("part",Partment.dao.find("select id, name,num from Partment order by id"));
		render("index.html");
	}

	public void list() {
		Long pid = getParaToLong(1, 0L);
		Long orgid = getParaToLong(0, 0L);
		StringBuffer whee = new StringBuffer();
		List<Object> param = new ArrayList<Object>();
		String fullname = getPara("fullname");
		if (fullname != null && !"".equals(fullname.trim())) {
			whee.append(" and fullname like ?");
			param.add("%" + fullname + "%");
		}
		String userno = getPara("userno");
		if (userno != null && !"".equals(userno.trim())) {
			whee.append(" and userno like ?");
			param.add("%" + userno + "%");
		}
		if (pid != 0) {
			whee.append(" and p.id = ?");
			param.add(pid);
		}
		setAttr("fullname", fullname);
		setAttr("userno", userno);
		setAttr("partid", pid);
		if (orgid != 0) {
			whee.append(" and p.orgid = ?");
			param.add(orgid);
		}
		setAttr("orgid", orgid);
		setAttr("pid", pid);
		setAttr("page",
				Db.paginate(
						getParaToInt("pageNum", 1),
						getParaToInt("numPerPage", 20),
						"select e.id,userno 编号,fullname 姓名,e.gender 性别,phone 手机电话,stat 状态 ",
						" from  Employee e join partment p on p.id=e.partmentid "
								+ whee.toString() + " order by e.id ",
						param.toArray()));
		setAttr("collist", new String[] { "编号", "姓名", "性别", "手机电话", "状态" });
		if (f == false)
			render("list.html");
	}

	private static final String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";

	/**
	 * 产生随机数
	 * @return
	 */
	private char[] getCode(int length) {
		char[] rands = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * chars.length());
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	public void add() {
		Employee pojo = new Employee();
		Long id = getParaToLong(2, 0L);
		Long pid = getParaToLong(1, 0L);
		Long orgid = getParaToLong(0, 0L);
		pojo.set("partmentid", pid);
		pojo.set("orgid", orgid);
		if (id != 0) {
			pojo = Employee.dao.findById(id);
		}
		setAttr(OrgSelectTarget.targetName, new OrgSelectTarget());
		setAttr(PartmentSelectTarget.targetName, new PartmentSelectTarget());
		setAttr("pojo", pojo);
		render("add.html");
	}
	public void show() {
		Employee pojo = new Employee();
		Long id = getParaToLong(0, 0L);
		pojo = Employee.dao.findById(id);
		setAttr("part",Partment.dao.findById(pojo.get("partmentid")));
		setAttr("org",Organization.dao.findById(pojo.get("orgid")));
		setAttr("pojo", pojo);
		render("show.html");
	}
	public void save() {
		try {
			Employee m = getModel(Employee.class);
			if (m.getLong("id") != null) {
				m.update();
			} else {
				m.save();
			}
			 Db.update("update employee e set e.orgid=(select p.orgid from partment p where p.id=e.partmentid)");
			toDwzJson(200, "保存成功！", navTabId);
		} catch (Exception e) {
			log.error("保存员工异常", e);
			toDwzJson(300, "保存异常！");
		}
	}

	public void del() {
		Long id = getParaToLong(0, 0L);
		Record po = getAdmin();
		if (id != po.getLong("id")) {
			Employee.dao.deleteById(id);
			toDwzJson(200, "删除成功！", navTabId);
		} else {
			toDwzJson(300, "不能删除自己的账号！");
		}
	}

	public void resetPwd() {
		String pwd = new String(getCode(6));// 随机密码
		Long id = getParaToLong(0, 0L);
		if (id != 0L) {
			Employee.dao.set("id", id).set("pwd", MD5.getMD5ofStr(pwd))
					.update();
		}
		toDwzJson(200, "重置密码成功！密码为<br><h3><b style='color:red;'>" + pwd
				+ "</b></h3><br>请牢记！", navTabId);
	}
	@PowerBind
	public void pwd() {
		render("pwd.html");
	}
	@PowerBind
	public void savepwd() {
		String oldpwd = getPara("oldpwd");
		String pwd = getPara("pwd");
		Record po = getAdmin();
		Employee e = Employee.dao.findById(po.get("id"));
		String pwd1 = e.getStr("pwd");
		if (MD5.getMD5ofStr(oldpwd).equals(pwd1)) {
			e.set("pwd", MD5.getMD5ofStr(pwd));
			e.update();
			toDwzJson(200, "重置密码成功！密码为<br><h3><b style='color:red;'>" + pwd
					+ "</b></h3><br>请牢记！");
		} else {
			toDwzJson(300, "旧密码输入错误！");
		}
	}

	public void role() {
		Long id = getParaToLong(0, 0L);
		setAttr("id", id);
		setAttr("pojo", Employee.dao.findById(id));
		setAttr("urolelist",
				Db.find("select roleid from userrole where userid=?", id));
		setAttr("rolelist", Role.dao.find("select * from role"));
		render("role.html");
	}

	public void saveRole() {
		try {
			Long id = getParaToLong(0, 0L);
			Integer[] roles=this.getParaValuesToInt("roles");
			if(roles!=null&&roles.length>0){
				Db.update("delete from userrole where userid=?",id);
				Integer[][] objs=new Integer[roles.length][1];
				int i=0;
				for(Integer m:roles){
					objs[i++][0]=m;
				}
				Db.batch("insert into userrole (userid,roleid) values("+id+",?)", objs,20);
			}
			toDwzJson(200, "保存成功！");
		} catch (Exception e) {
			toDwzJson(300, "保存异常！");
		}
	}
}
