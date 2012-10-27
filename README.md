JFOA
====

基于JFinal和dwz activiti工作流引擎
activiti工作流引擎目前还在调整中，此次上传是方便大家共享
目前登陆机制是：
将用户信息存储在memcache中，然后通过sessionid来取出来。
首先要将memcache服务启动，及在exe目录下的memcache.exe