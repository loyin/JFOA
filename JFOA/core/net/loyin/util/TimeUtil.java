package net.loyin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/**
 * 时间工具类
 * 
 * @author 刘声凤 2012-8-28 下午2:38:31
 */
public class TimeUtil {
	/***
	 * 友好时间显示
	 * @param req
	 * @param time
	 * @return
	 */
	public static String friendly_time(HttpServletRequest req, Date time) {
		Locale loc = (req != null) ? req.getLocale() : Locale.getDefault();
		return friendly_time(loc, time);
	}
	/***
	 * 友好时间显示
	 * @param loc
	 * @param time
	 * @return
	 */
	public static String friendly_time(Locale loc, Date time) {
		if (time == null)
			return ResourceUtils.getString("ui", "unknown", loc);
		int ct = (int) ((System.currentTimeMillis() - time.getTime()) / 1000);
		if (ct < 3600)
			return ResourceUtils.getStringForLocale(loc, "ui","minutes_before", Math.max(ct / 60, 1));
		if (ct >= 3600 && ct < 86400)
			return ResourceUtils.getStringForLocale(loc, "ui", "hours_before",ct / 3600);
		if (ct >= 86400 && ct < 2592000) { // 86400 * 30
			int day = ct / 86400;
			return ResourceUtils.getStringForLocale(loc, "ui",(day > 1) ? "days_before" : "yesterday", day);
		}
		if (ct >= 2592000 && ct < 31104000) // 86400 * 30
			return ResourceUtils.getStringForLocale(loc, "ui", "months_before",ct / 2592000);
		return ResourceUtils.getStringForLocale(loc, "ui", "years_before",ct / 31104000);
	}
	/***
	 * 友好时间显示
	 * @param req
	 * @param time 
	 * @param sdf
	 * @return
	 */
	public static String friendly_time(HttpServletRequest req, String time,SimpleDateFormat sdf) {
		try {
			return friendly_time(req, sdf.parse(time));
		} catch (ParseException e) {
		}
		return null;
	}
}
