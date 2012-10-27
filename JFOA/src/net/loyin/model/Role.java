package net.loyin.model;

import net.loyin.jFinal.anatation.TableBind;

import com.jfinal.plugin.activerecord.Model;

/***
 * 角色
 * @author 刘声凤
 *  2012-9-4 上午10:39:04
 */
@TableBind(name="role")
@SuppressWarnings("serial")
public class Role extends Model<Role> {
	public static final Role dao=new Role();
}
