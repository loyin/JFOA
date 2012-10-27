package net.loyin.jFinal.plugin;

import java.util.List;

import net.loyin.controller.BaseController;
import net.loyin.jFinal.anatation.RouteBind;

import com.jfinal.config.Routes;

/**
 * Routes 工具类 自动绑定Controller
 * @author 刘声凤
 *  2012-9-4 下午12:35:55
 */
public class MyRoutesUtil{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void add(Routes me){
		List<Class> list= ClassSearcher.findClasses();
		if(list!=null&&list.isEmpty()==false){
			for(Class clz:list){
				RouteBind rb=(RouteBind)clz.getAnnotation(RouteBind.class);
				if(rb!=null){
					me.add(rb.path(),clz);
				}else{
					if(clz.getSuperclass()==BaseController.class){
						System.out.println(clz.getSimpleName());
						me.add("/"+clz.getName().replace("Controller", "").toLowerCase(),clz);
					}
				}
			}
		}
	}
}
