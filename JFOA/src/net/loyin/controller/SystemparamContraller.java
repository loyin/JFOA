package net.loyin.controller;

import java.util.ArrayList;
import java.util.List;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Systemparam;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
/**
 * 参数管理
 * @author loyin
 */
@RouteBind(path = "/systemparam")
@Before({ ManagerPowerInterceptor.class })
public class SystemparamContraller extends BaseController {
	private static String navTabId="systemparam";
	@Override
	public void index() {

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
				"select id, name 名称,type 类别,val 值,sortnum 排序号,remark 备注 ",
				" from  systemparam "+whee.toString(),param.toArray()));
		setAttr("collist", new String[]{"类别","名称","值","排序号","备注"});
		render("index.html");
	}
	public void add() {
		Long id = getParaToLong(0, 0L);
		Systemparam systemparam=new Systemparam();
		if(id!=null&&id!=0){
			systemparam=Systemparam.dao.findById(id);
		}
		setAttr("pojo",systemparam);
		render("add.html");
	}
	public void save() {
		
		try{
			Systemparam m=getModel(Systemparam.class);
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
		Systemparam.dao.deleteById(getParaToLong(0, 0L));
		toDwzJson( 200, "删除成功！", navTabId);
	}
}
