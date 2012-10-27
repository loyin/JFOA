package net.loyin.util.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * 缩放
 * 
 */
public class ZoomImage {
	private int LOGO_H;
	private int LOGO_W;
	private AddWatermark addWatermark = null;
	private String saveas;

	/**
	 * 初始化
	 * 
	 * @param saveas 保存的地址
	 * @param logon 水印图片地址
	 * @param image 原图片地址
	 * @throws Exception
	 */
	public void init(String saveas, String logon, String image)
			throws Exception {
		this.saveas = saveas;
		addWatermark = new AddWatermark(image, logon);// image是图片地址,logo是添加水印图片的地址
		addWatermark.readAppendImage();
		addWatermark.readOriginalImage();
	}

	/**
	 * 计算图片大小并在原图上添加水印
	 * 
	 * @param postion
	 *            位置 1左上角2正上方9右下角 按照九宫格的位置定位
	 * @throws Exception
	 */
	public void account(int postion) throws Exception {
		// 原图
		int originality_W = this.addWatermark.getOriginalWidth();
		int originality_H = this.addWatermark.getOriginalHeigth();
		// 水印的logo
		LOGO_H = this.addWatermark.getAppendHeigth();
		LOGO_W = this.addWatermark.getAppendWidth();

		if (LOGO_H >= originality_H)// 高度
		{
			originality_H = LOGO_H;
		}

		if (LOGO_W >= originality_W)// 宽度
		{
			originality_W = LOGO_W;
		}

		BufferedImage originalImage = new BufferedImage(originality_W,
				originality_H, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = originalImage.getGraphics();
		Color color = new Color(255, 255, 255);
		graphics.setColor(color);
		graphics.fillRect(0, 0, originality_W, originality_H);
		graphics.drawImage(this.addWatermark.getOriginalImage(), 0, 0,
				originality_W, originality_H, null);
		graphics.dispose();
		addWatermark.setOriginalImage(originalImage);
		addWatermark.setCoordinate(postion);
		addWatermark.Merger(saveas);

	}

	/**
	 * 创建小图
	 * @param showall true:全部缩略
	 * @param minisaveas 图片缩略图文件名
	 * @throws Exception
	 */
	public void CreateSmal(int w, int h,boolean showall,String minisaveas) throws Exception {
		BufferedImage image = this.addWatermark.getOriginalImage();
		BufferedImage small = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);// 创建一个新图片
		Graphics graphics = small.getGraphics();
		if(showall==true){
			graphics.drawImage(image, 0, 0, w, h, null);
		}else{
			int oldw1=0;
			int oldh1=0;
			int oldw=image.getWidth();
			int oldh=image.getHeight();

			float m=(float)w/(float)h;
			if(oldw>oldh){
				if(m>1){//
					oldw1=oldw;
					oldh1=oldw*h/w;
					if(oldh1>oldh){
						oldh1=oldh;
						oldw1=oldh*w/h;
					}
				}else if(m<1){
					oldh1=oldh;
					oldw1=oldh*w/h;
				}else{//
					oldw1=oldh;
					oldh1=oldh;
				}
			}else if(oldw<oldh){
				if(m>1){
					oldw1=oldw;
					oldh1=oldw*h/w;
					if(oldh1>oldh){
						oldh1=oldh;
						oldw1=oldh*w/h;
					}
				}else if(m<1){
					oldh1=oldh;
					oldw1=oldh*w/h;
				}else{
					oldw1=oldw;
					oldh1=oldw;
				}
			}else{
				if(m>1){
					oldw1=oldw;
					oldh1=oldw*h/w;
				}else if(m<1){
					oldh1=oldh;
					oldw1=oldh*w/h;
				}else{
					oldw1=oldw;
					oldh1=oldh;
				}
			}
			
			graphics.drawImage(image, 0, 0, w, h,0,0,oldw1,oldh1, null);
		}
		graphics.dispose();
//		String filetype = this.getFileType(saveas);
//		this.saveas = this.saveas.replaceAll(filetype + "$", px + filetype);
//		this.addWatermark.setSaveas(saveas);
		this.addWatermark.setSaveas(minisaveas);
		this.addWatermark.save(small, w, h);

	}
	public void zoom(int w, int h,String oldfile,String savefile) throws Exception{
		BufferedImage image =ImageIO.read(new File(oldfile));
		int width=image.getWidth();
		int height=image.getHeight();
		int oldw1=width;
		int oldh1=height;
		if ( width > w || height > h ) 
        {
          if ( width > height){
            height = height*w/width; 
            width = w; 
          } else{ 
            width = width*h/height; 
          height = h; 
          } 
        }
		BufferedImage small = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);// 创建一个新图片
		Graphics graphics = small.getGraphics();
		graphics.drawImage(image, 0, 0, width, height,0,0,oldw1,oldh1, null);
		graphics.dispose();
		this.addWatermark.setSaveas(savefile);
		this.addWatermark.save(small, w, h);
	}
	private void setFix(int x, int y) {
		if (this.LOGO_W / this.LOGO_H > x / y) {
			this.setX(x);
		} else {
			this.setY(y);
		}
	}

	private void setX(int x) {
		this.LOGO_W = (int) (this.LOGO_W * x / this.LOGO_H);
	}

	private void setY(int y) {
		this.LOGO_H = (int) (this.LOGO_H * y / this.LOGO_W);
	}

	/**
	 * 获得文件的类型
	 */
	private String getFileType(String file) {
		Pattern pattern = Pattern.compile("[.\\\\]\\w++$");
		Matcher matcher = pattern.matcher(file);
		if (matcher.find())
			file = matcher.group();
		return file;
	}

}
