package net.loyin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.loyin.jFinal.anatation.PowerBind;
import net.loyin.jFinal.anatation.RouteBind;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

@RouteBind(path = "/file")
public class FileController extends Controller {
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmsss");
	private static String uploadroot = "/upload/";
	private static String root;
	/**单个或多个文件上传      swfupload*/
	@SuppressWarnings("deprecation")
	@PowerBind
	public void up() {
		String errmsg ="", savefilename="";
		StringBuffer files=new StringBuffer();
		List<UploadFile> upfilelist = this.getFiles();
		boolean suc=false;
		if(upfilelist!=null&&upfilelist.isEmpty()==false){
			if(root==null)
				root=this.getRequest().getContextPath();
			String realPath = this.getRequest().getRealPath("/");
			int fsize=upfilelist.size();
			int i=1;
			for(UploadFile upfile:upfilelist){
				File file = upfile.getFile();
				String filedataFileName = upfile.getOriginalFileName();
				savefilename = uploadroot+ sf.format(new Date())+ filedataFileName.substring(filedataFileName.lastIndexOf("."));
				if(file != null){
					upfile.getFile().renameTo(new File(realPath + savefilename));
					suc=true;
					files.append(root+savefilename);
					if(i<fsize)
						files.append(",");
				}
				i++;
			}
		}else{
			errmsg="未上传文件";
		}
		this.renderText("{'err':'" + errmsg + "','msg':'" +files.toString()+ "','suc':"+suc+"}");
	}
	
	/**文件下载 */
	@PowerBind
	public void down() {
		
	}

}
