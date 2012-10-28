package net.loyin.controller;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.model.Menu;

import com.jfinal.aop.Before;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Db;
/**
 * 菜单管理
 * @author loyin
 */
@RouteBind(path = "/menu")
@Before({ ManagerPowerInterceptor.class })
public class MenuContraller extends BaseController {
	public static Logger log=Logger.getLogger(MenuContraller.class);
	private static String navTabId="menu";
	@Override
	public void index() {
		this.setAttr("list", Db.find("select id,pid,name 名称,url 链接,type 类别,menulevel 级别,ordernum 排序,safecode 安全码 ,icon 图标,enable 状态  from  menu  order by ordernum asc,menulevel asc"));
		this.setAttr("collist", new String[]{"名称","类别","级别","链接","排序","安全码","图标","状态"});
		render("index.html");
	}
	public void add() {
		Long id = this.getParaToLong(0, 0L);
		Menu menu=new Menu();
		if(id!=null&&id!=0){
			menu=Menu.dao.findById(id);
		}
		setAttr("pojo",menu);
		render("add.html");
	}
	public void child(){
		Menu pojo=new Menu();
		pojo.set("pid", getParaToLong(0, 0L));
		setAttr("pojo",pojo);
		render("add.html");
	}

	public void save() {
		try{
			Menu m=getModel(Menu.class);
			if(m.getLong("id")!=null){
				m.update();
			}else{
				m.save();
			}
			toDwzJson( 200, "保存成功！", navTabId);
		}catch(Exception e){
			log.error("保存菜单异常",e);
			toDwzJson( 300, "保存异常！");
		}
	}
	
	public void del() {
		Menu.dao.deleteById(this.getParaToLong(0, 0L));
		toDwzJson( 200, "删除成功！", navTabId);
	}
	public void move() {
		int i=this.getParaToInt(0, 0);
		Long id=this.getParaToLong(1, 0L);
		Db.update("update menu set ordernum=(case when ordernum >=1 then ordernum+? else 1 end) where id=?",i==0?-1:1,id);
		toDwzJson( 200, "移动成功！", navTabId);
	}
	public void enable(){
		Long id=this.getParaToLong(0, 0L);
		Db.update("update menu set enable= (case when enable=1 then  0 else 1 end) where id=?",id);
		toDwzJson( 200, "操作成功！", navTabId);
	}
}
