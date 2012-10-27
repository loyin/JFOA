--将全部的菜单配置到权限表中
delete from powermenu;
insert into powermenu (objid,type,menuid) select 1,0,id from menu