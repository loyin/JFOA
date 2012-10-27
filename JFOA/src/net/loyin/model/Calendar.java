package net.loyin.model;

import net.loyin.jFinal.anatation.TableBind;

import com.jfinal.plugin.activerecord.Model;

/**
 * 个人日程
 * @author loyin
 * 2012-10-21
 */
@TableBind(name="Calendar")
@SuppressWarnings("serial")
public class Calendar extends Model<Calendar> {
	public static final Calendar dao=new Calendar();
}
