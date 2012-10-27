package net.loyin.model;

import net.loyin.jFinal.anatation.TableBind;

import com.jfinal.plugin.activerecord.Model;
/***
 * 系统参数配置
 * @author 刘声凤
 *  2012-9-4 上午10:39:04
 */
@TableBind(name="systemparam")
@SuppressWarnings("serial")
public class Systemparam extends Model<Systemparam> {
	public static final Systemparam dao=new Systemparam();
}
