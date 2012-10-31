import com.jfinal.core.JFinal;

/**
 * 服务器启动管理
 * @author loyin
 * 2012-10-21
 */
public class Server {
	public static void main(String[] args) {
		String webAppDir=Server.class.getResource("/").getPath().replace("/WEB-INF/classes/", "");
		int port=81;
		String content="/";
		if(args!=null&&args.length>0){
			try{
			port=Integer.parseInt(args[0]);
			content=args[1];
			}catch(Exception e){}
		}
		try{
			JFinal.start(webAppDir.substring(1), port,content, 10);
		}catch(Exception e){e.printStackTrace();}
	}
}
