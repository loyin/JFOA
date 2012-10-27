package net.loyin.config;

import javax.servlet.http.HttpServletRequest;

import net.loyin.handler.SessionHandler;
import net.loyin.jFinal.plugin.AutoTableBindPlugin;
import net.loyin.jFinal.plugin.MyRoutesUtil;
import net.loyin.jFinal.plugin.TableNameStyle;
import net.loyin.jFinal.plugin.sqlXml.SqlInXmlPlugin;
import net.loyin.memcache.MemcacheTool;
import net.loyin.util.PropertiesContent;

import com.alibaba.druid.filter.stat.StatFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
/**
 * API引导式配置
 */
public class JFWebConfig extends JFinalConfig {
	static{
	}
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
//		loadPropertyFile("a_little_config.txt");				// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(true);
//		me.setViewType(ViewType.JSP); 							// 设置视图类型为Jsp，否则默认为FreeMarker
		me.setError404View("/404.html");
		me.setError500View("/500.html");
		me.setBaseViewPath("/WEB-INF/pages/");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		MyRoutesUtil.add(me);
		MemcacheTool.mcc.flushAll();//刷新所有的memcache
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropertiesContent.get("jdbc.url"),
												  PropertiesContent.get("jdbc.username"),
												  PropertiesContent.get("jdbc.password"));
		/*WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		druidPlugin.addFilter(wall);*/
		druidPlugin.addFilter(new StatFilter());
		me.add(druidPlugin);
		// 配置ActiveRecord插件
		/**
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		me.add(arp);
		//数据库表与bean映射
		arp.addMapping("acategory", Acategory.class);	// 映射acategory 表到 Acategory模型
		*/
		
		//添加自动绑定model与表插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, TableNameStyle.LOWER);
		autoTableBindPlugin.setShowSql(true);
		me.add(autoTableBindPlugin);
		me.add(new SqlInXmlPlugin());
		
//		me.add(new SpringPlugin());//添加spring插件自动从WEB-INF下寻找application.xml
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
//		me.add(new SessionInViewInterceptor());//解决session在freemarker中不能取得的问题 获取方法：${session["manager"].username}
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new SessionHandler());//将session里的参数传递到request中直接得到
		//访问路径是/admin/monitor
		DruidStatViewHandler dvh =  new DruidStatViewHandler("/admin/monitor", new IDruidStatViewAuth() {
			public boolean isPermitted(HttpServletRequest request) {//获得查看权限
//				HttpSession hs = request.getSession(false);
//				return (hs != null && hs.getAttribute("$admin$") != null);
				return true;
			}
		});
		me.add(dvh);
	}
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	/*public static void main(String[] args) {
		
	}*/
}
