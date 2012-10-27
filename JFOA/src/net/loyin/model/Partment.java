package net.loyin.model;

import net.loyin.jFinal.anatation.TableBind;

import com.jfinal.plugin.activerecord.Model;
/***
 * 部门
 * @author 刘声凤
 *  2012-9-4 上午10:39:04
 */
@TableBind(name="partment")
@SuppressWarnings("serial")
public class Partment extends Model<Partment> {
	public static final Partment dao=new Partment();
}
