package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Role;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
/**
 * 角色管理
 * @author loyin
 */
@RouteBind(path = "/role")
@Before({ ManagerPowerInterceptor.class })
public class RoleContraller extends BaseController {
	private static String navTabId="role";
	@Override
	public void index() {
		StringBuffer whee=new StringBuffer(" where 1=1 ");
		List<Object> param=new ArrayList<Object>();
		String name=getPara("name");
		if(name!=null&&!"".equals(name.trim())){
			whee.append(" and name like ?");
			param.add("%"+name+"%");
		}
		setAttr("name", name);
		setAttr("page", Db.paginate(getParaToInt("pageNum", 1),
							getParaToInt("numPerPage", 20),
				"select id,name 名称,remark 备注 ",
				" from  role "+whee.toString(),param.toArray()));
		setAttr("collist", new String[]{"名称","备注"});
		render("index.html");
	}
	public void add() {
		Long id = getParaToLong(0, 0L);
		Role role=new Role();
		if(id!=null&&id!=0){
			role=Role.dao.findById(id);
		}
		setAttr("pojo",role);
		render("add.html");
	}
	public void save() {
		try{
			Role m=getModel(Role.class);
			if(m.getLong("id")!=null){
				m.update();
			}else{
				m.save();
			}
			toDwzJson( 200, "保存成功！", navTabId);
		}catch(Exception e){
			toDwzJson( 300, "保存异常！");
		}
	}
	
	public void del() {
		Role.dao.deleteById(getParaToLong(0, 0L));
		
		toDwzJson( 200, "删除成功！", navTabId);
		
	}
}
