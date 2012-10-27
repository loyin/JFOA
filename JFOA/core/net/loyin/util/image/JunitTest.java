package net.loyin.util.image;

public class JunitTest {
	public static void main(String[] args) {
		ZoomImage image = new ZoomImage();
		try {
			String p=	"2";
						//"42766159";
			image.init(
					"E:/temp/背景图片/"+p+"_2.jpg",
					"E:/Workspaces/MyEclipse 9/jia12/WebRoot/themes/images/logo_h_min.png",
					"E:/temp/背景图片/"+p+".jpg");
			//image.account(9);
			
			image.CreateSmal(250,150,false,"_min");
		} catch (Exception e) {
		}
	}

}
