package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Organization;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
/**
 * 机构管理
 * @author loyin
 */
@RouteBind(path = "/organization")
@Before({ ManagerPowerInterceptor.class })
public class OrganizationContraller extends BaseController {
	private static String navTabId="organization";
	@Override
	public void index() {
		StringBuffer whee=new StringBuffer();
		List<Object> param=new ArrayList<Object>();
		String name=getPara("name");
		if(name!=null&&!"".equals(name.trim())){
			whee.append(" and name like ?");
			param.add("%"+name+"%");
		}
		setAttr("name", name);
		setAttr("page", Db.paginate(getParaToInt("pageNum", 1),
							getParaToInt("numPerPage", 20),
				"select o.id,o.name 名称,o.num 编号,o.addr 地址,o.remark 备注 ",
				" from  organization o where 1=1 "+whee.toString(),param.toArray()));
		setAttr("collist", new String[]{"编号","名称","地址","备注"});
		render("index.html");
	}
	public void add() {
		Long id = getParaToLong(0, 0L);
		Organization organization=new Organization();
		if(id!=null&&id!=0){
			organization=Organization.dao.findById(id);
		}
		setAttr("pojo",organization);
		render("add.html");
	}
	public void save() {
		
		try{
			Organization m=getModel(Organization.class);
			if(m.getLong("id")!=null){
				m.update();
			}else{
				m.save();
			}
			toDwzJson( 200, "保存成功！", navTabId);
		}catch(Exception e){
			toDwzJson( 300,"保存异常！");
		}
		
	}
	
	public void del() {
		Organization.dao.deleteById(getParaToLong(0, 0L));
		toDwzJson( 200,"删除成功！", navTabId);
	}
}
