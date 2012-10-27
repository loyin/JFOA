package net.loyin.handler;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfinal.handler.Handler;
/**
 * session参数填入到request
 * @author 刘声凤
 *  2012-9-4 下午10:22:19
 */
public class SessionHandler extends Handler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		HttpSession session=request.getSession();
		Enumeration<String> atts=session.getAttributeNames();
		while(atts.hasMoreElements()==true){
			String an=atts.nextElement();
			request.setAttribute(an, session.getAttribute(an));
		}
	     nextHandler.handle(target, request, response, isHandled);
	}

}
