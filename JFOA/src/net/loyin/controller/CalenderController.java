package net.loyin.controller;

import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.PowerBind;
import net.loyin.jFinal.anatation.RouteBind;

import com.jfinal.aop.Before;

@RouteBind(path = "/calender")
@PowerBind
@Before({ ManagerPowerInterceptor.class })
public class CalenderController extends BaseController {

	@Override
	public void index() {
	}
	public void frm() {
		String lang=this.getPara("lang", "zh_CN");
		this.setAttr("lang",lang);
	}
	public void add(){}
	public void up(){}
	public void del(){}
	public void get(){}
}
