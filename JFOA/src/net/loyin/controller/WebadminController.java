package net.loyin.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.loyin.StaticCfg;
import net.loyin.jFinal.anatation.PowerBind;
import net.loyin.jFinal.anatation.RouteBind;
import net.loyin.jFinal.plugin.sqlXml.SqlManager;
import net.loyin.memcache.MemcacheTool;
import net.loyin.util.safe.MD5;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 后台管理
 * 
 * @author 刘声凤 2012-9-4 下午4:51:36
 */
@RouteBind(path = "/")
public class WebadminController extends BaseController {
	
	public void index() {
		this.setAttr("StaticCfg", new StaticCfg());
//		Record m=this.getSessionAttr("manager");
		Record m=getCurrentUser();
		if(m==null){
			this.setAttr("StaticCfg", new StaticCfg());
		}else{
			List<Record> menus=fetchMenu();
			if(menus!=null&&menus.isEmpty()==false){
				this.setAttr("menus",menus);
				this.setAttr("pid", menus.get(0).getLong("id"));
				this.setAttr("menuChild",menus);
				this.setAttr("user",m);
				this.render("main.html");
			}
		}
	}
	public void commmenu(){}
	public void getmenu(){
		this.setAttr("pid", getParaToLong(0));
		this.setAttr("menuChild", fetchMenu());
	}
	@PowerBind
	public void loginDialog(){
		this.setAttr("StaticCfg", new StaticCfg());
		this.render("loginDialog.html");
	}
	@SuppressWarnings("unchecked")
	private List<Record> fetchMenu(){
		String sid=getSession().getId();
		Record m=getCurrentUser();
		List<Record> menus=(List<Record>)MemcacheTool.mcc.get("menu"+sid);
		if(menus==null||menus.isEmpty())
			menus=Db.find(SqlManager.sql("webadmin.getpower"),m.get("id"));
		if(menus!=null&&menus.isEmpty()==false){
			//将菜单放置memcache
			MemcacheTool.mcc.set("menu"+sid,menus,new Date(new Date().getTime()+86400000));
			List<String> powersafecodelist=new ArrayList<String>();
			for(Record menu:menus){
				int menuType=Integer.valueOf(menu.get("type").toString());
				if(menuType==2){
					String safecode=menu.getStr("safecode");
					if(safecode!=null&&!"".equals(safecode.trim())){
						powersafecodelist.add(safecode);
					}
				}
			}
			//将按钮放置memcache
			MemcacheTool.mcc.set("powersafecodelist"+sid,powersafecodelist,new Date(new Date().getTime()+86400000));
		}
		return menus;
	}
	@SuppressWarnings("unchecked")
	@PowerBind
	public void login(){
		String username=this.getPara("username");
		String pwd=this.getPara("pwd");
		String code=this.getPara("code");
		String check= this.getSessionAttr("check");
		this.removeSessionAttr("check");
		boolean validCode="1".equals((String)StaticCfg.get("validCode").get("value"))?true:false;
		
		if(username==null||"".equals(username.trim())||pwd==null||"".equals(pwd)||(code==null||"".equals(code))&&validCode){
			this.toDwzJson(300, "信息填写不全！");
		}else
		if((check==null||"".equals(check))&&validCode){
			this.toDwzJson(300, "验证码超时！");
		}else if(validCode&&(check!=null&&code!=null&&!check.equals(code.toLowerCase()))){
			this.toDwzJson(300, "验证码错误！");
		}else{
			pwd=MD5.getMD5ofStr(pwd);
			Record m=Db.findFirst(SqlManager.sql("webadmin.login"), new Object[]{username,pwd});
			if(m!=null&&m.getLong("id")!=0){
//				this.setSessionAttr("manager", m);
				String nowsid=this.getSession().getId();
				/**唯一登录，即同一用户只可在一处登录*/
				/**第一步 获取所有的session集合
				 * 第二步 比较对应sessionid存储的Record记录
				 * 第三步 对应的session进行超时操作,删除sessionid对应的缓存*/
				boolean single="1".equals(StaticCfg.get("single").get("value"))?true:false;
				if(single){
					Set<String>sessionSet=(Set<String>)MemcacheTool.mcc.get("clientSet");
					if(sessionSet!=null&&sessionSet.isEmpty()==false){
						Iterator<String> it= sessionSet.iterator();
						while(it.hasNext()){
							String sid=it.next();
							Record r=(Record) MemcacheTool.mcc.get(sid);
							if(r!=null)
							if(!sid.equals(nowsid)&&r.get("userno").equals(m.get("userno"))){
								MemcacheTool.mcc.delete(sid);
								MemcacheTool.mcc.delete("menu"+sid);
								MemcacheTool.mcc.delete("btn"+sid);
							}
						}
					}
				}
				/**唯一登录结束*/
				MemcacheTool.mcc.set(nowsid, m,new Date(new Date().getTime()+86400000));
				fetchMenu();
				this.toDwzJson(200, "登录成功");
			}else{
				this.toDwzJson(300, "用户名或密码错误");
			}
		}
	}
	@PowerBind
	public void logout(){
//		this.removeSessionAttr("manager");
		String sessionid=this.getSession().getId();
		MemcacheTool.mcc.delete(sessionid);
		MemcacheTool.mcc.delete("menu"+sessionid);
		MemcacheTool.mcc.delete("powersafecodelist"+sessionid);
		this.redirect("/");
	}
	public void fun(){}
	private static final String chars = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
	private static final int WIDTH = 150;
	private static final int HEIGHT = 50;
	@PowerBind
	public void jpg() {
		HttpServletResponse response = this.getResponse();
		HttpSession session = this.getSession();
		response.setContentType("image/jpeg");

		// 防止浏览器缓冲
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		char[] rands = getCode(4);
		drawBackground(g);
		drawRands(g, rands);
		g.dispose();
		try {
			ServletOutputStream out = response.getOutputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "PNG", bos);
			byte[] buf = bos.toByteArray();
			response.setContentLength(buf.length);
			out.write(buf);
			bos.close();
			out.close();
			session.setAttribute("check", new String(rands).toLowerCase());
		} catch (Exception e) {}
		renderNull();
	}

	/**
	 * 产生随机数
	 * @return
	 */
	private char[] getCode(int length) {
		char[] rands = new char[length];
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * chars.length());
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	/**
	 * 绘制背景
	 * 
	 * @param g
	 */
	private void drawBackground(Graphics g) {
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Random random = new Random();
		int len = 0;
		while (len <= 5) {
			len = random.nextInt(15);
		}
		for (int i = 0; i < len; i++) {
			int x = (int) (random.nextInt(WIDTH));
			int y = (int) (random.nextInt(HEIGHT));
			int red = (int) (255 - random.nextInt(200));
			int green = (int) (255 - random.nextInt(200));
			int blue = (int) (255 - random.nextInt(200));
			g.setColor(new Color(red, green, blue));
			// g.drawLine(x, y, random.nextInt(WIDTH)-x,
			// random.nextInt(HEIGHT)-y);
			g.drawOval(x, y, 2, 2);
		}
	}

	/**
	 * 绘制验证码
	 * @param g
	 * @param rands
	 */
	private void drawRands(Graphics g, char[] rands) {
		Random random = new Random();

		g.setFont(new Font("黑体", Font.ITALIC | Font.BOLD, 45));
		for (int i = 0; i < rands.length; i++) {
			int red = (int) (random.nextInt(255));
			int green = (int) (random.nextInt(255));
			int blue = (int) (random.nextInt(255));
			g.setColor(new Color(red, green, blue));
			g.drawString("" + rands[i], i * 40, 40);
		}
	}
}
