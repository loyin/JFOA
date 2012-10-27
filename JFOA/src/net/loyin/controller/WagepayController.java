package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Organization;
import net.loyin.model.Partment;
import net.loyin.model.Wagepay;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;

/**
 * 员工工资发放
 * @author loyin
 * 2012-10-1
 */
@RouteBind(path = "/wagepay")
@Before({ ManagerPowerInterceptor.class })
public class WagepayController extends BaseController {
	private static String navTabId="wagepay";
	private boolean f;
	public void index() {
		f = true;
		list();
		setAttr("org",Organization.dao.find("select id, name,num from Organization order by id"));
		setAttr("part",Partment.dao.find("select id, name,num from Partment order by id"));
		render("index.html");
	}

	public void list() {
		StringBuffer whee=new StringBuffer(" where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		String name=getPara("name");
		if(name!=null&&!"".equals(name.trim())){
			whee.append(" and name like ?");
			param.add("%"+name+"%");
		}
		String type=getPara("type");
		if(type!=null&&!"".equals(type.trim())){
			whee.append(" and type = ?");
			param.add("%"+type+"%");
		}
		setAttr("type", type);
		setAttr("name", name);
		setAttr("page", Db.paginate(getParaToInt("pageNum", 1),
							getParaToInt("numPerPage", 20),
				"select w.id,concat(e.fullname,'[',e.userno,']') 员工,w.amount 实发工资金额（元）,concat(w.sdate,'至',w.edate) 工资结算日期,w.paydate 发放日期,concat(e1.fullname,'[',e1.userno,']') 操作员 ",
				" from  wagepay w join employee e join employee e1 on w.empid=e.id and e1.id=w.operater "+whee.toString()+" order by w.id desc",param.toArray()));
		setAttr("collist", new String[]{"员工","实发工资金额（元）","工资结算日期","发放日期","操作员 "});
		if (f == false)
			render("list.html");
	}
	public void add() {
		Long id = getParaToLong(0, 0L);
		Wagepay systemparam=new Wagepay();
		if(id!=null&&id!=0){
			systemparam=Wagepay.dao.findById(id);
		}
		setAttr("pojo",systemparam);
		render("add.html");
	}
	public void save() {
		
		try{
			Wagepay m=getModel(Wagepay.class);
			if(m.getLong("id")!=null){
				m.update();
			}else{
				m.save();
			}
			toDwzJson(200, "保存成功！", navTabId);
		}catch(Exception e){
			toDwzJson( 300, "保存异常！");
		}
		
	}
	
	public void del() {
		Wagepay.dao.deleteById(getParaToLong(0, 0L));
		toDwzJson( 200, "删除成功！", navTabId);
	}
}
