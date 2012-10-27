package net.loyin.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-7 21:56:47 
* 远程的接口的实现 
*/ 
@SuppressWarnings("serial")
public class HelloImpl extends UnicastRemoteObject implements IHello { 
    /** 
     * 因为UnicastRemoteObject的构造方法抛出了RemoteException异常，因此这里默认的构造方法必须写，必须声明抛出RemoteException异常 
     * 
     * @throws RemoteException 
     */ 
    public HelloImpl() throws RemoteException { 
    	/**plugin执行start方法后可以在非web等程序中调用数据库操作*/
    	DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://127.0.0.1/mymall?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull", "root", "123456");
    	druidPlugin.start();
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.start();
    } 

    /** 
     * 简单的返回“Hello World！"字样 
     * 
     * @return 返回“Hello World！"字样 
     * @throws java.rmi.RemoteException 
     */ 
    public String helloWorld() throws RemoteException { 
        return "Hello World!"; 
    } 

    /** 
     * 一个简单的业务方法，根据传入的人名返回相应的问候语 
     * 
     * @param someBodyName 人名 
     * @return 返回相应的问候语 
     * @throws java.rmi.RemoteException 
     */ 
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException { 
    	Record r= Db.findById("manager", 1);
        return "你好，" +r.getStr("username")+ someBodyName + "!"; 
    } 
}