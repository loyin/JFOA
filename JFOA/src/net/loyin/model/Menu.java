package net.loyin.model;

import net.loyin.jFinal.anatation.TableBind;

import com.jfinal.plugin.activerecord.Model;
/***
 * 菜单
 * @author 刘声凤
 *  2012-9-4 上午10:39:04
 */
@TableBind(name="menu")
@SuppressWarnings("serial")
public class Menu extends Model<Menu> {
	public static final Menu dao=new Menu();
}
