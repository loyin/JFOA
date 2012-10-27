package net.loyin.controller;

import java.util.List;

import net.loyin.activiti.ActivitiServer;
import net.loyin.interceptor.ManagerPowerInterceptor;
import net.loyin.jFinal.anatation.PowerBind;
import net.loyin.jFinal.anatation.RouteBind;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;

/**
 * activiti相关
 * @author loyin
 *
 * 2012-10-23
 */
@RouteBind(path="/activiti")
@Before({ ManagerPowerInterceptor.class })
public class ActivitiController extends BaseController {
	@PowerBind
	@Override
	public void index() {
		ProcessDefinitionQuery query=ActivitiServer.processEngine.getRepositoryService().createProcessDefinitionQuery();
		List<ProcessDefinition> list=query.listPage(getParaToInt("pageNum", 1),
				getParaToInt("numPerPage", 20));
		setAttr("page",new Page<ProcessDefinition>(list,getParaToInt("pageNum", 1),
				getParaToInt("numPerPage", 20),0,Integer.valueOf(query.count()+"")));		
	}

}
