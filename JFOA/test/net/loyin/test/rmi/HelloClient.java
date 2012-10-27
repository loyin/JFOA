package net.loyin.test.rmi;

import java.rmi.Naming;

/** 
* Created by IntelliJ IDEA. 
* User: leizhimin 
* Date: 2008-8-7 22:21:07 
* 客户端测试，在客户端调用远程对象上的远程方法，并返回结果。 
*/ 
public class HelloClient { 
	public static IHello rhello;
    public static void main(String args[]){
    	try{
    		rhello=(IHello) Naming.lookup("rmi://127.0.0.1:8888/RHello");
    		}catch(Exception e){
    			e.printStackTrace();
    		}
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
            for(int i=0;i<=100;i++){
            	Thread t=new Thread(){
            		public void run(){
            			super.run();
            			try{
	        	            System.out.println(HelloClient.rhello.sayHelloToSomeBody("熔岩"+this.getId()));
            			 }catch(Exception e){
            	            	e.printStackTrace();
            	         }
            		}
            	};
            	t.start();
            }
    } 
}